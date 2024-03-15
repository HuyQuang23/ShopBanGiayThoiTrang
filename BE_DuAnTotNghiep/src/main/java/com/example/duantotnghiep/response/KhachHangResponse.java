package com.example.duantotnghiep.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangResponse {

    private UUID id;

    private String ten;

    private String email;

    private String soDienThoai;

    private String diaChi;

}
