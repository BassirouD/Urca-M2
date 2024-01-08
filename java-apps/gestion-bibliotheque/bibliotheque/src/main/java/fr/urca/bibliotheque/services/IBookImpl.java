package fr.urca.bibliotheque.services;

import fr.urca.bibliotheque.dao.BookRepo;
import fr.urca.bibliotheque.entities.Book;
import fr.urca.bibliotheque.services.iservices.IBook;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IBookImpl implements IBook {

    private final BookRepo bookRepo;

    public IBookImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Book save(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book update(int id, Book book) throws Exception {
        if (bookRepo.existsById(id)) {
            book.setId(id);
            return bookRepo.save(book);
        } else {
            throw new Exception("Book not found with id: " + id);
        }
    }

    @Override
    public Boolean delete(int id) {
        bookRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Book> listBook() {
        return bookRepo.findAll();
    }

    @Override
    public List<Book> filter(String keyWold) {
        List<Book> bookListByAuthor = bookRepo.findBookByAuthorContainingIgnoreCase(keyWold);
        List<Book> bookListByTitle = bookRepo.findBookByTitleContainingIgnoreCase(keyWold);

        if (bookListByAuthor != null && !bookListByAuthor.isEmpty()) {
            return bookListByTitle;
        }
        if (bookListByTitle != null && !bookListByTitle.isEmpty()) {
            return bookListByTitle;
        } else {
            throw new RuntimeException("No books found");
        }
    }
}
