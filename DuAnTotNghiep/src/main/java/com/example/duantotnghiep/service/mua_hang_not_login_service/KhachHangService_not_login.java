package com.example.duantotnghiep.service.mua_hang_not_login_service;

import com.example.duantotnghiep.entity.TaiKhoan;
import com.example.duantotnghiep.request.not_login.CreateKhachRequest_not_login;
import com.example.duantotnghiep.response.MessageResponse;

import java.util.List;

public interface KhachHangService_not_login {
    MessageResponse createKhachHang(CreateKhachRequest_not_login CreateKhachRequest_not_login);
    List<TaiKhoan> findKhachHang(String email,String sodienthoai);
}
