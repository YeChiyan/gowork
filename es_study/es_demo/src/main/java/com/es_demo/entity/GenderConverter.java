package com.contractdemo.entity;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.enums.CellDataTypeEnum;

public class GenderConverter implements Converter<Integer> {

    @Override
    public Class<Integer> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * Excel => Java
     */
//    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) {
        String genderStr = cellData.getStringValue();
        if ("男".equals(genderStr)) {
            return 1;
        } else if ("女".equals(genderStr)) {
            return 0;
        }
        return -1; // 返回-1表示不合法的值
    }

    /**
     * Java => Excel
     */
    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) {
        // 根据value返回对应的字符串，1为"男"，0为"女"
        return new WriteCellData<>(value == 1 ? "男" : "女");
    }
}