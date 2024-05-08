package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 地址查询类
 * @author hc
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressQuery extends BaseQuery {
    /**
     * 编号
     */
    private Long id;

    /**
     * 收货人姓名
     */
    private String receiver;

    /**
     * 电话
     */
    private String phone;

    /**
     * 县区编号
     */
    private Long countryId;

    /**用户编号
     *
     */
    private Long userId;

    /**
     * 地址
     */
    private String addr;

    /**
     * 是否是默认
     */
    private Boolean moRen;

    /**
     * 状态：0不可用 1可用
     */
    private Integer state;

    @Override
    public String toString() {
        return "AddressQuery{" +
                "id=" + id +
                ", receiver='" + receiver + '\'' +
                ", countryId='" + countryId + '\'' +
                ", userId='" + userId + '\'' +
                ", addr='" + addr + '\'' +
                ", moRen=" + moRen + '\'' +
                ", state=" + state +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}
