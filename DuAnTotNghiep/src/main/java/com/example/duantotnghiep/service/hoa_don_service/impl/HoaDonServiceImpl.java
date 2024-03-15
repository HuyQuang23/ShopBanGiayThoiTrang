package com.example.duantotnghiep.service.hoa_don_service.impl;

import com.example.duantotnghiep.entity.*;
import com.example.duantotnghiep.enums.StatusCartDetailEnums;
import com.example.duantotnghiep.enums.StatusCartEnums;
import com.example.duantotnghiep.enums.StatusOrderDetailEnums;
import com.example.duantotnghiep.repository.*;
import com.example.duantotnghiep.request.HoaDonGiaoThanhToanRequest;
import com.example.duantotnghiep.response.HoaDonDTOResponse;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.response.TokenResponse;
import com.example.duantotnghiep.service.hoa_don_service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private TrangThaiHoaDonRepository trangThaiHoaDonRepository;

    @Override
    public List<HoaDonDTOResponse> getAllHoaDonAdmin(Integer trangThaiHD, Integer loaiDon, String tenNhanVien, String ma, String soDienThoai, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<HoaDonDTOResponse> pageList = hoaDonRepository.getAllHoaDonAdmin(trangThaiHD, loaiDon, tenNhanVien, ma, soDienThoai, pageable);
        return pageList.getContent();
    }

    @Override
    public List<HoaDonDTOResponse> getAllHoaDonStaff(Integer trangThaiHD, Integer loaiDon, String ma, String soDienThoai, String username, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<HoaDonDTOResponse> pageList = hoaDonRepository.getAllHoaDonStaff(trangThaiHD, loaiDon, ma, soDienThoai, username, pageable);
        return pageList.getContent();
    }

    @Override
    public List<HoaDonDTOResponse> getAllHoaDonCTTStaff(Integer loaiDon, String ma, String soDienThoai, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<HoaDonDTOResponse> pageList = hoaDonRepository.getAllHoaDonCTTStaff(loaiDon, ma, soDienThoai, pageable);
        return pageList.getContent();
    }

    @Override
    public HoaDon updateHoaDon(UUID hoaDonId, String name) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Optional<HoaDon> hoaDonOptional = hoaDonRepository.findById(hoaDonId);
        if (hoaDonOptional.isPresent()) {
            HoaDon hoaDon = hoaDonOptional.get();
            hoaDon.setNgayCapNhap(timestamp);
            Optional<TaiKhoan> taiKhoanOptional = taiKhoanRepository.findByUsername(name);
            if (taiKhoanOptional.isPresent()) {
                TaiKhoan taiKhoan = taiKhoanOptional.get();
                hoaDon.setTaiKhoanNhanVien(taiKhoan);
            }
            return hoaDonRepository.save(hoaDon); // Lưu hóa đơn đã cập nhật và trả về nó
        }
        TrangThaiHoaDon trangThaiHoaDon = new TrangThaiHoaDon();
        trangThaiHoaDon.setId(UUID.randomUUID());
        trangThaiHoaDon.setTrangThai(StatusOrderDetailEnums.CHO_XAC_NHAN.getValue());
        trangThaiHoaDon.setThoiGian(timestamp);
        trangThaiHoaDon.setGhiChu("Nhân viên xác nhận đơn cho khách");
        trangThaiHoaDon.setHoaDon(hoaDonOptional.get());
        trangThaiHoaDonRepository.save(trangThaiHoaDon);
        return null; // Xử lý khi hóa đơn không tồn tại
    }

}

