package com.cug.cs.overseaprojectinformationsystem.util;

/**
 * @ClassName EventWayUtil
 * @Description 市场影响数据录入的信息转换
 * @Author Zhangz
 * @Date 2023/9/20 21:20
 * @Version 1.0
 **/
public class EventWayUtil {
    public static String swithWay(Integer way){
        if (way==0){return "来访";}
        else if (way==1){return "走访";}
        else if (way==2){return "线上";}
        else {
            return "其他，请补充";
        }
    }
}
