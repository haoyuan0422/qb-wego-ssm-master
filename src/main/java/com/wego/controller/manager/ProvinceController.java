package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.QueryUtil;
import com.wego.entity.domain.Province;
import com.wego.entity.query.ProvinceQuery;
import com.wego.ex.GlobalException;
import com.wego.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

/**
 * 省份控制器
 * @author: hc
 * @date: 2023/7/2
 */
@Slf4j
@Controller
@RequestMapping("/manager/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    /**
     * 省份列表
     * @param provinceQuery
     * @param model
     * @param notification
     * @return
     */
    @GetMapping("/list")
    public String list(ProvinceQuery provinceQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String name = provinceQuery.getName();
        if (name == null) {
            String keywords = provinceQuery.getKeywords();
            provinceQuery.setName(keywords);
        }

        PageBean<Province> pageBean = provinceService.selectPage(provinceQuery);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(provinceQuery);
        pageBean.setUrl("manager/province/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("provinceQuery", provinceQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/province";
    }

    /**
     * 省份详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/details")
    public String details(Long id, Model model) {
        Province province = provinceService.selectByPrimaryKey(id);

        model.addAttribute("province", province);

        return "manager/province_details";
    }

    /**
     * 打开添加修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/openAddUpdate")
    public String openAddUpdate(Long id, Model model) {
        Province province = provinceService.selectByPrimaryKey(id);
        model.addAttribute("province", province);

        if (id != null) {
            model.addAttribute("title", "修改省份");
        } else {
            model.addAttribute("title", "添加省份");
        }

        return "manager/province_add_update";
    }

    /**
     * 具体执行添加修改操作
     * @param province
     * @return
     */
    @PostMapping("/doAddUpdate")
    public String doAddUpdate(Province province, RedirectAttributes redirectAttributes) {
        province.setUpdateTime(LocalDateTime.now());

        Notification notification;
        Long id = province.getId();

        if (id != null) {
            //修改
            boolean res = provinceService.updateByPrimaryKeySelective(province);
            String msg = res ? "修改成功！" : "修改失败";
            notification = new Notification(res, msg);
        } else {
            //添加
            province.setCreateTime(LocalDateTime.now());
            boolean res = provinceService.insertSelective(province);
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
     * @param province
     * @return
     */
    @GetMapping("/changeState")
    public String changeState(Province province, RedirectAttributes redirectAttributes) {
        Integer state = province.getState();
        Long id = province.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        province.setState(state == 1 ? 0 : 1);
        province.setUpdateTime(LocalDateTime.now());

        boolean res = provinceService.updateByPrimaryKeySelective(province);

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
        boolean res = provinceService.deleteByPrimaryKey(id);

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
        boolean res = provinceService.batchDelete(ids);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "批量删除成功！" : "批量删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }
}
