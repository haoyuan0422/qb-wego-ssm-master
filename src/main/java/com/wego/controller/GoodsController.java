package com.wego.controller;

import com.wego.common.bean.PageBean;
import com.wego.common.utils.QueryUtil;
import com.wego.converter.GoodsConverter;
import com.wego.converter.trans.GoodsCommentTrans;
import com.wego.entity.domain.Category;
import com.wego.entity.domain.Goods;
import com.wego.entity.domain.GoodsComment;
import com.wego.entity.query.GoodsCommentQuery;
import com.wego.entity.query.GoodsQuery;
import com.wego.entity.vo.GoodsCommentVOFront;
import com.wego.entity.vo.GoodsDetialsVOFront;
import com.wego.entity.vo.GoodsVOFront;
import com.wego.service.CategoryService;
import com.wego.service.GoodsCommentService;
import com.wego.service.GoodsService;
import com.wego.utils.GoodsUtil;
import com.wego.utils.goods.SpecsEntry;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前端商品处理器
 * @author: hc
 * @date: 2023/7/14
 */
@Controller("GoodsControllerFront")
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsConverter goodsConverter;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsCommentService goodsCommentService;

    @Autowired
    private GoodsCommentTrans goodsCommentTrans;

    /**
     * 商品列表
     * @param goodsQuery
     * @return
     */
    @GetMapping("list")
    String list(GoodsQuery goodsQuery, Model model) {
        final String keywords = goodsQuery.getKeywords();
        if (keywords != null) {
            goodsQuery.setName(keywords);
            goodsQuery.setSellingPoint(keywords);
            goodsQuery.setInfo(keywords);
        }

        final PageBean<Goods> goodsPageBean = goodsService.selectPage(goodsQuery);
        final PageBean<GoodsVOFront> pageBean = goodsConverter.goodsPageBean2GoodsVOFrontPageBean(goodsPageBean);
        model.addAttribute("pageBean", pageBean);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(goodsQuery);
        pageBean.setUrl("goods/list?" + params);

        //导航显示的类别
        final Long categoryId = goodsQuery.getCategoryId();
        if (categoryId != null) {
            model.addAttribute("categoryId", categoryId);
            final Category categoryTmp = categoryService.selectByPrimaryKey(categoryId);
            final String categoryName = categoryTmp.getName();
            model.addAttribute("categoryName", categoryName);
        }

        return "goods_list";
    }

    /**
     * 商品详情  示例：http://localhost:8080/wego/goods/details?goodsId=722
     * @param goodsCommentQuery
     * @return
     */
    @GetMapping("details")
    String details(GoodsCommentQuery goodsCommentQuery, Model model) {
        //商品详情
        Goods goods = goodsService.selectByPrimaryKey(goodsCommentQuery.getGoodsId());
        GoodsDetialsVOFront goodsDetialsVOFront = goodsConverter.goods2GoodsDetailsVOFront(goods);
        if (goods.getSpecs() != null) {
            List<SpecsEntry> specList = GoodsUtil.goodsSpecs2Obj(goods.getSpecs());
            goodsDetialsVOFront.setSpecList(specList);
        }
        model.addAttribute("goods", goodsDetialsVOFront);

        //获取商品评论
        PageBean<GoodsComment> goodsCommentVOPageBean = goodsCommentService.selectPage(goodsCommentQuery);

        PageBean<GoodsCommentVOFront> commentVOFrontPageBean = goodsCommentTrans.getInstanceFront()
                .tPageBean2VPageBean(goodsCommentVOPageBean);
        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(goodsCommentQuery);
        commentVOFrontPageBean.setUrl("goods/details?" + params);
        model.addAttribute("pageBean", commentVOFrontPageBean);
        return "goods_details";
    }
}
