package fr.urca.bibliotheque.controllers;

import fr.urca.bibliotheque.entities.Book;
import fr.urca.bibliotheque.services.IBookImpl;
import fr.urca.bibliotheque.services.iservices.IBook;
import fr.urca.bibliotheque.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("api/v1/books")
public class BookController {
    private final IBook iBookService;

    public BookController(IBookImpl iBookService) {
        this.iBookService = iBookService;
    }

    @GetMapping()
    ResponseEntity<?> getAllBooks() {
        try {
            List<Book> bookList = iBookService.listBook();
            return bookList == null ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) : ResponseEntity.status(HttpStatus.OK).body(bookList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }

   /* @GetMapping()
    ResponseEntity<?> filterByKeyWord(@RequestParam String keyWord) {
        try {
            List<Book> bookList = iBookService.filter(keyWord);
            return bookList == null ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) : ResponseEntity.status(HttpStatus.OK).body(bookList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }*/

    @PostMapping()
    ResponseEntity<?> saveBook(@RequestBody Book bookForm) {
        try {
            Book book = iBookService.save(bookForm);
            return book == null ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) : ResponseEntity.status(HttpStatus.OK).body(book);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody Book bookForm) {
        try {
            Book book = iBookService.update(id, bookForm);
            return book == null ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) : ResponseEntity.status(HttpStatus.OK).body(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> deteteBook(@PathVariable int id) {
        try {
            Boolean delete = iBookService.delete(id);
            return delete == false ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false) : ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }
}
