package com.contractdemo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.contractdemo.anno.LogTimeAnno;
import com.contractdemo.entity.IncomeContract;
import com.contractdemo.entity.Member;
import com.contractdemo.mapper.IncomeContractMapper;
import com.contractdemo.mapper.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Time;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/easyExcel")
public class EasyExcelController {
    private static final Logger log = LoggerFactory.getLogger(EasyExcelController.class);

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private IncomeContractMapper contractMapper;

    /**
     * 第三种方案，线程池写在多个 sheet 里
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/exportMemberList", method = RequestMethod.GET)
    @LogTimeAnno
    public void exportMemberList(HttpServletResponse response) throws IOException, InterruptedException {
        setExcelRespProp(response, "会员列表");
        
        // 动态计算分页参数
        long total = memberMapper.selectTotalCount();
        int targetSheets = 10; // 固定10个sheet
        long pageSize = (total + targetSheets - 1) / targetSheets; // 确保分页正确

        // 配置线程池（根据CPU核心数优化）
        int corePoolSize = Math.max(4, Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                corePoolSize * 2,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                new CustomThreadFactory("export-"),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        ConcurrentHashMap<Integer, List<Member>> dataMap = new ConcurrentHashMap<>(targetSheets);
        CountDownLatch latch = new CountDownLatch(targetSheets);

        try {
            // 提交分页查询任务
            for (int sheetIndex = 0; sheetIndex < targetSheets; sheetIndex++) {
                final int pageNum = sheetIndex + 1;
                final long offset = sheetIndex * pageSize;
                
                executor.submit(() -> {
                    try {
                        // 边界检查
                        if (offset >= total) {
                            log.info("跳过空页：第{}页 offset={}", pageNum, offset);
                            return;
                        }

                        List<Member> records = memberMapper.selectPageWithCustomLimit(offset, pageSize);
                        if (!records.isEmpty()) {
                            dataMap.put(sheetIndex, records);
                            log.info("第{}页查询成功 → 起始ID：{} 数量：{}", 
                                pageNum, records.get(0).getId(), records.size());
                        }
                    } catch (Exception e) {
                        log.error("第{}页查询失败 offset={}", pageNum, offset, e);
                    } finally {
                        latch.countDown();
                    }
                });
            }

            // 等待所有任务完成（含超时控制）
            if (!latch.await(10, TimeUnit.MINUTES)) {
                log.error("分页查询超时，已完成页数：{}/{}", dataMap.size(), targetSheets);
            }
        } finally {
            executor.shutdownNow();
        }

        // 确保生成10个sheet（含空页处理）
        try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), Member.class)
                .registerWriteHandler(new NoStyleWriteHandler()) // 禁用样式提升性能
                .build()) {
            
            for (int sheetIndex = 0; sheetIndex < targetSheets; sheetIndex++) {
                List<Member> data = dataMap.getOrDefault(sheetIndex, Collections.emptyList());
                String sheetName = String.format("数据分页%02d", sheetIndex+1);
                
                WriteSheet sheet = EasyExcel.writerSheet(sheetIndex, sheetName)
                        .needHead(sheetIndex == 0) // 仅第一页保留表头
                        .build();

                excelWriter.write(data, sheet);
                log.info("Sheet[{}]写入完成 → {}", sheetIndex+1, data.isEmpty() ? "空页" : data.size() + "条");
            }
        }
    }

    // 自定义线程工厂
    static class CustomThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CustomThreadFactory(String prefix) {
            namePrefix = prefix;
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
            t.setDaemon(true);
            t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    @RequestMapping(value = "/exportMemberList2", method = RequestMethod.GET)
    @LogTimeAnno
    public void exportMemberList2(HttpServletResponse response) throws IOException {
        setExcelRespProp(response, "会员列表");
        LambdaQueryWrapper<Member> lqw = new LambdaQueryWrapper<>();
        lqw.last("LIMIT 1000000");

        List<Member> members = memberMapper.selectList(lqw);
        ExcelWriter excelWriter = null;

        try {
            excelWriter = EasyExcel.write(response.getOutputStream(), Member.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .build();

            // 创建3个sheet（索引从0开始）
            WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "模板1").build();
            WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "模板2").build();
            WriteSheet writeSheet3 = EasyExcel.writerSheet(2, "模板3").build();

            // 安全分割数据
            int total = members.size();
            int partition = (total + 2) / 3; // 向上取整分片

            List<Member> data1 = members.subList(0, Math.min(partition, total));
            List<Member> data2 = members.subList(Math.min(partition, total), Math.min(partition*2, total));
            List<Member> data3 = members.subList(Math.min(partition*2, total), total);

            // 写入数据
            excelWriter.write(data1, writeSheet1);
            excelWriter.write(data2, writeSheet2);
            excelWriter.write(data3, writeSheet3);
            log.info("正在写入第1页，数据量：{}", data1.size());
            log.info("正在写入第2页，数据量：{}", data2.size());
            log.info("正在写入第3页，数据量：{}", data3.size());
        } finally {
            // 关闭资源
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 第一种方案，查出来之后写在一个 sheet里
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/exportMemberList0", method = RequestMethod.GET)
    @LogTimeAnno
    public void exportMemberList0(HttpServletResponse response) throws IOException {
        setExcelRespProp(response, "会员列表");
//        List<Member> memberList = LocalJsonUtil.getListFromJson("json/members.json", Member.class);
//        // **手动创建数据**
//        List<Member> memberList = new ArrayList<>();
//        memberList.add(new Member(1L, "张三", 25));
//        memberList.add(new Member(2L, "李四", 30));
        LambdaQueryWrapper<Member> lqw = new LambdaQueryWrapper<>();
        lqw.last("LIMIT 500000");

        List<Member> members = memberMapper.selectList(lqw);
//        List<IncomeContract> contracts = contractMapper.selectList(null);



        EasyExcel.write(response.getOutputStream())
                .head(Member.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("会员列表")
                .doWrite(members);
    }

    /**
     * 设置 Excel 下载响应头
     */
    private void setExcelRespProp(HttpServletResponse response, String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }
}

// 无样式写入处理器（提升性能）
public class NoStyleWriteHandler extends AbstractCellWriteHandler {
    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, 
            List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        // 禁用所有样式处理
    }
}