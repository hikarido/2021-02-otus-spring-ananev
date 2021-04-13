package ru.otus.homework.dao;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations operations;
    private final BookRowMapper mapper;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoJdbc(NamedParameterJdbcOperations operations, AuthorDao authorDao, GenreDao genreDao) {
        this.operations = operations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.mapper = new BookRowMapper();
    }

    @Override
    public Book create(Book newBook) {
        if (authorDao.read(newBook.getAuthor().getId()).isEmpty()) {
            authorDao.create(newBook.getAuthor());
        }
        if (genreDao.read(newBook.getGenre().getId()).isEmpty()) {
            genreDao.create(newBook.getGenre());
        }
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", newBook.getId())
                .addValue("author_id", newBook.getAuthor().getId())
                .addValue("genre_id", newBook.getGenre().getId())
                .addValue("title", newBook.getTitle());
        operations.update(
                "insert into book (`id`, `author_id`, `genre_id`, `title`) values (:id, :author_id, :genre_id, :title)",
                params
        );
        return operations.queryForObject(
                "select `id`, `author_id`, `genre_id`, `title` from book where `id`=:id",
                params,
                mapper
        );
    }

    @Override
    public Optional<Book> read(long id) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        try {
            return Optional.ofNullable(
                    operations.queryForObject(
                            "select `id`, `author_id`, `genre_id`, `title` from book where `id` = :id",
                            params,
                            mapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Book> update(Book book) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("genre_id", book.getGenre().getId());

        operations.update(
                "update book set `author_id`=:author_id, `genre_id`=:genre_id, `title`=:title where `id`=:id",
                params
        );
        authorDao.update(book.getAuthor());
        genreDao.update(book.getGenre());
        return read(book.getId());
    }

    @Override
    public boolean delete(Book book) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", book.getId());
        final int deleted = operations.update("delete from book where `id`=:id", params);
        return deleted > 0;
    }

    @Override
    public Collection<Book> findAll() {
        return operations.query("select `id`, `author_id`, `genre_id`, `title` from book", mapper);
    }

    @Override
    public int count() {
        return operations.getJdbcOperations().queryForObject("select count(`id`) from book", Integer.class);
    }

    private class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Optional<Author> author = authorDao.read(rs.getLong("author_id"));
            final Optional<Genre> genre = genreDao.read(rs.getLong("genre_id"));
            if (author.isPresent() & genre.isPresent()) {
                return new Book(rs.getLong("id"), author.get(), genre.get(), rs.getString("title"));
            }
            throw new DataRetrievalFailureException(
                    String.format("For book.id=%d absence author or genre.", rs.getLong("id")));
        }
    }
}
