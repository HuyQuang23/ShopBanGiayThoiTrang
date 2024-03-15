package com.example.duantotnghiep.controller.hoa_don_controller;

import com.example.duantotnghiep.entity.HoaDon;
import com.example.duantotnghiep.entity.TaiKhoan;
import com.example.duantotnghiep.request.HoaDonGiaoThanhToanRequest;
import com.example.duantotnghiep.response.HoaDonDTOResponse;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.response.TokenResponse;
import com.example.duantotnghiep.service.account_service.TaiKhoanService;
import com.example.duantotnghiep.service.hoa_don_service.impl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hoa-don/")
public class HoaDonController {

    @Autowired
    private HoaDonServiceImpl hoaDonService;

    @Autowired
    private TaiKhoanService.TaiKhoanServiceImpl taiKhoanService;


    @GetMapping("hien-thi")
    public ResponseEntity<List<HoaDonDTOResponse>> getAll(
            @RequestParam(name = "trangThaiHD", required = false) Integer trangThaiHD,
            @RequestParam(name = "loaiDon", required = false) Integer loaiDon,
            @RequestParam(name = "tenNhanVien", required = false) String tenNhanVien,
            @RequestParam(name = "ma", required = false) String ma,
            @RequestParam(name = "soDienThoai", required = false) String soDienThoai,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            Principal principal
    ) {
        String username = principal.getName();
        Optional<TaiKhoan> taiKhoan = taiKhoanService.findByUserName(username);
        String role = taiKhoan.get().getLoaiTaiKhoan().getName().name();

        if (role == "STAFF") {
            if (trangThaiHD == 1) {
                return new ResponseEntity<>(hoaDonService.getAllHoaDonCTTStaff(loaiDon, ma, soDienThoai, pageNumber, pageSize), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(hoaDonService.getAllHoaDonStaff(trangThaiHD, loaiDon, ma, soDienThoai, username, pageNumber, pageSize), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(hoaDonService.getAllHoaDonAdmin(trangThaiHD, loaiDon, tenNhanVien, ma, soDienThoai, pageNumber, pageSize), HttpStatus.OK);
        }
    }
    @PutMapping("update/{hoaDonId}")
    public ResponseEntity<HoaDon> updateHoaDonWithIdNhanVien(
            @PathVariable("hoaDonId") UUID hoaDonId,
            Principal principal
    ) {
        String username = principal.getName();
        return new ResponseEntity<>(hoaDonService.updateHoaDon(hoaDonId, username), HttpStatus.OK);
    }

}
