package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.EntryBean;
import com.wego.common.bean.PageBean;
import com.wego.common.minio.MinioUtil;
import com.wego.common.utils.QueryUtil;
import com.wego.converter.CategoryConverter;
import com.wego.entity.domain.Category;
import com.wego.entity.query.CategoryQuery;
import com.wego.ex.GlobalException;
import com.wego.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品类别控制器
 * @author: hc
 * @date: 2023/7/10
 */
@Controller
@RequestMapping("/manager/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 查找某一类别下面的所有子类
     * @param pid
     * @return
     */
    @ResponseBody
    @GetMapping("/selectEntryByPid")
    List<EntryBean<String>> selectEntryByPid(Long pid) {
        final List<Category> categoryList = categoryService.selectCategoryByPid(pid, -1, false);
        final List<EntryBean<String>> entryBeanList = categoryConverter.categoryList2EntryList(categoryList);
        return entryBeanList;
    }

    @GetMapping("/list")
    public String list(CategoryQuery categoryQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String keywords = categoryQuery.getKeywords();
        if (keywords != null) {
            if (categoryQuery.getName() == null) {
                categoryQuery.setName(keywords);
            }
            if (categoryQuery.getInfo() != null) {
                categoryQuery.setInfo(keywords);
            }
        }

        PageBean<Category> pageBean = categoryService.selectPage(categoryQuery);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(categoryQuery);
        pageBean.setUrl("manager/category/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("categoryQuery", categoryQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/category";
    }

    /**
     * 省份详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/details")
    public String details(Long id, Model model) {
        Category category = categoryService.selectByPrimaryKey(id);

        model.addAttribute("category", category);

        return "manager/category_details";
    }

    /**
     * 打开添加修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/openAddUpdate")
    public String openAddUpdate(Long id, Model model) {
        Category category = categoryService.selectByPrimaryKey(id);
        model.addAttribute("category", category);

        if (id != null) {
            model.addAttribute("title", "修改类别");
        } else {
            model.addAttribute("title", "添加类别");
        }

        return "manager/category_add_update";
    }

    @PostMapping("/doAddUpdate")
    public String doAddUpdate(Category category, MultipartFile iconFile, RedirectAttributes redirectAttributes) {
        if (iconFile != null && iconFile.getSize() > 0) {
            final String picFileName = minioUtil.putObject2(iconFile, "category", null);
            category.setIcon(picFileName);
        }

        category.setUpdateTime(LocalDateTime.now());

        Notification notification;
        Long id = category.getId();

        if (id != null) {
            //修改
            boolean res = categoryService.updateByPrimaryKeySelective(category);
            String msg = res ? "修改成功！" : "修改失败";
            notification = new Notification(res, msg);
        } else {
            //添加
            category.setCreateTime(LocalDateTime.now());
            boolean res = categoryService.insertSelective(category);
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
     * @param category
     * @return
     */
    @GetMapping("/changeState")
    public String changeState(Category category, RedirectAttributes redirectAttributes) {
        Integer state = category.getState();
        Long id = category.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        category.setState(state == 1 ? 0 : 1);
        category.setUpdateTime(LocalDateTime.now());

        boolean res = categoryService.updateByPrimaryKeySelective(category);

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
        boolean res = categoryService.deleteByPrimaryKey(id);

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
        boolean res = categoryService.batchDelete(ids);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "批量删除成功！" : "批量删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }
}
