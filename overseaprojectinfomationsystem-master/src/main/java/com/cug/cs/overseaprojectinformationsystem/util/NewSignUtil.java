package com.cug.cs.overseaprojectinformationsystem.util;

/**
 * @ClassName NewSignUtil
 * @Description TODO 新签市场类型转化
 * @Author Zhangz
 * @Date 2023/9/21 10:41
 * @Version 1.0
 **/
public class NewSignUtil {

    public static String toInt(Integer integration){
        if(integration==1){return "是";}
        else {return "否";}
    }

    public static String toPstm(Integer pstm){
        if(pstm==1){return "是";}
        else {return "否";}
    }
}
