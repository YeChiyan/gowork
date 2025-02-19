package com.cug.cs.overseaprojectinformationsystem.util;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

/**
 * @ClassName ManageExportUtil
 * @Description
 * @Author Zhangz
 * @Date 2023/11/24 10:38
 * @Version 1.0
 **/
public class ManageExportUtil {
//    处理方式
    public static String toType(Integer type){
        String Type = null;
        if (type==1){Type += "处理";}
        if (type==2){Type += "+"+ "解释";}
        if (type==3){Type += "+"+  "油藏";}
        if (type==4){Type += "+"+  "科研";}
        if (type==5){Type += "+"+  "服务";}
        return Type;
    }

    //    是否一体化
    public static String toInt(Integer integration){
        if(integration==1){return "是";}
        else {return "否";}
    }
}

