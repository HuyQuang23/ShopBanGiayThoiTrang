package com.example.duantotnghiep.mapper.not_login;

import java.math.BigDecimal;
import java.util.UUID;

public interface loadsanpham_not_login {
    UUID getId();
    String getTenImage();
    String getTenSanPham();
    BigDecimal getGiaban();
    BigDecimal getDonGiaKhiGiam();
    Long getMucGiam();
    UUID getIdThuongHieu();

}
