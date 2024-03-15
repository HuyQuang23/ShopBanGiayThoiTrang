package com.example.duantotnghiep.mapper;

import com.example.duantotnghiep.entity.SanPhamChiTiet;
import com.example.duantotnghiep.response.ProductDetailResponse;

public class ProductMapper {

    public static SanPhamChiTiet sanPhamChiTietToDto(ProductDetailResponse productDetailResponse) {
        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
        sanPhamChiTiet.setId(productDetailResponse.getId());
        return sanPhamChiTiet;
    }
}
