package com.relatospapel.bookspayments.dto;

import java.math.BigDecimal;

public record OrderDetailDto(
        int bookId,
        Integer quantity
) {
}
