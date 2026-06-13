package com.relatospapel.books.dto.request;

import com.relatospapel.books.dto.enums.StockOperation;

public record StockUpdateRequest(
        Integer quantity,
        StockOperation operation
) {
}
