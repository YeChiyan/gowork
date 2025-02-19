package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

/**
 * @author shentong
 * @date 2023/11/6 15:01
 * @description
 */
public class News {
    private String title;
    private String href;

    public News(String title, String href) {
        this.title = title;
        this.href = href;
    }

    public News() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
