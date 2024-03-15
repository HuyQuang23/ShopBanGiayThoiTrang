package com.example.duantotnghiep.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonResponse {

    private UUID id;

    private String maHoaDon;

    private String tenNhanVien;

    private Date ngayTao;

    private String tenKhachHang;

    private String loaiDon;

    private Integer trangThai;

}
