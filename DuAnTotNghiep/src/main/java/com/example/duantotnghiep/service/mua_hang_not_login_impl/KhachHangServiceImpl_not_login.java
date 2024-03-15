package com.example.duantotnghiep.service.mua_hang_not_login_impl;

import com.example.duantotnghiep.entity.DiaChi;
import com.example.duantotnghiep.entity.TaiKhoan;
import com.example.duantotnghiep.repository.mua_hang_not_login_repo.DiaChiRepository_not_login;
import com.example.duantotnghiep.repository.mua_hang_not_login_repo.KhachHangRepository_not_login;
import com.example.duantotnghiep.request.not_login.CreateKhachRequest_not_login;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.service.mua_hang_not_login_service.KhachHangService_not_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KhachHangServiceImpl_not_login implements KhachHangService_not_login {

    @Autowired
    private KhachHangRepository_not_login khachHangRepository_not_login;

    @Autowired
    private DiaChiRepository_not_login diaChiRepository_not_login;

    @Override
    public MessageResponse createKhachHang(CreateKhachRequest_not_login CreateKhachRequest_not_login) {
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setId(UUID.randomUUID());
        taiKhoan.setName(CreateKhachRequest_not_login.getHoTen());
        taiKhoan.setSoDienThoai(CreateKhachRequest_not_login.getSoDienThoai());
        taiKhoan.setEmail(CreateKhachRequest_not_login.getEmail());
        khachHangRepository_not_login.save(taiKhoan);
        DiaChi diaChi = new DiaChi();
        diaChi.setId(UUID.randomUUID());
        diaChi.setDiaChi(CreateKhachRequest_not_login.getDiaChi());
        diaChi.setTinh(CreateKhachRequest_not_login.getThanhPho());
        diaChi.setHuyen(CreateKhachRequest_not_login.getQuanHuyen());
        diaChi.setXa(CreateKhachRequest_not_login.getPhuongXa());

        diaChi.setTaiKhoan(taiKhoan);
        diaChiRepository_not_login.save(diaChi);
        return MessageResponse.builder().message("Thêm Thành Công").build();
    }

    @Override
    public List<TaiKhoan> findKhachHang(String email, String sodienthoai) {
        return khachHangRepository_not_login.getKhachHangByEmailAndSdt(email,sodienthoai);
    }
}
