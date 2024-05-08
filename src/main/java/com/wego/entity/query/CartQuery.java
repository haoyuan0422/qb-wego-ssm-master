package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车查询类
 *  @author hc
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartQuery extends BaseQuery {
    /**
     * 编号
     */
    private Long id;

    /**
     * 用户编号
     */
    private List<Long> userIds;

    /**
     * 用户昵称or账户
     */
    private String userName;

    public static void main(String[] args) {
        final CartQuery cartQuery = new CartQuery(1234L, List.of(22L, 33L, 44L), "haha");
        System.out.println(cartQuery);
    }

    @Override
    public String toString() {
        return "CartQuery{" +
                "id=" + id +
                ", userIds=" + userIds.stream().map(String::valueOf).collect(Collectors.joining(",")) +
                ", userName='" + userName + '\'' +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}
