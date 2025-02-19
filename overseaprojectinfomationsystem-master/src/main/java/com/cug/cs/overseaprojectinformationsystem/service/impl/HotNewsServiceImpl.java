package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.News;
import com.cug.cs.overseaprojectinformationsystem.service.HotNewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shentong
 * @date 2023/11/9 9:31
 * @description
 */

@Service
public class HotNewsServiceImpl implements HotNewsService {
    @Override
    public List<News> catchNews() {
        List<News> newsList = new ArrayList<>();
        // 石油新闻(国际新闻网）
        String url1 = "https://oil.in-en.com/news/";
        //中国石油网
        String url2 = "http://center.cnpc.com.cn/bk/1/";

        Document document1 = null;
        try {
            document1 = Jsoup.connect(url1).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document2 = null;
        try {
            document2 = Jsoup.connect(url2).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 文字新闻
        Elements texts1 = document1.getElementsByClass("hotList");
        Elements texts2 = document2.getElementsByClass("hot-content");
        for (Element e : texts1) {
            Elements tags = e.getElementsByTag("a");
            if (tags.size() > 5) {
                for (int i = 0; i < 5; i++) {
                    Element tag = tags.get(i);
                    // 标题
                    String title = tag.getElementsByAttribute("href").text();
                    // 链接地址，可以根据需求继续解析网址，获取新闻详细信息
                    String href = tag.attributes().get("href");
                    // 所属分类
                    /*String classification = null;
                    if (href.contains("?") && href.contains("clickfrom=w_")) {
                        classification = href.substring(href.lastIndexOf("?") + 1).replace("clickfrom=w_", "");
                    }*/
                    News news = new News();
                    news.setTitle(title);
                    news.setHref(href);
                    newsList.add(news);
                }
            }

        }
        for (Element e : texts2) {
            Elements tags = e.getElementsByTag("a");
            if (tags.size() > 5) {
                for (int i = 0; i <= 5; i++) {
                    Element tag = tags.get(i);
                    // 标题
                    String title = tag.getElementsByAttribute("href").text();
                    // 链接地址，可以根据需求继续解析网址，获取新闻详细信息
                    String href = tag.attributes().get("href");
                    News news = new News();
                    if (href != null && title != null && !title.equals("") && !href.equals("")) {
                        news.setTitle(title);
                        news.setHref(href);
                        newsList.add(news);
                    }
                }
            }

        }
        return newsList;
    }
}
