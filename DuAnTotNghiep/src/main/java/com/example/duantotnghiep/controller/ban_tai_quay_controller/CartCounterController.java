package com.example.duantotnghiep.controller.ban_tai_quay_controller;

import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.service.ban_tai_quay_service.impl.CartCounterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/gio-hang")
public class CartCounterController {

    @Autowired
    private CartCounterServiceImpl gioHangService;

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateGioHang(@RequestParam("idGioHang") UUID idGioHang,
                                                         @RequestParam("idKhachHang") UUID idAccount) {
        return new ResponseEntity<>(gioHangService.updateGioHang(idGioHang, idAccount), HttpStatus.OK);
    }

}