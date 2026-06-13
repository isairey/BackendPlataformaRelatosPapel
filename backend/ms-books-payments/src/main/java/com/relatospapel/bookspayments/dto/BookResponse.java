package com.relatospapel.bookspayments.dto;

public record BookResponse(
        Long idBook,
        String title,
        java.time.LocalDate datePublication,
        String isbn,
        java.math.BigDecimal price,
        int stock,
        String description,
        Boolean visible
) {
}
