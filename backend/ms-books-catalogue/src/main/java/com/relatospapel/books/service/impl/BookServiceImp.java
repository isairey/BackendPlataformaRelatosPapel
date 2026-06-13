package com.relatospapel.books.service.impl;


import com.relatospapel.books.dto.request.BookCreateRequest;
import com.relatospapel.books.dto.request.StockUpdateRequest;
import com.relatospapel.books.dto.response.BookResponseRecord;
import com.relatospapel.books.entity.Book;
import com.relatospapel.books.exception.BookNotFoundException;
import com.relatospapel.books.exception.DuplicateResourceException;
import com.relatospapel.books.exception.InvalidDataException;
import com.relatospapel.books.mapper.Mapper;
import com.relatospapel.books.repository.BookRepository;
import com.relatospapel.books.service.BookService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public BookResponseRecord addBook(Book book) {
        if(book == null){
            throw new InvalidDataException("Book data can not be empty");
        }
        if (book.getIsbn() == null || book.getIsbn().isBlank()) {
            throw new InvalidDataException("ISBN is required");
        }
        log.info("Validar si existe el ISBN: {}", book.getIsbn());
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new DuplicateResourceException("MS-> ISBN ya existe: " + book.getIsbn());
        }

        try{
            Book saved = bookRepository.save(book);
            log.info("Se registro el libro correctamente su id: {}", saved.getIdBook());
            return mapper.bookToBookResponseRecord(saved);
        } catch (Exception e) {
            throw new DuplicateResourceException("Ex-ISBN ya existe: " + book.getIsbn());
        }

    }

    @Override
    public List<BookResponseRecord> findAllBooks() {

        return bookRepository.findAll().stream().map(book -> mapper.bookToBookResponseRecord(book)).toList();
    }

    @Override
    public BookResponseRecord findBookById(int id) {

            return mapper.bookToBookResponseRecord(bookRepository.findById(id)
                    .orElseThrow(() -> new BookNotFoundException("Book not found")));
        }

    @Override
    public void deleteBookById(int id) {

        if (id <= 0)
            throw new IllegalArgumentException("El id no puede ser 0 o negativo");

        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("No existe el libro con id: " + id);
        }

        bookRepository.deleteById(id);
    }

    @Override
    public BookResponseRecord updateBook(BookCreateRequest req, int id) {
        if(req == null)
            throw new IllegalArgumentException("BookCreateRequest es null");

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No existe el libro con id: " + id));

        boolean noChanges =
                req.title() == null &&
                        req.isbn() == null &&
                        req.assessment() == null &&
                        req.price() == null &&
                        req.stock() == null;
        if (noChanges) {
            throw new IllegalArgumentException("No hay campos para actualizar");
        }

        if(req.title() != null){
            book.setTitle(req.title());
        }
        if(req.isbn() != null){
            book.setIsbn(req.isbn());
        }
        if(req.assessment() != null){
            book.setAssessment(req.assessment());
        }
        if(req.price() != null){
            book.setPrice(req.price());
        }
        if(req.stock() != null){
            int discount = req.stock();
            if (discount <= 0) {
                throw new InvalidDataException("El stock a descontar debe ser mayor a 0");
            }
            int current = book.getStock();
            int newStock = current - discount;
            if (newStock < 0) {
                throw new InvalidDataException(
                        "Stock insuficiente. Disponible: " + current + ", solicitado: " + discount
                );
            }
            book.setStock(newStock);
        }

        Book upsateBook = bookRepository.save(book);
        return mapper.bookToBookResponseRecord(upsateBook);
    }

    @Transactional
    @Override
    public BookResponseRecord updateStock(int idBook, StockUpdateRequest req) {

        if (req == null) throw new IllegalArgumentException("StockUpdateRequest es null");
        if (req.quantity() == null) throw new InvalidDataException("quantity es requerida");
        if (req.operation() == null) throw new InvalidDataException("operation es requerida");
        if (req.quantity() <= 0) throw new InvalidDataException("quantity debe ser mayor a 0");

        Book book = bookRepository.findById(idBook)
                .orElseThrow(() -> new BookNotFoundException("No existe el libro con id: " + idBook));
        int current = book.getStock();
        int newStock = switch (req.operation()) {
            case ACQUISITION -> current + req.quantity();
            case SALE -> current - req.quantity();
        };
        if (newStock < 0) {
            throw new InvalidDataException(
                    "Stock insuficiente. Disponible: " + current + ", solicitado: " + req.quantity()
            );
        }
        book.setStock(newStock);
        Book updated = bookRepository.save(book);
        return mapper.bookToBookResponseRecord(updated);
    }
}
