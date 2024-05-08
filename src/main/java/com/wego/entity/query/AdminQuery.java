package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 管理员查询类
 *  @author hc
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminQuery extends BaseQuery {
    /**
     * 编号
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 登录名
     */
    private String account;

    /**
     * 电话
     */
    private String phone;

    /**
     * 状态
     */
    private Integer state;

    @Override
    public String toString() {
        return "AdminQuery{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", state=" + state +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}
