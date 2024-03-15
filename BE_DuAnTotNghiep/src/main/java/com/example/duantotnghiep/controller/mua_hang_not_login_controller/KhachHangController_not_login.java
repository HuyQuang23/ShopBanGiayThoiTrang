package com.example.duantotnghiep.controller.mua_hang_not_login_controller;

import com.example.duantotnghiep.request.not_login.CreateKhachRequest_not_login;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.service.mua_hang_not_login_impl.KhachHangServiceImpl_not_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/khach-hang-not-login/")
public class KhachHangController_not_login {

    @Autowired
    private KhachHangServiceImpl_not_login khachHangService;

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createKhachHang(@RequestBody CreateKhachRequest_not_login createKhachRequest_not_login) {
        return new ResponseEntity<>(khachHangService.createKhachHang(createKhachRequest_not_login), HttpStatus.CREATED);
    }

}
