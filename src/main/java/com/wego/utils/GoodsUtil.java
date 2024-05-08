package com.wego.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.wego.utils.goods.SpecsEntry;
import com.wego.utils.goods.SpecsItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/4
 */
public class GoodsUtil {

    /**
     * 将数据库中保存的JSON形式的商品的规格信息转换成Java对象，便于在页面中展示
     * 待转换的数据：{"主题": {"颜色": "黑色", "屏幕尺寸": "23.8英寸"}, "接口": {"Type-C": "不支持Type-C接口", "DP接口": "不支持DP接口", "DVI接口": "不支持DVI接口", "VGA接口": "支持VGA接口", "HDMI接口": "支持HDMI接口", "USB扩展/充电": "不支持USB扩展/充电"}, "显示": {"亮度": "280cd/㎡", "色数": "16.7M"}, "规格": {"产品尺寸": "长539.8mm；宽181.8mm；高399.9mm", "壁挂规格": "100x100mm", "支架底座": "普通", "电源类型": "外接电源适配器", "是否内置音箱": "无内置音箱", "是否支持壁挂": "支持壁挂", "产品净重（kg）": "3.2Kg"}}
     * @param specs
     * @return
     */
    public static List<SpecsEntry> goodsSpecs2Obj(String specs) {
        List<SpecsEntry> list = new ArrayList<>();

        JsonFactory factory = new JsonFactory();
        try {
            JsonParser parser = factory.createParser(specs);
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = parser.getCurrentName();
                if (fieldName != null) {
                    SpecsEntry specsEntry = new SpecsEntry();
                    specsEntry.setKey(fieldName);
                    parser.nextToken();
                    List<SpecsItem> items = new ArrayList<>();
                    while (parser.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName2 = parser.getCurrentName();
                        if (fieldName2 != null) {
                            SpecsItem item = new SpecsItem();
                            item.setKey(fieldName2);
                            parser.nextToken();
                            item.setValue(parser.getText());
                            items.add(item);
                        }
                    }
                    specsEntry.setItems(items);
                    list.add(specsEntry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        String ss = "\t{\"主题\": {\"颜色\": \"黑色\", \"屏幕尺寸\": \"23.8英寸\"}, \"接口\": {\"Type-C\": \"不支持Type-C接口\", \"DP接口\": \"不支持DP接口\", \"DVI接口\": \"不支持DVI接口\", \"VGA接口\": \"支持VGA接口\", \"HDMI接口\": \"支持HDMI接口\", \"USB扩展/充电\": \"不支持USB扩展/充电\"}, \"显示\": {\"亮度\": \"280cd/㎡\", \"色数\": \"16.7M\"}, \"规格\": {\"产品尺寸\": \"长539.8mm；宽181.8mm；高399.9mm\", \"壁挂规格\": \"100x100mm\", \"支架底座\": \"普通\", \"电源类型\": \"外接电源适配器\", \"是否内置音箱\": \"无内置音箱\", \"是否支持壁挂\": \"支持壁挂\", \"产品净重（kg）\": \"3.2Kg\"}}";
        List<SpecsEntry> list = goodsSpecs2Obj(ss);
        System.out.println(list);
    }
}
