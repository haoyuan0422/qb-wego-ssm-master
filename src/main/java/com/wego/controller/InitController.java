package com.wego.controller;

import com.wego.bean.Notification;
import com.wego.common.bean.EntryBean;
import com.wego.constant.WeGoConst;
import com.wego.converter.CategoryConverter;
import com.wego.entity.domain.Category;
import com.wego.service.CategoryService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目初始化<br>
 * 项目启动成功后需要首先请求该控制器
 * @author: hc
 * @date 2023/7/14
 */
@Controller
public class InitController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryConverter categoryConverter;

    @GetMapping(value = "/init")
    String init(HttpServletRequest request, Model model) {
        final ServletContext context = request.getServletContext();

        //图片服务器网址
        context.setAttribute("RESOURCES_SERVER", WeGoConst.RESOURCES_SERVER);

        //订单状态
        context.setAttribute("ORDER_STATE_MAP", WeGoConst.ORDER_STATE);

        //首页类别
        Map<EntryBean<String>, List<EntryBean<String>>> categoryMap = new HashMap<>();

        final List<Category> parentCategoryList = categoryService.selectCategoryByPid(null, 8, true);
        for (Category parentCategory : parentCategoryList) {
            List<EntryBean<String>> entryBeanList = new ArrayList<>();
            final List<Category> subCategoryList = categoryService.selectCategoryByPid(parentCategory.getId(), 6, true);
            for (Category subCategory : subCategoryList) {
                final EntryBean<String> entryBean = categoryConverter.obj2Entry(subCategory);
                entryBeanList.add(entryBean);
            }
            final EntryBean<String> entryBean = categoryConverter.obj2Entry(parentCategory);
            categoryMap.put(entryBean, entryBeanList);
        }
        //System.out.println(JsonUtil.obj2String(categoryMap));
        context.setAttribute("categoryMap", categoryMap);

        //热门搜索
        final List<Category> categoryList = categoryService.selectCategoryByPid(-1L, 6, true);
        final List<EntryBean<String>> categoryList2EntryBeanList = categoryConverter.categoryList2EntryList(categoryList);
        context.setAttribute("categoryList", categoryList2EntryBeanList);

        //通知
        final Notification notification = new Notification(true, "系统初始化成功！");
        model.addAttribute("notification", notification);

        return "redirect:managerIndex";
    }
}
