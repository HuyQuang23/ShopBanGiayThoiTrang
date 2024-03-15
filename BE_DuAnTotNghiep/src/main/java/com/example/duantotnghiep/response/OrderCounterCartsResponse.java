package com.example.duantotnghiep.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCounterCartsResponse {

    private UUID idKhach;

    private String maHoaDon;

    private String tenKhach;

    private Date ngayTao;

    private String diaChi;

    private String email;

    private String soDienThoai;

}
