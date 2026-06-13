package com.relatospapel.books.dto.response;

import java.math.BigDecimal;

public record BookResponseRecord(
        Integer idBook,
        String title,
        java.time.LocalDate datePublication,
        String isbn,
        BigDecimal price,
        int stock,
        String description,
        Boolean visible
) {
}
