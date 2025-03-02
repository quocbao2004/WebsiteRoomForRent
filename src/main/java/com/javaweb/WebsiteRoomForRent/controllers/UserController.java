package com.javaweb.WebsiteRoomForRent.controllers;

import com.javaweb.WebsiteRoomForRent.components.JwtTokenUtil;
import com.javaweb.WebsiteRoomForRent.dtos.PasswordDTO;
import com.javaweb.WebsiteRoomForRent.dtos.UserDTO;
import com.javaweb.WebsiteRoomForRent.dtos.UserLoginDTO;
import com.javaweb.WebsiteRoomForRent.entities.TokenEntity;
import com.javaweb.WebsiteRoomForRent.entities.UserEntity;
import com.javaweb.WebsiteRoomForRent.repository.TokenRepository;
import com.javaweb.WebsiteRoomForRent.repository.UserRepository;
import com.javaweb.WebsiteRoomForRent.requests.TokenRequest;
import com.javaweb.WebsiteRoomForRent.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.Introspection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}/users")
@Transactional
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwt;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO,
                                        BindingResult result){
        try{
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if(!userDTO.getPassword().equals(userDTO.getRetypePassword())){
                return ResponseEntity.badRequest().body("Password not match");
            }
            UserEntity user = userService.createUser(userDTO);//return ResponseEntity.ok("Register successfully");
            return ResponseEntity.ok("");
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); //rule 5
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO) {
        // Kiểm tra thông tin đăng nhập và sinh token
        try {
            String token = userService.login(userLoginDTO.getPhone(), userLoginDTO.getPassword());
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setToken(token);
            tokenEntity.setExpired(false);
            tokenEntity.setTokenType("Bearer");
            tokenEntity.setUser(userRepository.findById(1L).get());
            LocalDateTime expirationDate = LocalDateTime.now().plusDays(30);
            tokenEntity.setExpirationDate(LocalDateTime.parse(expirationDate.toString()));
            tokenRepository.save(tokenEntity);
            // Trả về token trong response
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody TokenRequest tokenRequest) {
        try {
            Optional<TokenEntity> optionalTokenEntity = tokenRepository.findByToken(tokenRequest.getToken());
            if (optionalTokenEntity.isPresent()) {
                TokenEntity tokenEntity = optionalTokenEntity.get();
                tokenEntity.setRevoked(true);
                tokenEntity.setExpired(true);
                tokenRepository.save(tokenEntity);
                return ResponseEntity.ok("Logout successful");
            } else {
                return ResponseEntity.badRequest().body("Token not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Logout failed: " + e.getMessage());
        }
    }

    @PostMapping("/edit-user")
    public ResponseEntity<Map<String, String>> editProfile(@RequestBody UserDTO userDTO) throws Exception {
        String newToken = userService.editProfile(userDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Profile updated successfully.");
        response.put("token", newToken);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/change-password")
    public ResponseEntity<String>changePassword(@Valid @RequestBody PasswordDTO passwordDTO, BindingResult result){
        try {
            UserEntity user  = userRepository.findById(passwordDTO.getId()).get();
            if(user.getUsername().equals(passwordDTO.getUsername())){
                return ResponseEntity.ok(userService.changePassword(passwordDTO, user));
            } else {
                return ResponseEntity.badRequest().body("Username not match");
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Edit failed: " + e.getMessage());
        }
    }

    @GetMapping("/get-user")
    public ResponseEntity<?>getUser(@RequestParam Long id){ // chỗ này t sửa ResponseEntity<String> thành ResponseEntity<?>
        try {
            UserDTO userDTO = userService.getUser(id);
            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Get failed: " + e.getMessage());
        }
    }

    @GetMapping("check-username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        try {
            String getUsername = userRepository.findByPhone(username).get().getUsername();
            if (getUsername.equals(username)) {
                Map<String, String> response = new HashMap<>();
                response.put("username", getUsername);
                return ResponseEntity.ok(response); // Return a JSON object
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Get failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body("Get failed");
    }

}