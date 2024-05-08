package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户查询类
 * @author: hc
 * @date: 2023/7/2
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery extends BaseQuery {
    /**
     * 编号
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 电话
     */
    private String phone;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 状态：0不可用 1可用
     */
    private Integer state;

    @Override
    public String toString() {
        return "UserQuery{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", state=" + state +
                ", phone=" + phone +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}
