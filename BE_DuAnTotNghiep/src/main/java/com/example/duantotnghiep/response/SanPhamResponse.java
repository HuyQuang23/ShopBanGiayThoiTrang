package com.example.duantotnghiep.response;

import java.math.BigDecimal;
import java.util.UUID;


public interface SanPhamResponse {

    UUID getId();

    String getTenImage();

    String getTenSanPham();

    BigDecimal getGiaBan();




}
