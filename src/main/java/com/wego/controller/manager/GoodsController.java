package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.PageBean;
import com.wego.common.minio.MinioUtil;
import com.wego.common.utils.JsonUtil;
import com.wego.common.utils.QueryUtil;
import com.wego.converter.GoodsConverter;
import com.wego.entity.domain.Category;
import com.wego.entity.domain.Goods;
import com.wego.entity.query.GoodsQuery;
import com.wego.entity.vo.GoodsVO;
import com.wego.ex.GlobalException;
import com.wego.service.CategoryService;
import com.wego.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品控制器
 *
 * @author hc
 */
@Controller
@RequestMapping("/manager/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsConverter goodsConverter;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 商品列表
     * @param goodsQuery
     * @param model
     * @param notification
     * @return
     */
    @GetMapping("/list")
    public String list(GoodsQuery goodsQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String keywords = goodsQuery.getKeywords();
        if (keywords != null) {
            if (goodsQuery.getName() == null) {
                goodsQuery.setName(keywords);
            }
            if (goodsQuery.getSellingPoint() == null) {
                goodsQuery.setSellingPoint(keywords);
            }
            if (goodsQuery.getInfo() == null) {
                goodsQuery.setInfo(keywords);
            }
        }

        PageBean<GoodsVO> pageBean = null;
        final PageBean<Goods> goodsPageBean = goodsService.selectPage(goodsQuery);
        //转换
        pageBean = goodsConverter.goodsPageBean2GoodsVOPageBean(goodsPageBean);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(goodsQuery);
        pageBean.setUrl("manager/goods/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("goodsQuery", goodsQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/goods";
    }

    /**
     * 商品详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/details")
    public String details(Long id, Model model) {
        Goods goods = goodsService.selectByPrimaryKey(id);

        model.addAttribute("goods", goods);
        System.out.println(JsonUtil.obj2String(goods));

        return "manager/goods_details";
    }

    /**
     * 打开添加修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/openAddUpdate")
    public String openAddUpdate(Long id, Model model) {
        //获取全部顶级商品类别
        final List<Category> categoryList = categoryService.selectCategoryByPid(null, -1, false);
        model.addAttribute("categoryList", categoryList);

        if (id != null) {
            Goods goods = goodsService.selectByPrimaryKey(id);
            model.addAttribute("goods", goods);
            model.addAttribute("title", "修改商品");
        } else {
            model.addAttribute("title", "添加商品");
        }

        return "manager/goods_add_update";
    }

    /**
     * 具体执行添加修改操作
     * @param goods
     * @return
     */
    @PostMapping("/doAddUpdate")
    public String doAddUpdate(Goods goods, MultipartFile picFile, MultipartFile[] imgsFile, RedirectAttributes redirectAttributes) {
        //上传图片
        if (picFile != null && picFile.getSize() > 0) {
            final String picFileName = minioUtil.putObject2(picFile, "goods", null);
            goods.setPic(picFileName);
        }

        if (imgsFile != null && imgsFile.length > 0) {
            String imgs = "";
            for (MultipartFile item : imgsFile) {
                final String tmp = minioUtil.putObject2(item, "goods", null);
                imgs += tmp + ",";
            }
            goods.setImgs(imgs.substring(0, imgs.length() - 1));
        }
        goods.setUpdateTime(LocalDateTime.now());

        boolean res;
        String msg;
        Long id = goods.getId();
        if (id != null) {
            //修改
            res = goodsService.updateByPrimaryKeySelective(goods);
            msg = res ? "修改成功！" : "修改失败";
        } else {
            //添加
            goods.setCreateTime(LocalDateTime.now());
            res = goodsService.insertSelective(goods);
            msg = res ? "添加成功！" : "添加失败";
        }
        Notification notification = new Notification(res, msg);

        //返回列表页面时，对话框中显示的提示信息
        redirectAttributes.addFlashAttribute("notification", notification);

        //重定向
        return "redirect:list";
    }

    /**
     * 修改状态
     * @param goods
     * @return
     */
    @GetMapping("/changeState")
    public String changeState(Goods goods, RedirectAttributes redirectAttributes) {
        Integer state = goods.getState();
        Long id = goods.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        goods.setState(state == 1 ? 0 : 1);
        goods.setUpdateTime(LocalDateTime.now());

        boolean res = goodsService.updateByPrimaryKeySelective(goods);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "更新成功！" : "更新失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    public String deleteById(Long id, RedirectAttributes redirectAttributes) {
        boolean res = goodsService.deleteByPrimaryKey(id);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "删除成功！" : "删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @GetMapping("/batchDelete")
    public String batchDelete(String ids, RedirectAttributes redirectAttributes) {
        boolean res = goodsService.batchDelete(ids);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "批量删除成功！" : "批量删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }
}
