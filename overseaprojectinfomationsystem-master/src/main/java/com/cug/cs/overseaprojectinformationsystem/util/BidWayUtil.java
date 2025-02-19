package com.cug.cs.overseaprojectinformationsystem.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName BidWayUtil
 * @Description 投标市场数据转化
 * @Author Zhangz
 * @Date 2023/9/21 10:16
 * @Version 1.0
 **/
public class BidWayUtil {
    public static String toType(String type){
        String Type = null;
        if (type.contains("1")){Type += "处理";}
        if (type.contains("2")){Type += "+"+ "解释";}
        if (type.contains("3")){Type += "+"+  "油藏";}
        if (type.contains("4")){Type += "+"+  "科研";}
        if (type.contains("5")){Type += "+"+  "服务";}
        return Type;

    }
    public static String toMethod(Integer method){
        if (method==0){return "投标";}
        else if (method==1){return "议标";}
        else {
            return "其他，请补充";
        }
    }

    public static String toDynamics(String D){
        if (D.contains("0")){return "中标";}
        else if (D.contains("1")){return "落标";}
        else if (D.contains("2")){return "废标";}
        else if (D.contains("3")){return "弃标";}
        else if (D.contains("4")){return "评标";}
        else {
            return "其他，请补充";
        }
    }
    // 输出数据解决空指针异常
    public static String toSupport(Integer sup){
        if (sup == null || StringUtils.isEmpty(sup.toString())) {
            return "否";
        }
        if(sup==1){return "是";}
        else {return "否";}
    }
    public static String toInt(Integer integration){
        if(integration==1){return "是";}
        else {return "否";}
    }
}
