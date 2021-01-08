package pl.tkaczyk.bookthymeleaf.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tkaczyk.bookthymeleaf.model.Book;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    void deleteByTytul(String tytul);

}
