package com.classroom.backend.pageable;

import lombok.Builder;
import org.springframework.boot.context.properties.bind.Name;

@Builder
public record PageableRequest(
    @Name("name")
    String searched,
    int pageNumber,
    int pageSize,
    String sortField,
    @Name("sortDir")
    String sortDirection
) {
}
