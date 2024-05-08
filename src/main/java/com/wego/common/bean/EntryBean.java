package com.wego.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用来封装key-value类型的键值对
 * @author: hc
 * @date: 2023/7/13
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EntryBean<V> {
    private Serializable key;
    private V value;
}
