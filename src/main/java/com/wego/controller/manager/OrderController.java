package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.EntryBean;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.QueryUtil;
import com.wego.converter.trans.OrderItemTrans;
import com.wego.converter.trans.OrderTrans;
import com.wego.entity.domain.Address;
import com.wego.entity.domain.Order;
import com.wego.entity.domain.OrderItem;
import com.wego.entity.domain.User;
import com.wego.entity.query.OrderQuery;
import com.wego.entity.vo.OrderItemVO;
import com.wego.entity.vo.OrderVO;
import com.wego.ex.GlobalException;
import com.wego.service.AddressService;
import com.wego.service.CountryService;
import com.wego.service.OrderItemService;
import com.wego.service.OrderService;
import com.wego.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 订单控制器
 *
 * @author hc
 */
@Controller
@RequestMapping("/manager/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private OrderTrans orderTrans;

    @Autowired
    private OrderItemTrans orderItemTrans;

    /**
     * 省份列表
     *
     * @param orderQuery
     * @param model
     * @param notification
     * @return
     */
    @GetMapping("/list")
    public String list(OrderQuery orderQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String keywords = orderQuery.getKeywords();
        if (keywords != null) {
            final List<User> userList = userService.selectByNickameOrAccount(keywords);
            if (userList != null && userList.size() > 0) {
                final List<Long> idList = userList.stream().map(User::getId).toList();
                orderQuery.setUserIds(idList);
            }
        }

        final PageBean<Order> orderPageBean = orderService.selectPage(orderQuery);
        PageBean<OrderVO> pageBean = orderTrans.getInstance().tPageBean2VPageBean(orderPageBean);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(orderQuery);
        pageBean.setUrl("manager/order/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("orderQuery", orderQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/order";
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
        Order order = orderService.selectByPrimaryKey(id);
        //转换
        final OrderVO orderVO = orderTrans.getInstance().tObj2VObj(order);
        model.addAttribute("order", orderVO);

        List<OrderItem> orderItemList = orderItemService.selectOrderItemByOrderId(id);
        //转换
        final List<OrderItemVO> orderItemVOList = orderItemTrans.getInstance().tList2VList(orderItemList);
        model.addAttribute("orderItemList", orderItemVOList);

        //获取省份-城市-县区列表
        final Address address = addressService.selectByPrimaryKey(order.getAddressId());
        final List<EntryBean<String>> pccList = countryService.selectProvinceCityCountryByCountryId(address.getCountryId());
        model.addAttribute("pccList", pccList);

        return "manager/order_details";
    }

    /**
     * 修改状态
     *
     * @param order
     * @return
     */
    @GetMapping("/changeState")
    public String changeState(Order order, RedirectAttributes redirectAttributes) {
        Integer state = order.getState();
        Long id = order.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        order.setState(state == 1 ? 0 : 1);
        order.setUpdateTime(LocalDateTime.now());

        boolean res = orderService.updateByPrimaryKeySelective(order);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "更新成功！" : "更新失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }
}
