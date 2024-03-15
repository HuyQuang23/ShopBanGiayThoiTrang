package com.example.duantotnghiep.service.mua_hang_not_login_service;

import com.example.duantotnghiep.mapper.GioHangCustom_not_login;
import com.example.duantotnghiep.mapper.NameAndQuantityCart_Online;
import com.example.duantotnghiep.mapper.TongTienCustom_Online;

import java.util.List;
import java.util.UUID;

public interface GioHangChiTietService_not_login {
    void themSanPhamVaoGioHangChiTiet(UUID idGioHang, UUID idSanPhamChiTiet, int soLuong);
    List<GioHangCustom_not_login> loadGH(UUID idgh);
    List<TongTienCustom_Online> getTongTien(UUID idgh);
    List<NameAndQuantityCart_Online> getNameAndQuantity(UUID idgh);
}
