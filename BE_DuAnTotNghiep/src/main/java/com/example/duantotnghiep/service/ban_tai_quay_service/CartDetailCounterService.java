package com.example.duantotnghiep.service.ban_tai_quay_service;

import com.example.duantotnghiep.mapper.GioHangCustom;
import com.example.duantotnghiep.response.MessageResponse;

import java.util.List;
import java.util.UUID;

public interface CartDetailCounterService {

    MessageResponse themSanPhamVaoGioHangChiTiet(UUID idGioHang, UUID idSanPhamChiTiet, int soLuong);

    MessageResponse themSanPhamVaoGioHangChiTietQrCode(UUID idGioHang, String qrCode);

    List<GioHangCustom> loadGH(UUID id, Integer pageNumber, Integer pageSize);

    void deleteProductInCart(UUID id);
}
