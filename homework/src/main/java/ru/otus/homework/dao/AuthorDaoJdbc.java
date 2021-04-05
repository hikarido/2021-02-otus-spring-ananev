package ru.otus.homework.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations operations;
    private final AuthorRowMapper mapper;

    public AuthorDaoJdbc(NamedParameterJdbcOperations operations) {
        this.operations = operations;
        this.mapper = new AuthorRowMapper();
    }

    @Override
    public Author create(Author author) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", author.getId())
                .addValue("full_name", author.getFullName());
        operations.update("insert into author (`id`, `full_name`) values (:id, :full_name)", params);
        return operations.queryForObject("select * from author where `id`=:id", params, mapper);
    }

    @Override
    public Optional<Author> read(long id) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        try{
            return Optional.ofNullable(operations.queryForObject("select * from author where `id`=:id", params, mapper));
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Collection<Author> readAll() {
        return operations.query("select * from author", mapper);
    }

    @Override
    public void update(Author author) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", author.getId())
                .addValue("full_name", author.getFullName());
        operations.update("update author set `full_name`=:full_name where `id`=:id", params);
    }

    @Override
    public boolean delete(Author author) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", author.getId());
        final int updated = operations.update("delete from author where `id`=:id", params);
        return updated > 0;
    }

    private static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("full_name"));
        }
    }
}
