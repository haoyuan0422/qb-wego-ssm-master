package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.Category;
import com.wego.entity.query.CategoryQuery;
import com.wego.mapper.CategoryMapper;
import com.wego.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 类别服务实现类
 * @author: hc
 * @date: 2023/7/5
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    @Autowired
    private CategoryMapper cityMapper;

    @Override
    public List<Category> selectCategoryByPid(Long pid, Integer amount, boolean flag) {
        final Example example = new Example(Category.class);
        final Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("priority DESC, update_time DESC");

        //前端调用
        if (flag) {
            //可用
            criteria.andEqualTo("state", 1);
        }
        //amout不等于-1时才分页，否则查询全部
        if (amount != -1) {
            PageHelper.startPage(1, amount);
        }

        if (pid == null) {
            //查询父类别
            criteria.andIsNull("pid");
        } else if (pid == -1) {
            //查询所有的子类别
            criteria.andIsNotNull("pid");
        } else {
            //查询指定父类别下的子类别
            criteria.andEqualTo("pid", pid);
        }
        return cityMapper.selectByExample(example);
    }

    @Override
    public PageBean<Category> selectPage(CategoryQuery categoryQuery) {
        if (categoryQuery == null) {
            categoryQuery = new CategoryQuery();
        }
        //设置分页信息
        Page<Category> page = PageHelper.startPage(categoryQuery.getPageNum(), categoryQuery.getPageSize());
        //查询数据
        cityMapper.selectList(categoryQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<Category> pageBean = PageBeanUtil.page2PageBean(page);

        return pageBean;
    }

}
