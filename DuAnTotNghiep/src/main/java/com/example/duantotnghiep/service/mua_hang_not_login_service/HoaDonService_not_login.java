package com.example.duantotnghiep.service.mua_hang_not_login_service;

import com.example.duantotnghiep.request.not_login.CreateKhachRequest_not_login;
import com.example.duantotnghiep.response.MessageResponse;

public interface HoaDonService_not_login {
    MessageResponse thanhToanKhongDangNhap(CreateKhachRequest_not_login createKhachRequest_not_login);
}
