package com.example.swscreen.rowmapper;

import com.example.swscreen.domain.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Users.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .imageUrl(resultSet.getString("image_url"))
                .build();
    }

}
