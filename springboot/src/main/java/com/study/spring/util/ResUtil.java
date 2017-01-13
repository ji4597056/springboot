package com.study.spring.util;

import com.github.pagehelper.PageInfo;
import com.study.spring.entity.model.SearchResModel;

/**
 * web响应工具类
 * @author Jeffrey
 * @since 2017/01/09 16:25
 */
public class ResUtil {

    /**
     * 返回查询分页响应实体
     * @param page
     * @return
     */
    public static SearchResModel getSearchRes(PageInfo page) {
        return new SearchResModel(page.getPageNum(), page.getSize(), page.getPages(), page.getList());
    }
}
