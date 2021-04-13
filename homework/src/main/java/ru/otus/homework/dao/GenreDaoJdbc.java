package ru.otus.homework.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations operations;
    private final GenreRowMapper mapper;

    public GenreDaoJdbc(NamedParameterJdbcOperations operations) {
        this.operations = operations;
        this.mapper = new GenreRowMapper();
    }

    @Override
    public Genre create(Genre genre) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", genre.getId())
                .addValue("name", genre.getName());
        operations.update("insert into genre (`id`, `name`) values (:id, :name)", params);
        return operations.queryForObject("select * from genre where id = :id", params, mapper);
    }

    @Override
    public Optional<Genre> read(long id) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        List<Genre> genres = operations.query("select `id`, `name` from genre where id = :id", params, mapper);
        if (genres.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(genres.get(0));
    }

    @Override
    public Collection<Genre> readAll() {
        return operations.query("select `id`, `name` from genre", mapper);
    }

    @Override
    public void update(Genre genre) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", genre.getId())
                .addValue("name", genre.getName());
        operations.update("update genre set `name`=:name where `id`=:id", params);
    }

    @Override
    public boolean delete(Genre genre) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", genre.getId());
        final int rowsAffected = operations.update("delete from genre where `id` = :id", params);
        return rowsAffected > 0;
    }

    private static class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(
                    rs.getLong("id"),
                    rs.getString("name")
            );
        }
    }
}
