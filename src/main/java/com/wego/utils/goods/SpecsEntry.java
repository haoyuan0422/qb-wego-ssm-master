package com.wego.utils.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 *  @author hc
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecsEntry {
    private String key;
    private List<SpecsItem> items;

    @Override
    public String toString() {
        String tmp = "{";
        for (SpecsItem item : items) {
            tmp += "\"" + item.getKey() + "\":\"";
            tmp += item.getValue() + "\",";
        }
        tmp = tmp.substring(0, tmp.length() - 1);
        tmp += "}";

        return "\"" + key + "\":" + tmp;
    }
}