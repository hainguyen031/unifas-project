package com.unifasservice.dto.payload.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> content;

    private int pageSize;

    private int totalPages;

    private boolean hasNext;

    private boolean hasPrevious;

    private long totalElements;

    private int currentPageNumber;
}
