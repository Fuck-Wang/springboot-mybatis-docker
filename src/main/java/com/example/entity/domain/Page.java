package com.example.entity.domain;

import com.example.entity.exception.BadRequestException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

    /*页码*/
    private int pageNum;
    /*页面大小*/
    private int pageSize;
    /*总元素数量*/
    private int totalElements;
    /*总页数*/
    private int totalPages;
    /*当前页内容列表*/
    private List<T> content = new ArrayList<>();
    /*是否有下一页*/
    public boolean hasNext() {
        return pageNum < totalPages;
    }
    /*是否最后一页*/
    public boolean isLast() {
        return !hasNext();
    }

    public int getPageNum() {
        return pageNum;
    }

    public Page<T> setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Page<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public Page<T> setTotalElements(int totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public Page<T> setTotalPages(int totalPages) {
        if (this.pageNum > totalPages) {
            throw new BadRequestException(400, "Page", "没有更多页数了!");
        }
        this.totalPages = totalPages;
        return this;
    }

    public List<T> getContent() {
        return content;
    }

    public Page<T> setContent(List<T> content) {
        this.content = content;
        return this;
    }
}
