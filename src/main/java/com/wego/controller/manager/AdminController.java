package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.PageBean;
import com.wego.common.bean.ResultBean;
import com.wego.common.minio.MinioUtil;
import com.wego.common.utils.QueryUtil;
import com.wego.common.utils.ResultBeanUtil;
import com.wego.converter.AdminConverter;
import com.wego.entity.domain.Admin;
import com.wego.entity.query.AdminQuery;
import com.wego.entity.vo.AdminVO;
import com.wego.ex.GlobalException;
import com.wego.service.AdminService;
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

/**
 * 管理员控制器
 *
 * @author hc
 */
@Controller
@RequestMapping("/manager/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminConverter adminConverter;

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 省份列表
     *
     * @param adminQuery
     * @param model
     * @param notification
     * @return
     */
    @GetMapping("/list")
    public String list(AdminQuery adminQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        final String keywords = adminQuery.getKeywords();
        if (keywords != null) {
            if (adminQuery.getNickname() == null) {
                adminQuery.setNickname(keywords);
            }
            if (adminQuery.getAccount() == null) {
                adminQuery.setAccount(keywords);
            }
            if (adminQuery.getPhone() == null) {
                adminQuery.setPhone(keywords);
            }
        }

        PageBean<Admin> adminPageBean = adminService.selectPage(adminQuery);
        //转换
        final PageBean<AdminVO> pageBean = adminConverter.adminPageBean2AdminVOPageBean(adminPageBean);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(adminQuery);
        pageBean.setUrl("manager/admin/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("adminQuery", adminQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/admin";
    }

    /**
     * 省份详情
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/details")
    public String details(Long id, Model model) {
        Admin admin = adminService.selectByPrimaryKey(id);

        model.addAttribute("admin", admin);

        return "manager/admin_details";
    }

    /**
     * 打开添加修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/openAddUpdate")
    public String openAddUpdate(Long id, Model model) {
        Admin admin = adminService.selectByPrimaryKey(id);
        model.addAttribute("admin", admin);

        if (id != null) {
            model.addAttribute("title", "修改管理员");
        } else {
            model.addAttribute("title", "添加管理员");
        }

        return "manager/admin_add_update";
    }

    /**
     * 判断指定账户的管理员是否存在
     * @param account
     * @return
     */
    @ResponseBody
    @GetMapping("/checkAccount")
    public ResultBean<String> checkAccount(String account) {
        boolean exist = adminService.accountExist(account);
        String msg = exist ? "指定账户的用户已经存在，请更换名称重新注册" : "指定账户的用户尚未存在，可以使用";
        return ResultBeanUtil.success(200, msg);
    }

    /**
     * 具体执行添加修改操作
     * @param admin
     * @return
     */
    @PostMapping("/doAddUpdate")
    public String doAddUpdate(Admin admin, MultipartFile avatarFile, RedirectAttributes redirectAttributes) {
        //上传图片
        if (avatarFile != null && avatarFile.getSize() > 0) {
            final String picFileName = minioUtil.putObject2(avatarFile, "admin", null);
            admin.setAvatar(picFileName);
        }

        admin.setUpdateTime(LocalDateTime.now());

        Notification notification;
        Long id = admin.getId();
        if (id != null) {
            //修改
            boolean res = adminService.updateByPrimaryKeySelective(admin);
            String msg = res ? "修改成功！" : "修改失败";
            notification = new Notification(res, msg);
        } else {
            //添加
            admin.setCreateTime(LocalDateTime.now());
            boolean res = adminService.insertSelective(admin);
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
     *
     * @param admin
     * @return
     */
    @GetMapping("/changeState")
    public String changeState(Admin admin, RedirectAttributes redirectAttributes) {
        Integer state = admin.getState();
        Long id = admin.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        admin.setState(state == 1 ? 0 : 1);
        admin.setUpdateTime(LocalDateTime.now());

        boolean res = adminService.updateByPrimaryKeySelective(admin);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "更新成功！" : "更新失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    public String deleteById(Long id, RedirectAttributes redirectAttributes) {
        boolean res = adminService.deleteByPrimaryKey(id);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "删除成功！" : "删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @GetMapping("/batchDelete")
    public String batchDelete(String ids, RedirectAttributes redirectAttributes) {
        boolean res = adminService.batchDelete(ids);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "批量删除成功！" : "批量删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }
}
