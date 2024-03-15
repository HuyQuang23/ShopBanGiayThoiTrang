package com.example.duantotnghiep.response;

import com.example.duantotnghiep.enums.TypeAccountEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NhanVienResponse {

    private String image;

    private String username;

    private String email;

    private String fullName;

    private Integer trangThai;

    private TypeAccountEnum role;
}
