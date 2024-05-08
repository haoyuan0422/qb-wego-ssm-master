package com.wego.converter;

import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Admin;
import com.wego.entity.vo.AdminVO;
import org.mapstruct.Mapper;

/**
 * 管理员转换器
 * @author: hc
 * @date: 2023/7/6
 */
@Mapper(componentModel = "spring")
public interface AdminConverter {

    PageBean<AdminVO> adminPageBean2AdminVOPageBean(PageBean<Admin> adminPageBean);
}
