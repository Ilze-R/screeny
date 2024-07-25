package com.example.swscreen.repository.implementation;

import com.example.swscreen.domain.Role;
import com.example.swscreen.domain.UserPrincipal;
import com.example.swscreen.domain.Users;
import com.example.swscreen.dto.UserDTO;
import com.example.swscreen.exception.ApiException;
import com.example.swscreen.repository.RoleRepository;
import com.example.swscreen.repository.UserRepository;
import com.example.swscreen.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;


import java.util.Collection;

import static com.example.swscreen.enumeration.RoleType.ROLE_USER;
import static com.example.swscreen.query.UserQuery.*;
import static java.util.Objects.requireNonNull;
import static java.util.Map.of;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<Users>, UserDetailsService {
    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    private final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository<Role> roleRepository;
    private final BCryptPasswordEncoder encoder;


    @Override
    public Users create(Users user) {
        if (getUsernameCount(user.getUsername().trim().toLowerCase()) > 0)
            throw new ApiException("Username already in use. Please use a different username and try again");
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, parameters, holder);
            user.setId(requireNonNull(holder.getKey()).longValue());
            roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());
            return user;
        } catch (Exception exception) {
            throw new ApiException("An error occurred. Please try again");
        }
    }

    private Integer getUsernameCount(String username){
        return jdbc.queryForObject(COUNT_USER_USERNAMES_QUERY, of("username", username), Integer.class);
    }

    @Override
    public Collection<Users> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Users get(Long id) {
        try {
            return jdbc.queryForObject(SELECT_USER_BY_ID_QUERY, of("id", id), new UserRowMapper());
        } catch (EmptyResultDataAccessException exception){
            throw new ApiException("No user found by id: " + id);
        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again");
        }
    }

    @Override
    public Users update(Users data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

//    @Override
//    public void sendVerificationCode(UserDTO user) {
//
//    }

    @Override
    public Users verifyCode(String email, String code) {
        return null;
    }

    @Override
    public void resetPassword(String email) {

    }

    @Override
    public Users verifyPasswordKey(String key) {
        return null;
    }

    @Override
    public void renewPassword(String key, String password, String confirmPassword) {

    }

    @Override
    public Users verifyAccountKey(String key) {
        return null;
    }

    @Override
    public void updatePassword(Long id, String currentPassword, String newPassword, String confirmNewPassword) {

    }

    @Override
    public void updateAccountSettings(Long userId, Boolean enabled, Boolean notLocked) {

    }

    @Override
    public void updateImage(UserDTO user, MultipartFile image) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = getUserByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database: {}", username);
            return new UserPrincipal(user, roleRepository.getRoleByUserId(user.getId()));
        }
    }

        @Override
    public Users getUserByUsername(String username) {
            try {
                Users user = jdbc.queryForObject(SELECT_USER_BY_USERNAME_QUERY, of("username", username), new UserRowMapper());
                return user;
            } catch (EmptyResultDataAccessException exception){
                throw new ApiException("No user found by username: " + username);
            }catch (Exception exception){
                log.error(exception.getMessage());
                throw new ApiException("An error occurred. Please try again");
            }
    }

    private SqlParameterSource getSqlParameterSource(Users user) {
        return new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", encoder.encode(user.getPassword()));
    }
}
