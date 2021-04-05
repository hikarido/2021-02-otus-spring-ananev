package ru.otus.homework.tui;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;

import java.util.NoSuchElementException;
import java.util.Optional;

@ShellComponent
public class ShellTui {

    BookDao bookDao;
    AuthorDao authorDao;
    GenreDao genreDao;

    public ShellTui(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @ShellMethod(value = "Display all rows of special table", key = {"show"})
    public String showTable(@ShellOption({"-n", "--tableName"}) String tableName) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%n"));
        switch (tableName) {
            case "book":
                builder.append(toLines(bookDao.findAll()));
                break;
            case "author":
                builder.append(toLines(authorDao.readAll()));
                break;
            case "genre":
                builder.append(toLines(genreDao.readAll()));
                break;
            default:
                return "There is no such table. Tables names are {book, author, genre}";
        }
        return builder.toString();
    }

    @ShellMethod(value = "Display all tables in system", key = {"ls"})
    String ls() {
        StringBuilder builder = new StringBuilder();
        final String newLine = String.format("%n");
        builder.append(newLine);
        builder.append(toLines(bookDao.findAll()));
        builder.append(newLine);
        builder.append(toLines(authorDao.readAll()));
        builder.append(newLine);
        builder.append(toLines(genreDao.readAll()));
        builder.append(newLine);
        return builder.toString();
    }

    @ShellMethod(value = "Add new book", key = {"addBook"})
    String addTestBook(
            @ShellOption({"--book-name"}) String bookName,
            @ShellOption({"--author-id"}) int authorId,
            @ShellOption({"--genre-id"}) int genreId
    ) {
        final Author author = authorDao.read(authorId)
                .orElseThrow(() -> new NoSuchElementException("There is no such author id"));
        final Genre genre = genreDao.read(genreId)
                .orElseThrow(() -> new NoSuchElementException("There is not such genre id"));
        final Book book = new Book(getNextBookId(), author, genre, bookName);
        bookDao.create(book);
        return String.format("%nok");
    }

    @ShellMethod(value = "Remove book with certain id", key = {"rm"})
    String removeBook(@ShellOption({"--id"}) long id){
        final boolean deleted = bookDao.delete(new Book(id, null, null, ""));
        return deleted ? String.format("%nok"): String.format("%nthere is no such id");
    }

    @ShellMethod(value = "Update book", key = {"updateBook"})
    String updateBook(
            @ShellOption({"--book-id"}) long bookId,
            @ShellOption({"--book-name"}) String bookName,
            @ShellOption({"--author-id"}) long authorId,
            @ShellOption({"--genre-id"}) long genreId
    ) {
        final Book oldBook = bookDao.read(bookId)
                .orElseThrow(() -> new NoSuchElementException("There is no boot with such id"));
        final Author author = authorDao.read(authorId)
                .orElseThrow(() -> new NoSuchElementException("There is no such author id"));
        final Genre genre = genreDao.read(genreId)
                .orElseThrow(() -> new NoSuchElementException("There is not such genre id"));
        final Book refreshedBook = new Book(oldBook.getId(), author, genre, bookName);
        if(bookDao.update(refreshedBook).isEmpty()){
            return String.format("%nupdate failed");
        }
        return String.format("%nupdate ok");

    }

    private String toLines(Iterable<?> objects) {
        final StringBuilder builder = new StringBuilder();
        final String newLine = String.format("%n");
        for (Object o : objects) {
            builder.append(o.toString());
            builder.append(newLine);
        }
        return builder.toString();
    }

    private long getNextBookId() {
        final Optional<Book> maybeBook = bookDao
                .findAll()
                .stream()
                .max((a, b) -> (int) (a.getId() - b.getId()));
        final Book bookWithMaximumId = maybeBook.
                orElse(new Book(0L, null, null, ""));
        return bookWithMaximumId.getId() + 1;
    }
}
