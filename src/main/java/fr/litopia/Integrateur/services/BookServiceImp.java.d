/*package fr.litopia.Integrateur.services;

import fr.litopia.Integrateur.model.Book;
import fr.litopia.Integrateur.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<Object> findById(long id) {
        return Optional.of(bookRepository.findById(id));
    }

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Book book) {
        entityManager.persist(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        entityManager.merge(book);
    }
}
*/