package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.QueryUtil;
import com.wego.entity.domain.PayMethod;
import com.wego.entity.query.PayMethodQuery;
import com.wego.ex.GlobalException;
import com.wego.service.PayMethodService;
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
 * 支付方式控制器
 *
 * @author hc
 */
@Controller
@RequestMapping("/manager/payMethod")
public class PayMethodController {
    @Autowired
    private PayMethodService payMethodService;

    @GetMapping("/list")
    public String list(PayMethodQuery payMethodQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String keywords = payMethodQuery.getKeywords();
        if (keywords != null) {
            if (payMethodQuery.getName() == null) {
                payMethodQuery.setName(keywords);
            }
        }

        PageBean<PayMethod> pageBean = payMethodService.selectPage(payMethodQuery);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(payMethodQuery);
        pageBean.setUrl("manager/pay_method/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("payMethodQuery", payMethodQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }
        return "manager/pay_method";
    }

    @GetMapping("/openAddUpdate")
    public String openAddUpdate(Integer id, Model model) {
        PayMethod payMethod = payMethodService.selectByPrimaryKey(id);
        model.addAttribute("payMethod", payMethod);

        if (id != null) {
            model.addAttribute("title", "修改支付方式");
        } else {
            model.addAttribute("title", "添加支付方式");
        }

        return "manager/pay_method_add_update";
    }

    @PostMapping("/doAddUpdate")
    public String doAddUpdate(PayMethod payMethod, RedirectAttributes redirectAttributes) {
        payMethod.setUpdateTime(LocalDateTime.now());

        Notification notification;
        Integer id = payMethod.getId();

        if (id != null) {
            //修改
            boolean res = payMethodService.updateByPrimaryKeySelective(payMethod);
            String msg = res ? "修改成功！" : "修改失败";
            notification = new Notification(res, msg);
        } else {
            //添加
            payMethod.setCreateTime(LocalDateTime.now());
            boolean res = payMethodService.insert(payMethod);
            String msg = res ? "添加成功！" : "添加失败";
            notification = new Notification(res, msg);
        }

        //返回列表页面时，对话框中显示的提示信息
        redirectAttributes.addFlashAttribute("notification", notification);
        //重定向
        return "redirect:list";
    }

    @GetMapping("/details")
    public String details(Integer id, Model model) {
        PayMethod payMethod = payMethodService.selectByPrimaryKey(id);

        model.addAttribute("payMethod", payMethod);

        return "manager/pay_method_details";
    }

    @GetMapping("/deleteById")
    public String deleteById(Integer id, RedirectAttributes redirectAttributes) {
        boolean res = payMethodService.deleteByPrimaryKey(id);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "删除成功！" : "删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

    @GetMapping("/changeState")
    public String changeState(PayMethod payMethod, RedirectAttributes redirectAttributes) {
        Integer state = payMethod.getState();
        Integer id = payMethod.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        payMethod.setState(state == 1 ? 0 : 1);
        payMethod.setUpdateTime(LocalDateTime.now());

        boolean res = payMethodService.updateByPrimaryKeySelective(payMethod);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "更新成功！" : "更新失败";
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
        boolean res = payMethodService.batchDelete(ids);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "批量删除成功！" : "批量删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }
}
