package com.relatospapel.books.controller;

import com.relatospapel.books.common.LogService;
import com.relatospapel.books.dto.request.BookCreateRequest;
import com.relatospapel.books.dto.request.StockUpdateRequest;
import com.relatospapel.books.dto.response.BookResponseRecord;
import com.relatospapel.books.entity.Book;
import com.relatospapel.books.mapper.Mapper;
import com.relatospapel.books.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Controlador de libros",
        description = "Todas las operaciones CRUD del servicio libro y muchas API para que las utilicen otros microservicios")
@Slf4j
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    private Mapper mapper;

    @Autowired
    LogService logService;

    @Operation(summary = "Agregar nuevo libro", description = "Agregar nuevo libro")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Transaccion exitosa")})
    @PostMapping
    public ResponseEntity<BookResponseRecord> addBook(@RequestBody BookCreateRequest request){

        log.info("Inicia proceso para agregar un libro...");
        logService.logAsJson(request);
        Book book = mapper.bookCreateRequestToBook(request);
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);

    }

    @Operation(summary = "Buscar un libro segun su id", description = "Buscar un libro segun su id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Transaccion exitosa")})
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseRecord> findBookById(@Parameter(description = "ID del libro a recuperar",
            required = true) @PathVariable int id) {

        log.info("Obtener un libro segun si id: {}",id);
        return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);

    }

    @Operation(summary = "Obtener todos los libros", description = "Obtener todos los libros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Transaccion exitosa")})
    @GetMapping
    public ResponseEntity<List<BookResponseRecord>> findAllBook() {

        log.info("Obtener todos los libros regitrados...");
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);

    }

    @Operation(summary = "Eliminar un libro", description = "Eliminar un libro")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Transaccion exitosa")})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBookById(@PathVariable int id) {
        log.info("Proceso para eliminar un libro con el id: {}",id);
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Editar un libro", description = "Permite editar un libro")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseRecord> updateBook(@RequestBody BookCreateRequest req,
                                              @Parameter(description = "ID del libro a actualizar",
                                                      required = true) @PathVariable int id) {
        return new ResponseEntity<>(bookService.updateBook(req, id), HttpStatus.OK);
    }

    @Operation(summary = "Actualizar el stock del libro", description = "Actualizar el stock del libro")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @PostMapping("/{id}")
    public ResponseEntity<BookResponseRecord> updateBookQuantity(
            @PathVariable int id,
            @RequestBody StockUpdateRequest request
    ) {
        log.info("Actualizar la cantidad del libro, dependiendo si es venta/compra : {}",request.operation().name());
        return ResponseEntity.ok(bookService.updateStock(id, request));
    }

}
