package com.example.duantotnghiep.service.ban_tai_quay_service.impl;

import com.example.duantotnghiep.repository.TaiKhoanRepository;
import com.example.duantotnghiep.repository.GioHangRepository;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.service.ban_tai_quay_service.CartCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartCounterServiceImpl implements CartCounterService {
    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public MessageResponse updateGioHang(UUID idGioHang, UUID idAccount) {
        var gioHang = gioHangRepository.findById(idGioHang);
        var khachHang = taiKhoanRepository.findById(idAccount);
        gioHang.get().setTaiKhoan(khachHang.get());
        gioHangRepository.save(gioHang.get());
        return MessageResponse.builder().message("Update thành công").build();
    }
}