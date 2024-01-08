package fr.urca.bibliotheque.services.iservices;

import fr.urca.bibliotheque.entities.Book;

import java.util.List;

public interface IBook {
    Book save(Book book);

    Book update(int id, Book book) throws Exception;

    Boolean delete(int id);

    List<Book> listBook();

    List<Book> filter(String keyWold);

}
