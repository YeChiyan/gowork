package com.cug.cs.overseaprojectinformationsystem.util;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

/**
 * @ClassName ExcelUtil
 * @Description xlsx表单输出
 * @Author Zhangz
 * @Date 2023/9/20 15:45
 * @Version 1.0
 **/
public class ExcelUtil {
    //xlsx表单输出
    /**
     *
     * * 获取文档
     * @param sheetname 表单名
     * @param title 标题栏
     * @param content0 市场营销内容
     * @param content1 技术宣讲内容
     * @param content2 潜在项目内容
     * @param content3 投标统计内容
     * @param content4 新签市场内容
     * @return
     */
    public static XSSFWorkbook getWorkbook (String[] sheetname, String[][] title, String[][] content0,
    String[][] content1,String[][] content2,String[][] content3,String[][] content4) {
        //新建文档实例
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建单元格格式，并设置居中
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        XSSFCellStyle style1 = workbook.createCellStyle();
        style1.setFont(font);
        style1.setAlignment(HorizontalAlignment.CENTER);


        /*
        市场营销内容录入
         */
        //在文档中添加表单
        XSSFSheet sheet = workbook.createSheet(sheetname[0]);
        //创建第一行，用于填充标题
        XSSFRow titleRow = sheet.createRow(0);
        //填充标题
        for (int i=0 ; i<title[0].length ; i++) {
            //创建单元格
            XSSFCell cell = titleRow.createCell(i);
            //设置单元格内容
            cell.setCellValue(title[0][i]);
            //设置单元格样式
            cell.setCellStyle(style1);
        }
        //填充内容
        for (int i=0 ; i<content0.length ; i++) {
            //创建行
            XSSFRow row = sheet.createRow(i+1);
            //遍历某一行
            for (int j=0 ; j<content0[i].length ; j++) {
                //创建单元格
                XSSFCell cell = row.createCell(j);
                //设置单元格内容
                cell.setCellValue(content0[i][j]);
                //设置单元格样式
                cell.setCellStyle(style);
            }
        }

        /*
        技术宣讲内容录入
         */
        //在文档中添加表单
        sheet = workbook.createSheet(sheetname[1]);
        //创建第一行，用于填充标题
        titleRow = sheet.createRow(0);
        //填充标题
        for (int i=0 ; i<title[1].length ; i++) {
            //创建单元格
            XSSFCell cell = titleRow.createCell(i);
            //设置单元格内容
            cell.setCellValue(title[1][i]);
            //设置单元格样式
            cell.setCellStyle(style1);
        }
        //填充内容
        for (int i=0 ; i<content1.length ; i++) {
            //创建行
            XSSFRow row = sheet.createRow(i+1);
            //遍历某一行
            for (int j=0 ; j<content1[i].length ; j++) {
                //创建单元格
                XSSFCell cell = row.createCell(j);
                //设置单元格内容
                cell.setCellValue(content1[i][j]);
                //设置单元格样式
                cell.setCellStyle(style);
            }
        }

        /*
        潜在项目内容录入
         */
        //在文档中添加表单
        sheet = workbook.createSheet(sheetname[2]);
        //创建第一行，用于填充标题
        titleRow = sheet.createRow(0);
        //填充标题
        for (int i=0 ; i<title[2].length ; i++) {
            //创建单元格
            XSSFCell cell = titleRow.createCell(i);
            //设置单元格内容
            cell.setCellValue(title[2][i]);
            //设置单元格样式
            cell.setCellStyle(style1);
        }
        //填充内容
        for (int i=0 ; i<content2.length ; i++) {
            //创建行
            XSSFRow row = sheet.createRow(i+1);
            //遍历某一行
            for (int j=0 ; j<content2[i].length ; j++) {
                //创建单元格
                XSSFCell cell = row.createCell(j);
                //设置单元格内容
                cell.setCellValue(content2[i][j]);
                //设置单元格样式
                cell.setCellStyle(style);
            }
        }

        /*
        投标统计内容录入
         */
        //在文档中添加表单
        sheet = workbook.createSheet(sheetname[3]);
        //创建第一行，用于填充标题
        titleRow = sheet.createRow(0);
        //填充标题
        for (int i=0 ; i<title[3].length ; i++) {
            //创建单元格
            XSSFCell cell = titleRow.createCell(i);
            //设置单元格内容
            cell.setCellValue(title[3][i]);
            //设置单元格样式
            cell.setCellStyle(style1);
        }
        //填充内容
        for (int i=0 ; i<content3.length ; i++) {
            //创建行
            XSSFRow row = sheet.createRow(i+1);
            //遍历某一行
            for (int j=0 ; j<content3[i].length ; j++) {
                //创建单元格
                XSSFCell cell = row.createCell(j);
                //设置单元格内容
                cell.setCellValue(content3[i][j]);
                //设置单元格样式
                cell.setCellStyle(style);
            }
        }

        /*
        新签市场内容录入
         */
        //在文档中添加表单
        sheet = workbook.createSheet(sheetname[4]);
        //创建第一行，用于填充标题
        titleRow = sheet.createRow(0);
        //填充标题
        for (int i=0 ; i<title[4].length ; i++) {
            //创建单元格
            XSSFCell cell = titleRow.createCell(i);
            //设置单元格内容
            cell.setCellValue(title[4][i]);
            //设置单元格样式
            cell.setCellStyle(style1);
        }
        //填充内容
        for (int i=0 ; i<content4.length ; i++) {
            //创建行
            XSSFRow row = sheet.createRow(i+1);
            //遍历某一行
            for (int j=0 ; j<content4[i].length ; j++) {
                //创建单元格
                XSSFCell cell = row.createCell(j);
                //设置单元格内容
                cell.setCellValue(content4[i][j]);
                //设置单元格样式
                cell.setCellStyle(style);
            }
        }

        //返回文档实例
        return workbook;
    }

//    数据表格输出（单个表输出）
    public static XSSFWorkbook getWorkbook
    (String[] sheetname, String[] title, String[][] content,Boolean flag) {
        //新建文档实例
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建单元格格式，并设置居中
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中


        XSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        XSSFCellStyle rowStyle = workbook.createCellStyle();
        rowStyle.setFont(font);
        rowStyle.setAlignment(HorizontalAlignment.CENTER);
        /*
            合同管理内容录入
        */
        //在文档中添加表单
        XSSFSheet sheet = workbook.createSheet(sheetname[0]);
        //创建第一行，用于填充标题
        XSSFRow titleRow = sheet.createRow(0);
        //填充标题
        for (int i=0 ; i<title.length ; i++) {
            //创建单元格
            XSSFCell cell = titleRow.createCell(i);
            //设置单元格内容
            cell.setCellValue(title[i]);
            //设置单元格样式
            cell.setCellStyle(rowStyle);
        }
        //填充内容
        for (int i=0 ; i<content.length ; i++) {
            //创建行
            XSSFRow row = sheet.createRow(i+1);
            //遍历某一行
            for (int j=0 ; j<content[i].length ; j++) {
                //创建单元格
                XSSFCell cell = row.createCell(j);
                //设置单元格内容
                cell.setCellValue(content[i][j]);
                //设置单元格样式
                cell.setCellStyle(cellStyle);
            }
        }
        int num = title.length;
        if(!flag) {
            num = num-1;
        }
        for (int k = 0; k <num; k++) {
            sheet.autoSizeColumn(k);
            sheet.setColumnWidth(k,sheet.getColumnWidth(k)*12/10);
        }
        // 处理中文不能自动调整列宽的问题
//        setSizeColumn(sheet,title.length);
        //返回文档实例
        return workbook;
    }
}
