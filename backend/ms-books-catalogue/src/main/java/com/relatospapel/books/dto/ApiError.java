package com.relatospapel.books.dto;

import java.time.Instant;

public record ApiError(
        String message,
        int status,
        String path,
        Instant timestamp
) {
}
