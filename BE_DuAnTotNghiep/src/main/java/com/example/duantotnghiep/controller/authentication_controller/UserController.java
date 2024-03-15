package com.example.duantotnghiep.controller.authentication_controller;

import com.example.duantotnghiep.entity.RefreshToken;
import com.example.duantotnghiep.jwt.JwtService;
import com.example.duantotnghiep.model.UserCustomDetails;
import com.example.duantotnghiep.request.LoginRequest;
import com.example.duantotnghiep.request.RegisterRequest;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.response.TokenResponse;
import com.example.duantotnghiep.service.authentication_service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.CREATED);
    }

    // TODO: phương thức có nhiệm vụ cập nhật token.
    @PostMapping("refresh-token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshToken refreshToken) {
        try {
            // tìm user thông qua token
            TokenResponse response = userService.findByToken(refreshToken.getToken())
                    .map(userService::verifyExpiration) // nếu tìm thấy user sẽ gọi hàm very...
                    .map(RefreshToken::getTaiKhoan) // lấy ra thông tin phật tử
                    .map(phatTu -> {
                        String accessToken = jwtService.generateToken(new UserCustomDetails(phatTu));
                        return TokenResponse.builder()
                                .accessToken(accessToken)
                                .token(refreshToken.getToken())
                                .build();
                    }).orElseThrow(() -> new RuntimeException(
                            "Refresh token is not in database!"));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
