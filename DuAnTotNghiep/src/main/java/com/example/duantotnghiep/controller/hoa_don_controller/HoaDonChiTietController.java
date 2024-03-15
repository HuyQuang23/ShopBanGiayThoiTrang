package com.example.duantotnghiep.controller.hoa_don_controller;

import com.example.duantotnghiep.request.TrangThaiHoaDonRequest;
import com.example.duantotnghiep.request.XacNhanThanhToanRequest;
import com.example.duantotnghiep.response.HinhThucThanhToanResponse;
import com.example.duantotnghiep.response.SanPhamHoaDonChiTietResponse;
import com.example.duantotnghiep.response.ThongTinDonHang;
import com.example.duantotnghiep.response.TrangThaiHoaDonResponse;
import com.example.duantotnghiep.service.hoa_don_service.impl.HoaDonChiTietServiceImpl;
import com.example.duantotnghiep.service.hoa_don_service.impl.TrangThaiHoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
    @RequestMapping("/api/v1/hoa-don-chi-tiet/")
public class HoaDonChiTietController {

    @Autowired
    private HoaDonChiTietServiceImpl hoaDonChiTietService;

    @Autowired
    private TrangThaiHoaDonServiceImpl trangThaiHoaDonService;

    @GetMapping("hien-thi-don/{idHoaDon}")
    public ResponseEntity<ThongTinDonHang> viewThongTinDonHang(@PathVariable(name = "idHoaDon") UUID idHoaDon) {
        return new ResponseEntity<>(hoaDonChiTietService.getThongTinDonHang(idHoaDon), HttpStatus.OK);
    }
    @GetMapping("hien-thi-san-pham/{idHoaDon}")
    public ResponseEntity<List<SanPhamHoaDonChiTietResponse>> getSanPhamHDCT(@PathVariable(name = "idHoaDon") UUID idHoaDon) {
        return new ResponseEntity<>(hoaDonChiTietService.getSanPhamHDCT(idHoaDon), HttpStatus.OK);
    }

    @GetMapping("hien-thi-lich-su/{idHoaDon}")
    public ResponseEntity<List<HinhThucThanhToanResponse>> getLichSuThanhToan(@PathVariable(name = "idHoaDon") UUID idHoaDon) {
        return new ResponseEntity<>(hoaDonChiTietService.getLichSuThanhToan(idHoaDon), HttpStatus.OK);
    }
    @GetMapping("hien-thi-trang-thai/{idHoaDon}")
    public ResponseEntity<List<TrangThaiHoaDonResponse>> getAllTrangThaiHoaDon(@PathVariable(name = "idHoaDon") UUID idHoaDon) {
        return new ResponseEntity<>(hoaDonChiTietService.getAllTrangThaiHoaDon(idHoaDon), HttpStatus.OK);
    }

    @PostMapping("confirm-order/{hoadonId}")
    public ResponseEntity<String> confirmOrder(@PathVariable UUID hoadonId, @RequestBody TrangThaiHoaDonRequest request) {
        trangThaiHoaDonService.confirmOrder(hoadonId, request);
        return ResponseEntity.ok("Trạng thái hóa đơn đã được cập nhật.");
    }

    @PostMapping("confirm-thanh-toan/{hoadonId}")
    public ResponseEntity<String> confirmThanhToan(@PathVariable UUID hoadonId, @RequestBody XacNhanThanhToanRequest request) {
        hoaDonChiTietService.confirmThanhToan(hoadonId, request);
        return ResponseEntity.ok("Hóa đơn đã được thanh toán.");
    }
}
