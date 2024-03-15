package com.example.duantotnghiep.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


public interface SanPhamHoaDonChiTietResponse {
    String getTenImage();

    String getTenSanPham();

    BigDecimal getGiaBan();

    BigDecimal getDonGiaSauGiam();

    Integer getSoLuong();

}
