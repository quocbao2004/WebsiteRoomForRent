package com.javaweb.WebsiteRoomForRent.services.impl;

import com.javaweb.WebsiteRoomForRent.components.JwtTokenUtil;
import com.javaweb.WebsiteRoomForRent.customexceptions.DataNotFoundException;
import com.javaweb.WebsiteRoomForRent.customexceptions.PermissionDenyException;
import com.javaweb.WebsiteRoomForRent.dtos.PasswordDTO;
import com.javaweb.WebsiteRoomForRent.dtos.UserDTO;
import com.javaweb.WebsiteRoomForRent.entities.Role;
import com.javaweb.WebsiteRoomForRent.entities.TokenEntity;
import com.javaweb.WebsiteRoomForRent.entities.UserEntity;
import com.javaweb.WebsiteRoomForRent.repository.RoleRepository;
import com.javaweb.WebsiteRoomForRent.repository.TokenRepository;
import com.javaweb.WebsiteRoomForRent.repository.UserRepository;
import com.javaweb.WebsiteRoomForRent.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserEntity createUser(UserDTO userDTO) throws Exception {
        //register user
//        String phone = userDTO.getPhone();
        Role role = new Role();
        role.setId(1L);
        role.setName("ADMIN");

//        if(role.getName().toUpperCase().equals(Role.ADMIN)) {
//            throw new PermissionDenyException("You cannot register an admin account");
//        }
        //convert from userDTO => userEntity
        UserEntity newUser = UserEntity.builder()
                .fullname(userDTO.getFullname())
                .phone(userDTO.getPhone())
                .password(userDTO.getPassword())
                .username(userDTO.getUsername())
                .build();

        newUser.setRole(role);

        String password = userDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) throws Exception {
        Optional<UserEntity> optionalUser = userRepository.findByPhone(phoneNumber);
        if(optionalUser.isEmpty()) {
            throw new DataNotFoundException("Invalid phone number / password");
        }
        //return optionalUser.get();//muốn trả JWT token ?
        UserEntity existingUser = optionalUser.get();
        //check password
        if(!passwordEncoder.matches(password, existingUser.getPassword())) {
                throw new BadCredentialsException("Wrong phone number or password");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                phoneNumber, password,
                existingUser.getAuthorities()
        );

        //authenticate with Java Spring security
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existingUser);
    }

    @Override
    public void revokeToken(String token) {
        TokenEntity tokenEntity = tokenRepository.findByToken(token).orElse(null);
        if (tokenEntity != null) {
            tokenEntity.setRevoked(true); // Đánh dấu token là đã bị thu hồi
            tokenRepository.save(tokenEntity);
        } else {
            throw new DataNotFoundException("Token not found");
        }
    }

    @Override
    public String changePassword(PasswordDTO password, UserEntity user) throws Exception{
        try {
            if(!password.getRetypePassword().equals(password.getNewPassword())) {
                return("Password does not match");
            } else {
                if (!passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
                    return("Current password does not match");
                }
                String encoderNewPassword = passwordEncoder.encode(password.getNewPassword());
                user.setPassword(encoderNewPassword);
                userRepository.save(user);
                return("ok");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    @Override
    public String editProfile(UserDTO userDTO) throws Exception{
        UserEntity user = userRepository.findById(userDTO.getId()).get();

        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getFullname() != null && !userDTO.getFullname().isEmpty()) {
            user.setFullname(userDTO.getFullname());
        }
        if (userDTO.getPhone() != null && !userDTO.getPhone().isEmpty()) {
            user.setPhone(userDTO.getPhone());
            user.setUsername(userDTO.getPhone());
        }
        if (userDTO.getAddress() != null && !userDTO.getAddress().isEmpty()) {
            user.setAddress(userDTO.getAddress());
        }

        List<TokenEntity> oldTokens = tokenRepository.findAllByUserAndExpiredEquals(user, false);
        if (!oldTokens.isEmpty()) {
            for (TokenEntity oldToken : oldTokens) {
                oldToken.setRevoked(true);
                oldToken.setExpired(true);
            }
            tokenRepository.saveAll(oldTokens);
        }

        String newToken = jwtTokenUtil.generateToken(user);
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(newToken);
        tokenEntity.setExpired(false);
        tokenEntity.setTokenType("Bearer");
        tokenEntity.setUser(userRepository.findById(2L).get());
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(30);
        tokenEntity.setExpirationDate(LocalDateTime.parse(expirationDate.toString()));
        tokenRepository.save(tokenEntity);
        userRepository.save(user);
        return newToken;
    }

    @Override
    public UserDTO getUser(Long id){
        try {
            UserEntity user = userRepository.findById(id).get();
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return userDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return new UserDTO();
        }
    }

}