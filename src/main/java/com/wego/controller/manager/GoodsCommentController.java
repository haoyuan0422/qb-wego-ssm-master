package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.QueryUtil;
import com.wego.converter.trans.GoodsCommentTrans;
import com.wego.entity.domain.GoodsComment;
import com.wego.entity.query.GoodsCommentQuery;
import com.wego.entity.vo.GoodsCommentVO;
import com.wego.service.GoodsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/goodsComment")
public class GoodsCommentController {
    @Autowired
    private GoodsCommentService goodsCommentService;

    @Autowired
    private GoodsCommentTrans goodsCommentTrans;

    @GetMapping("/list")
    public String list(GoodsCommentQuery goodsCommentQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String keywords = goodsCommentQuery.getKeywords();
        if (keywords != null) {
            if (goodsCommentQuery.getContent() == null) {
                goodsCommentQuery.setContent(keywords);
            }
        }

        final PageBean<GoodsComment> commentPageBean = goodsCommentService.selectPage(goodsCommentQuery);
        //转换
        PageBean<GoodsCommentVO> pageBean = goodsCommentTrans.getInstance()
                .tPageBean2VPageBean(commentPageBean);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(goodsCommentQuery);
        pageBean.setUrl("manager/goodsComment/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("commentQuery", goodsCommentQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/order_comment";
    }

}
