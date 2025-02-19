package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.News;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shentong
 * @date 2023/11/9 9:29
 * @description
 */
@Transactional
public interface HotNewsService {

    public List<News> catchNews();
}
