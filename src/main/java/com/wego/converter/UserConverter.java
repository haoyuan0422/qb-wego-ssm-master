package com.wego.converter;

import com.wego.common.bean.PageBean;
import com.wego.entity.domain.User;
import com.wego.entity.vo.UserSessionVO;
import com.wego.entity.vo.UserVO;
import org.mapstruct.Mapper;

/**
 * 用户转换器
 * @author: hc
 * @date: 2023/7/9
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    PageBean<UserVO> userPageBean2UserVOPageBean(PageBean<User> userPageBean);

    UserSessionVO user2UserSessionVO(User user);
}
