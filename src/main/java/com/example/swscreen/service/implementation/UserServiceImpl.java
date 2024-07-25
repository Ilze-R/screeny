package com.example.swscreen.service.implementation;

import com.example.swscreen.domain.Role;
import com.example.swscreen.domain.Users;
import com.example.swscreen.dto.UserDTO;
import com.example.swscreen.repository.RoleRepository;
import com.example.swscreen.repository.UserRepository;
import com.example.swscreen.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.example.swscreen.dtomapper.UserDTOMapper.fromUser;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<Users> userRepository;
    private final RoleRepository<Role> roleRepository;
    @Override
    public UserDTO createUser(Users user) {
        return mapUserToDTO(userRepository.create(user));
    }

//    @Override
//    public UserDTO getUserByEmail(String email) {
//        return null;
//    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return mapUserToDTO(userRepository.getUserByUsername(username));
    }

//    @Override
//    public void sendVerificationCode(UserDTO user) {
//
//    }

    @Override
    public UserDTO verifyCode(String email, String code) {
        return null;
    }

    @Override
    public void resetPassword(String email) {

    }

    @Override
    public UserDTO verifyPasswordKey(String key) {
        return null;
    }

    @Override
    public void renewPasswordKey(String key, String password, String confirmPassword) {

    }

    @Override
    public UserDTO verifyAccountKey(String key) {
        return null;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return mapUserToDTO(userRepository.get(userId));
    }

    @Override
    public void updatePassword(Long userId, String currentPassword, String newPassword, String confirmNewPassword) {

    }

    @Override
    public void updateUserRole(Long userId, String roleName) {

    }

    @Override
    public void updateAccountSettings(Long userId, Boolean enabled, Boolean notLocked) {

    }

    @Override
    public void updateImage(UserDTO user, MultipartFile image) {
userRepository.updateImage(user, image);
    }

    private UserDTO mapUserToDTO(Users user) {
        return fromUser(user, roleRepository.getRoleByUserId(user.getId()));
    }

}
