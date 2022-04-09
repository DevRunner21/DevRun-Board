package com.devrun.backend.common.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageResponse<T> {

    private final long totalCount;

    private final int pageNo;

    private final int pageSize;

    private final List<T> list;

    private final boolean hasNext;

    @Builder
    public PageResponse(long totalCount, int pageNo, int pageSize, List<T> list, boolean hasNext) {
        this.totalCount = totalCount;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.list = list;
        this.hasNext = hasNext;
    }

    public static PageResponse of(Page page) {
        return PageResponse.builder()
            .pageNo(page.getNumber())
            .totalCount(page.getTotalElements())
            .pageSize(page.getSize())
            .hasNext(page.hasNext())
            .list(page.getContent())
            .build();
    }

}
