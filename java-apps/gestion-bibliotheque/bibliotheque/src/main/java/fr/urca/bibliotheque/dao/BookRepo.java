package fr.urca.bibliotheque.dao;

import fr.urca.bibliotheque.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findBookByAuthorContainingIgnoreCase(String author);
    List<Book> findBookByTitleContainingIgnoreCase(String title);
}
