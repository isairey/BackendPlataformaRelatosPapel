package com.relatospapel.bookspayments.client;

import com.relatospapel.bookspayments.dto.BookResponse;
import com.relatospapel.bookspayments.dto.StockUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "ms-books-catalogue",
        path = "/api/v1/books")
public interface BooksCatalogueClient {

    @GetMapping(path = "/{id}")
    BookResponse findBookById(@PathVariable int id);

    @PostMapping(path = "/{id}")
    BookResponse updateBookQuantity(@PathVariable int id,
                                    @RequestBody StockUpdateRequest request);

}
