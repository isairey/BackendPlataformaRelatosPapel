package com.relatospapel.bookspayments.dto;

import com.relatospapel.bookspayments.dto.enums.StockOperation;

public record StockUpdateRequest(
        Integer quantity,
        StockOperation operation
) {
}
