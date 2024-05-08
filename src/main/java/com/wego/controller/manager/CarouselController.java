package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.PageBean;
import com.wego.common.minio.MinioUtil;
import com.wego.common.utils.QueryUtil;
import com.wego.entity.domain.Carousel;
import com.wego.entity.query.CarouselQuery;
import com.wego.ex.GlobalException;
import com.wego.service.CarouselService;
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

/**
 * 轮播图控制器
 *
 * @author hc
 */
@Controller
@RequestMapping("/manager/carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 轮播图列表
     *
     * @param notification
     * @param model
     * @param carouselQuery
     * @return
     */
    @GetMapping("/list")
    public String list(CarouselQuery carouselQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String keywords = carouselQuery.getKeywords();
        if (keywords != null) {
            if (carouselQuery.getName() == null) {
                carouselQuery.setName(keywords);
            }
            if (carouselQuery.getInfo() == null) {
                carouselQuery.setInfo(keywords);
            }
        }

        PageBean<Carousel> pageBean = carouselService.selectPage(carouselQuery);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(carouselQuery);
        pageBean.setUrl("manager/carousel/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("carouselQuery", carouselQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/carousel";
    }

    /**
     * 轮播图详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/details")
    public String details(Long id, Model model) {
        Carousel carousel = carouselService.selectByPrimaryKey(id);

        model.addAttribute("carousel", carousel);

        return "manager/carousel_details";
    }

    /**
     * 打开添加修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/openAddUpdate")
    public String openAddUpdate(Long id, Model model) {
        if (id != null) {
            Carousel carousel = carouselService.selectByPrimaryKey(id);
            model.addAttribute("carousel", carousel);
            model.addAttribute("title", "修改轮播图");
        } else {
            model.addAttribute("title", "添加轮播图");
        }

        return "manager/carousel_add_update";
    }

    /**
     * 具体执行添加修改操作
     * @param carousel
     * @return
     */
    @PostMapping("/doAddUpdate")
    public String doAddUpdate(Carousel carousel, MultipartFile picFile, RedirectAttributes redirectAttributes) {
        //上传图片
        System.out.println("进入");
        System.out.println(picFile);
        if (picFile != null && picFile.getSize() > 0) {
            System.out.println("成功");
            final String picFileName = minioUtil.putObject2(picFile, "carousel", null);
            carousel.setPic(picFileName);
        }

        carousel.setUpdateTime(LocalDateTime.now());
        Notification notification;
        Long id = carousel.getId();

        if (id != null) {
            //修改
            boolean res = carouselService.updateByPrimaryKeySelective(carousel);
            String msg = res ? "修改成功！" : "修改失败";
            notification = new Notification(res, msg);
        } else {
            //添加
            carousel.setCreateTime(LocalDateTime.now());
            boolean res = carouselService.insertSelective(carousel);
            String msg = res ? "添加成功！" : "添加失败";
            notification = new Notification(res, msg);
        }

        //返回列表页面时，对话框中显示的提示信息
        redirectAttributes.addFlashAttribute("notification", notification);

        //重定向
        return "redirect:list";
    }

    /**
     * 修改状态
     * @param carousel
     * @return
     */
    @GetMapping("/changeState")
    public String changeState(Carousel carousel, RedirectAttributes redirectAttributes) {
        Integer state = carousel.getState();
        Long id = carousel.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        carousel.setState(state == 1 ? 0 : 1);
        carousel.setUpdateTime(LocalDateTime.now());

        boolean res = carouselService.updateByPrimaryKeySelective(carousel);

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
        boolean res = carouselService.deleteByPrimaryKey(id);

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
        boolean res = carouselService.batchDelete(ids);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "批量删除成功！" : "批量删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }
}
