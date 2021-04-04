package ru.otus.homework.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookDaoJdbc implements BookDao {
    public static final String genreIdKey = "genre_id";
    public static final String titleKey = "title";
    private final static String idKey = "id";
    private static final String authorIdKey = "author_id";
    private static final String tableName = "book";
    private final NamedParameterJdbcOperations operations;
    private final BookRowMapper mapper;

    public BookDaoJdbc(NamedParameterJdbcOperations operations) {
        this.operations = operations;
        this.mapper = new BookRowMapper();
    }

    @Override
    public Book create(Book newBook) {
        Map<String, Object> params = Map.ofEntries(
                Map.entry(idKey, newBook.getId()),
                Map.entry(authorIdKey, newBook.getAuthorId()),
                Map.entry(genreIdKey, newBook.getGenreId()),
                Map.entry(titleKey, newBook.getTitle())
        );
        final String insertQuery = String.format(
                "insert into %5$s (%1$s, %2$s, %3$s, %4$s) values (:%1$s, :%2$s, :%3$s, :%4$s)",
                idKey, authorIdKey, genreIdKey, titleKey, tableName);
        operations.update(insertQuery, params);

        final String selectQuesry = String.format("select * from %1$s where  %2$s=:%2$s", tableName, idKey);
        return operations.queryForObject(selectQuesry, Map.of(idKey, newBook.getId()), mapper);
    }

    @Override
    public Optional<Book> read(long id) {
        final String selectQuery = String.format("select * from %1$s where  %2$s=:%2$s", tableName, idKey);
        final Map<String, ?> params = Map.of(idKey, id);
        final List<Book> books = operations.query(selectQuery, params, mapper);
        if (books.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(books.get(0));
    }

    @Override
    public Optional<Book> update(Book book) {
        final String updateQuery = String.format(
                "update %1%s set %2$s=:%2$s, %3$s=:%3$s, %4$s=:%4$s, %5$s=:%5$s",
                tableName, idKey, authorIdKey, genreIdKey, titleKey);
        Map<String, ?> params = Map.ofEntries(
                Map.entry(idKey, book.getId()),
                Map.entry(authorIdKey, book.getAuthorId()),
                Map.entry(genreIdKey, book.getGenreId()),
                Map.entry(titleKey, book.getTitle())
        );

        operations.update(updateQuery, params);

        return this.read(book.getId());
    }

    @Override
    public boolean delete(Book book) {
        final String deleteQuery = String.format("delete from %1$s where %2$s=:%2$s", tableName, idKey);
        final Map<String, ?> params = Map.of(idKey, book.getId());
        final int deleteCount = operations.update(deleteQuery, params);
        return deleteCount > 0;
    }

    @Override
    public Iterable<Book> findAll() {
        final String selectQuery = String.format("select * from %s", tableName);
        return operations.query(selectQuery, mapper);
    }

    @Override
    public int count() {
        final String query = String.format("select count(%1$s) from %2$s", idKey, tableName);
        return operations.getJdbcOperations().queryForObject(query, Integer.class);
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(
                    rs.getLong(idKey),
                    rs.getLong(authorIdKey),
                    rs.getLong(genreIdKey),
                    rs.getString(titleKey)
            );
        }
    }
}
