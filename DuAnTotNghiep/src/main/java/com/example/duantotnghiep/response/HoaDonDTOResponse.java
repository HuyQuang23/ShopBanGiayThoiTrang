package com.example.duantotnghiep.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonDTOResponse {
    private UUID id;

    private String ma;

    private String tenKhachHang;

    private String soDienThoai;

    private BigDecimal thanhTien;

    private BigDecimal tienGiam;

    private Date ngayTao;

    private String tenNhanVien;

    private String loaiDon;

    private int trangThai;

}
