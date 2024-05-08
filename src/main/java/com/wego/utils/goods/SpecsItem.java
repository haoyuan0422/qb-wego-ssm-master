package com.wego.utils.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  @author hc
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecsItem {
    private String key;
    private String value;

    @Override
    public String toString() {
        return "\"" + key + "\"" + ":" + "\"" + value + "\"";
    }
}