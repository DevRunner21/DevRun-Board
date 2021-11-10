package com.devrun.backend.common.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PageResult<T> {

    private final long totalCount;

    private final int pageNo;

    private final int pageSize;

    private final List<T> list;

    private final boolean hasNext;

    public static PageResult of(Page tPage) {
        return new PageResult(
                tPage.getTotalElements(),
                tPage.getNumber(),
                tPage.getSize(),
                tPage.getContent(),
                tPage.hasNext()
        );
    }

}
