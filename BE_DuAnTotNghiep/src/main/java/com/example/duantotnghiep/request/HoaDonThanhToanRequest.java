package com.example.duantotnghiep.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonThanhToanRequest {

    private UUID idHoaDon;

    private String hoTen;

    private String soDienThoai;

    private String diaChi;

    private BigDecimal tongTien;

    private BigDecimal tienKhachTra;

    private BigDecimal tienThua;

    private List<UUID> gioHangChiTietList;
}
