package com.example.duantotnghiep.service.ban_tai_quay_service.impl;

import com.example.duantotnghiep.entity.*;
import com.example.duantotnghiep.enums.TypeAccountEnum;
import com.example.duantotnghiep.repository.*;
import com.example.duantotnghiep.request.CreateKhachRequest;
import com.example.duantotnghiep.response.KhachHangResponse;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.service.ban_tai_quay_service.CustomerCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerCounterServiceImpl implements CustomerCounterService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private DiaChiRepository diaChiRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private LoaiTaiKhoanRepository loaiTaiKhoanRepository;

    @Override
    public List<KhachHangResponse> getKhachHang() {
        return khachHangRepository.findlistKhachHang();
    }

    @Override
    public List<KhachHangResponse> findByKeyToKhachHang(String key) {
        return khachHangRepository.findByKeyToKhachHang(key);
    }

    @Override
    public MessageResponse createKhachHang(CreateKhachRequest createKhachRequest) {
        LoaiTaiKhoan loaiTaiKhoan = loaiTaiKhoanRepository.findByName(TypeAccountEnum.USER).get();
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setId(UUID.randomUUID());
        taiKhoan.setName(createKhachRequest.getHoTen());
        taiKhoan.setSoDienThoai(createKhachRequest.getSoDienThoai());
        taiKhoan.setEmail(createKhachRequest.getEmail());
        taiKhoan.setNgaySinh(createKhachRequest.getNgaySinh());
        taiKhoan.setLoaiTaiKhoan(loaiTaiKhoan);
        khachHangRepository.save(taiKhoan);
        DiaChi diaChi = new DiaChi();
        diaChi.setId(UUID.randomUUID());
        diaChi.setDiaChi(createKhachRequest.getDiaChi());
        diaChi.setTaiKhoan(taiKhoan);
        diaChi.setTrangThai(1);
        diaChiRepository.save(diaChi);
        return MessageResponse.builder().message("Thêm Thành Công").build();
    }

    @Override
    public MessageResponse updateHoaDon(UUID id, UUID idHoaDon, UUID idGioHang) {

        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHoaDon);
        if (hoaDon.isEmpty()) {
            return MessageResponse.builder().message("Hóa Đơn Null").build();
        }
        Optional<TaiKhoan> khachHang = khachHangRepository.findById(id);
        if (khachHang.isEmpty()) {
            return MessageResponse.builder().message("Khách Hàng Null").build();
        }
        hoaDon.get().setTaiKhoanKhachHang(khachHang.get());
        hoaDonRepository.save(hoaDon.get());

        GioHang gioHang = gioHangRepository.findById(idGioHang).get();
        gioHang.setTaiKhoan(khachHang.get());
        gioHangRepository.save(gioHang);

        return MessageResponse.builder().message("Update Thành Công").build();
    }

    @Override
    public KhachHangResponse detailKhachHang(UUID id) {
        return khachHangRepository.detailKhachHang(id);
    }

}
