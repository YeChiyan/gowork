package com.contractdemo.service;

import com.contractdemo.entity.BranchRank;
import com.contractdemo.mapper.SalesRecordSummaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import com.contractdemo.entity.SalesRecordSummary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RankingService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private SalesRecordSummaryMapper salesRecordSummaryMapper;

    // 每日凌晨1点执行
    @Scheduled(cron = "0 0 1 * * ?")
    public void updateDailyRanking() {
        String today = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        String expiredDay = LocalDate.now().minusDays(30).format(DateTimeFormatter.ISO_DATE);

        // 1. 使用MyBatis Plus查询数据
        List<SalesRecordSummary> summaries = salesRecordSummaryMapper.findDailySummary();
        
        summaries.forEach(summary -> {
            // 2. 更新当日ZSet
            redisTemplate.opsForZSet().add("rank:amount:daily:" + today, summary.getBranchId(), summary.getTotalAmount());
            redisTemplate.opsForZSet().add("rank:count:daily:" + today, summary.getBranchId(), summary.getOrderCount());

            // 3. 更新30天累计榜
            redisTemplate.opsForZSet().incrementScore("rank:amount:30days", summary.getBranchId(), summary.getTotalAmount());
            redisTemplate.opsForZSet().incrementScore("rank:count:30days", summary.getBranchId(), summary.getOrderCount());
        });

        // 4. 删除过期数据
        redisTemplate.delete("rank:amount:daily:" + expiredDay);
        redisTemplate.delete("rank:count:daily:" + expiredDay);
    }

    // 获取排行榜数据
    public List<BranchRank> getAmountRanking(int days, int topN) {
        String key = days == 1 ?
                "rank:amount:daily:" + LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE) :
                "rank:amount:30days";

        return getRanking(key, topN);
    }

    private List<BranchRank> getRanking(String key, int topN) {
        Set<ZSetOperations.TypedTuple<String>> tuples =
                redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, topN - 1);

        return tuples.stream().map(tuple -> {
            // 将计数值转换为Long类型
            Double countScore = redisTemplate.opsForZSet().score("rank:count:30days", tuple.getValue());
            Long orderCount = countScore != null ? countScore.longValue() : 0L;

            return new BranchRank(
                    tuple.getValue(),
                    tuple.getScore(),  // 金额保持Double类型
                    orderCount         // 转换为Long类型的订单数
            );
        }).collect(Collectors.toList());
    }

    // 排行榜VO

}

