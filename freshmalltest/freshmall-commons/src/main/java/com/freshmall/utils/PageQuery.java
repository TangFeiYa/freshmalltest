package com.freshmall.utils;

import java.util.ArrayList;
import java.util.List;

public class PageQuery {

    private Long pageIndex; //当前页码
    private Long pageSize; //页面大小
    private List<Object> queryDatas=new ArrayList<Object>(); //查询条件

    public Long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public List<Object> getQueryDatas() {
        return queryDatas;
    }

    public void setQueryDatas(List<Object> queryDatas) {
        this.queryDatas = queryDatas;
    }

    public PageQuery() {
    }

    public PageQuery(Long pageIndex, Long pageSize, Object... data) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        if(data!=null){
            for(int i=0;i<data.length;i++){
                queryDatas.add(data[i]);
            }
        }
    }
    @Override
    public String toString() {
        return "PageQuery{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", queryDatas=" + queryDatas +
                '}';
    }
}
