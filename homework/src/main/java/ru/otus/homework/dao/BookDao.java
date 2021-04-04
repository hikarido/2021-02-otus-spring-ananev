package ru.otus.homework.dao;

import ru.otus.homework.domain.Book;

import java.util.Optional;

public interface BookDao {
    /**
     * Persists new Book
     * @param newBook Book to add
     * @return newly persisted Book
     */
    Book create(Book newBook);

    /**
     * reads book by book.id
     * @param  id which exists in backend
     * @return retrieved Book if such id presence in backend or empty Optional in other case
     */
    Optional<Book> read(long id);

    /**
     * updates book by books id
     * @param book book which contains data for update
     * @return updated book if such Book.id presence in backend or empty optional in other case
     */
    Optional<Book> update(Book book);

    /**
     * deletes book by books.id
     * @return true if deletion passed correctly, false if there is no such book.id
     */
    boolean delete(Book book);

    /**
     * reads all books from underlying storage
     * @return returns one or more books or no books at all if they are not presence in storage
     */
    Iterable<Book> findAll();

    /**
     * @return amount of entities of Book in underlying storage
     */
    int count();
}
