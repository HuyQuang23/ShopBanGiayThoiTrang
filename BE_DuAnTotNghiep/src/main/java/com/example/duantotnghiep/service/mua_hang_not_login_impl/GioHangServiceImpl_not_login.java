package com.example.duantotnghiep.service.mua_hang_not_login_impl;

import com.example.duantotnghiep.entity.GioHang;
import com.example.duantotnghiep.repository.mua_hang_not_login_repo.GioHangRepository_not_login;
import com.example.duantotnghiep.service.mua_hang_not_login_service.GioHangService_not_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;

@Service
public class GioHangServiceImpl_not_login implements GioHangService_not_login {

    @Autowired
    private GioHangRepository_not_login gioHangRepository;


    public UUID taoGioHang() {
        GioHang gioHang = new GioHang();
        gioHang.setId(UUID.randomUUID());
        gioHang.setNgayTao(new Date(System.currentTimeMillis()));
        gioHang.setTrangThai(1);

        GioHang gioHangMoi = gioHangRepository.save(gioHang);
        return gioHangMoi.getId();
    }

}