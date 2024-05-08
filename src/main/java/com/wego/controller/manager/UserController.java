package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.PageBean;
import com.wego.common.minio.MinioUtil;
import com.wego.common.utils.QueryUtil;
import com.wego.converter.UserConverter;
import com.wego.entity.domain.User;
import com.wego.entity.query.UserQuery;
import com.wego.entity.vo.UserVO;
import com.wego.ex.GlobalException;
import com.wego.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
 * 用户控制器
 * @author: hc
 * @date: 2023/7/2
 */
@Slf4j
@Controller
@RequestMapping("/manager/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 省份列表
     * @param userQuery
     * @param model
     * @param notification
     * @return
     */
    @GetMapping("/list")
    public String list(UserQuery userQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String keywords = userQuery.getKeywords();
        if (keywords != null) {
            if (userQuery.getNickname() == null) {
                userQuery.setNickname(keywords);
            }
            if (userQuery.getPhone() == null) {
                userQuery.setPhone(keywords);
            }
        }

        PageBean<User> userPageBean = userService.selectPage(userQuery);
        final PageBean<UserVO> pageBean = userConverter.userPageBean2UserVOPageBean(userPageBean);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(userQuery);
        pageBean.setUrl("manager/user/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("userQuery", userQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/user";
    }

    /**
     * 省份详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/details")
    public String details(Long id, Model model) {
        User user = userService.selectByPrimaryKey(id);

        model.addAttribute("user", user);

        return "manager/user_details";
    }

    /**
     * 打开添加修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/openAddUpdate")
    public String openAddUpdate(Long id, Model model) {
        User user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);

        if (id != null) {
            model.addAttribute("title", "修改用户");
        } else {
            model.addAttribute("title", "添加用户");
        }

        return "manager/user_add_update";
    }

    /**
     * 具体执行添加修改操作
     * @param user
     * @return
     */
    @PostMapping("/doAddUpdate")
    public String doAddUpdate(User user, MultipartFile avatarFile, RedirectAttributes redirectAttributes) {
        if (avatarFile != null && avatarFile.getSize() > 0) {
            final String FileName = minioUtil.putObject2(avatarFile, "user", null);
            user.setAvatar(FileName);
        }

        user.setUpdateTime(LocalDateTime.now());

        Notification notification;
        Long id = user.getId();

        if (id != null) {
            //修改
            boolean res = userService.updateByPrimaryKeySelective(user);
            String msg = res ? "修改成功！" : "修改失败";
            notification = new Notification(res, msg);
        } else {
            //添加
            user.setCreateTime(LocalDateTime.now());
            boolean res = userService.insertSelective(user);
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
     * @param user
     * @return
     */
    @GetMapping("/changeState")
    public String changeState(User user, RedirectAttributes redirectAttributes) {
        Integer state = user.getState();
        Long id = user.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        user.setState(state == 1 ? 0 : 1);
        user.setUpdateTime(LocalDateTime.now());

        boolean res = userService.updateByPrimaryKeySelective(user);

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
        boolean res = userService.deleteByPrimaryKey(id);

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
        boolean res = userService.batchDelete(ids);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "批量删除成功！" : "批量删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }
}
