package com.relatospapel.books.dto.request;

public record BookCreateRequest(
        String title,
        java.time.LocalDate datePublication,
        String isbn,
        java.math.BigDecimal price,
        Integer stock,
        String description,
        Integer assessment,
        Boolean visibility,
        Integer editorialId,
        Integer authorId

) {
}
