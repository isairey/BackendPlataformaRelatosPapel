package com.relatospapel.books.mapper;

import com.relatospapel.books.dto.request.BookCreateRequest;
import com.relatospapel.books.dto.response.BookResponseRecord;
import com.relatospapel.books.entity.Author;
import com.relatospapel.books.entity.Book;
import com.relatospapel.books.entity.Editorial;
import com.relatospapel.books.repository.AuthorRepository;
import com.relatospapel.books.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    EditorialRepository editorialRepository;

    public BookResponseRecord  bookToBookResponseRecord(Book book){

        if(book == null){
            throw new NullPointerException("The Book should not be null");
        }
        return new BookResponseRecord(book.getIdBook(),book.getTitle(),
                book.getDatePublication(),book.getIsbn(),book.getPrice(),
                book.getStock(),book.getDescription(), book.getVisibility());
    }

    public Book bookCreateRequestToBook(BookCreateRequest createRequest){
        if(createRequest == null){
            throw new NullPointerException("The Book DTO should not be null");
        }
        Book book = new Book();
        book.setTitle(createRequest.title());
        book.setDatePublication(createRequest.datePublication());
        book.setIsbn(createRequest.isbn());
        book.setPrice(createRequest.price());
        book.setStock(createRequest.stock());
        book.setDescription(createRequest.description());
        book.setAssessment(createRequest.assessment());
        book.setVisibility(createRequest.visibility());
        Editorial editorial = editorialRepository.findById(createRequest.editorialId())
                .orElseThrow(() -> new IllegalArgumentException("Editorial no existe: " + createRequest.editorialId()));
        Author author = authorRepository.findById(createRequest.authorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor no exite: "+ createRequest.authorId()));
        book.setEditorial(editorial);
        book.setAuthor(author);

        return book;
    }
}
