package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.common.utils.QueryUtil;
import com.wego.converter.trans.GoodsCommentTrans;
import com.wego.converter.trans.OrderCommentTrans;
import com.wego.entity.domain.GoodsComment;
import com.wego.entity.domain.OrderComment;
import com.wego.entity.query.GoodsCommentQuery;
import com.wego.entity.query.OrderCommentQuery;
import com.wego.entity.vo.GoodsCommentVO;
import com.wego.entity.vo.OrderCommentVO;
import com.wego.ex.GlobalException;
import com.wego.service.GoodsCommentService;
import com.wego.service.OrderCommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 评论控制器
 *
 * @author hc
 */
@Controller
@RequestMapping("/manager/orderComment")
public class OrderCommentController {
    @Autowired
    private OrderCommentService orderCommentService;

    @Autowired
    private GoodsCommentService goodsCommentService;

    @Autowired
    private GoodsCommentTrans goodsCommentTrans;

    @Autowired
    private OrderCommentTrans orderCommentTrans;

    /**
     * 评论列表
     * @param orderCommentQuery
     * @param model
     * @param notification
     * @return
     */
    @GetMapping("/list")
    public String list(OrderCommentQuery orderCommentQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String keywords = orderCommentQuery.getKeywords();
        if (keywords != null) {
            if (orderCommentQuery.getContent() == null) {
                orderCommentQuery.setContent(keywords);
            }
        }

        final PageBean<OrderComment> commentPageBean = orderCommentService.selectPage(orderCommentQuery);
        //转换
        PageBean<OrderCommentVO> pageBean = commentPageBean2CommentVOPageBean(commentPageBean);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(orderCommentQuery);
        pageBean.setUrl("manager/orderComment/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("commentQuery", orderCommentQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/order_comment";
    }

    private PageBean<OrderCommentVO> commentPageBean2CommentVOPageBean(PageBean<OrderComment> pageBean) {
        PageBean<OrderCommentVO> commentVOPageBean = PageBeanUtil.pageBean2PageBean(pageBean);

        final List<OrderComment> orderCommentList = pageBean.getResult();
        List<OrderCommentVO> orderCommentVOList = orderCommentTrans.getInstance().tList2VList(orderCommentList);
        commentVOPageBean.setResult(orderCommentVOList);

        return commentVOPageBean;
    }


    /**
     * 省份详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/details")
    public String details(Long id, Model model) {
        //订单评论信息
        OrderComment orderComment = orderCommentService.selectByPrimaryKey(id);
        model.addAttribute("orderComment", orderComment);
        //商品评论信息
        List<GoodsComment> goodsCommentList = goodsCommentService.selectList(GoodsCommentQuery.builder()
                .orderCommentId(orderComment.getId())
                .build());
        List<GoodsCommentVO> goodsCommentVOList = goodsCommentTrans.getInstance().tList2VList(goodsCommentList);

        model.addAttribute("goodsCommentList", goodsCommentVOList);

        return "manager/order_comment_details";
    }

    /**
     * 修改状态
     * @param orderComment
     * @return
     */
    @GetMapping("/changeState")
    public String changeState(OrderComment orderComment, RedirectAttributes redirectAttributes) {
        Integer state = orderComment.getState();
        Long id = orderComment.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        orderComment.setState(state == 1 ? 0 : 1);
        boolean res = orderCommentService.updateByPrimaryKeySelective(orderComment);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "更新成功！" : "更新失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

}
