package com.example.duantotnghiep.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SanPhamGetAllResponse {

    UUID id;

    String tenSanPham;

    String tenImage ;

    String tenThuongHieu ;

    String tenDanhMuc ;

    String tenXuatXu ;

    BigDecimal giaBan;
}
