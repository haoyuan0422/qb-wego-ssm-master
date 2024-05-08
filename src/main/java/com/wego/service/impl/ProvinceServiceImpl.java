package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.Province;
import com.wego.entity.query.ProvinceQuery;
import com.wego.mapper.ProvinceMapper;
import com.wego.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 省份服务实现类
 * @author: hc
 * @date: 2023/7/2
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public boolean deleteByPrimaryKey(Long id) {
        return provinceMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public boolean insert(Province record) {
        return provinceMapper.insert(record) >= 1;
    }

    @Override
    public boolean insertSelective(Province record) {
        return provinceMapper.insertSelective(record) >= 1;
    }

    @Override
    public Province selectByPrimaryKey(Long id) {
        return provinceMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Province record) {
        return provinceMapper.updateByPrimaryKeySelective(record) == 1;
    }

    @Override
    public boolean updateByPrimaryKey(Province record) {
        return provinceMapper.updateByPrimaryKey(record) == 1;
    }

    @Override
    public PageBean<Province> selectPage(ProvinceQuery provinceQuery) {
        if (provinceQuery == null) {
            provinceQuery = new ProvinceQuery();
        }
        //设置分页信息
        Page<Province> page = PageHelper.startPage(provinceQuery.getPageNum(), provinceQuery.getPageSize());
        //查询数据
        provinceMapper.selectList(provinceQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<Province> pageBean = PageBeanUtil.page2PageBean(page);
        return pageBean;
    }

    @Override
    public List<Province> selectAll() {
        final List<Province> provinceList = provinceMapper.selectList(null);
        return provinceList;
    }

    @Override
    public boolean batchDelete(String ids) {
        long res = provinceMapper.batchDelete(ids.split(","));
        return res >= 0;
    }

    @Override
    public boolean batchUpdateSelective(List<Province> list) {
        return provinceMapper.batchUpdateSelective(list) >= 0;
    }
}

