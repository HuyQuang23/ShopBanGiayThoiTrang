package com.example.duantotnghiep.controller.mua_hang_not_login_controller;


import com.example.duantotnghiep.request.not_login.CreateKhachRequest_not_login;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.service.mua_hang_not_login_impl.HoaDonServiceImpl_not_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout-not-login")
public class HoaDonController_not_login {

    @Autowired
    private HoaDonServiceImpl_not_login hoaDonService_not_login;

    // Endpoint để tạo hóa đơn và thanh toán khi khách hàng không đăng nhập
    @PostMapping("/thanh-toan")
    public MessageResponse thanhToanKhongDangNhap(
            @RequestBody CreateKhachRequest_not_login createKhachRequest_not_login
    ) {
        return hoaDonService_not_login.thanhToanKhongDangNhap(createKhachRequest_not_login);
    }

}

