package com.example.duantotnghiep.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NhanVienRequest {

    private UUID id;

    @NotBlank(message = "Không Được Để Trống Tên Tài Khoản")
    private String username;

    @NotBlank(message = "Không Được Để Trống Họ Và Tên")
    private String fullName;

    @Email(
            message = "Email Không Hợp Lệ",
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    )
    @NotEmpty(message = "Email Không Được Dể Trống")
    private String email;

    private String vaiTro;
}