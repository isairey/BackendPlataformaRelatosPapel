package com.relatospapel.books.service;

import com.relatospapel.books.dto.request.BookCreateRequest;
import com.relatospapel.books.dto.request.StockUpdateRequest;
import com.relatospapel.books.dto.response.BookResponseRecord;
import com.relatospapel.books.entity.Book;

import java.util.List;

public interface BookService {

    BookResponseRecord addBook(Book book);

    List<BookResponseRecord> findAllBooks();

    BookResponseRecord findBookById(int id);

    void deleteBookById(int id);

    BookResponseRecord updateBook(BookCreateRequest bookCreateRequest, int id);

    BookResponseRecord updateStock(int idBook, StockUpdateRequest req);
}
