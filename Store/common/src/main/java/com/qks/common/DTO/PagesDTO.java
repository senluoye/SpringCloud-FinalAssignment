package com.qks.common.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PagesDTO<T> {
    private Integer totalPages;
    private Integer nowPage;
    private List<T> data;

    public PagesDTO(Integer totalPages, Integer nowPage, List<T> data) {
        this.totalPages = totalPages;
        this.nowPage = nowPage;
        this.data = data;
    }
}
