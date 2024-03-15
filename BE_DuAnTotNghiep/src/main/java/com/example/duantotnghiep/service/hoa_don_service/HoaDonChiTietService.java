package com.example.duantotnghiep.service.hoa_don_service;

import com.example.duantotnghiep.request.TrangThaiHoaDonRequest;
import com.example.duantotnghiep.request.XacNhanThanhToanRequest;
import com.example.duantotnghiep.response.HinhThucThanhToanResponse;
import com.example.duantotnghiep.response.SanPhamHoaDonChiTietResponse;
import com.example.duantotnghiep.response.ThongTinDonHang;
import com.example.duantotnghiep.response.TrangThaiHoaDonResponse;

import java.util.List;
import java.util.UUID;

public interface HoaDonChiTietService {
    ThongTinDonHang getThongTinDonHang(UUID idHoaDon);

    List<SanPhamHoaDonChiTietResponse> getSanPhamHDCT(UUID idHoaDon);

    List<HinhThucThanhToanResponse> getLichSuThanhToan(UUID idHoaDon);

    List<TrangThaiHoaDonResponse> getAllTrangThaiHoaDon(UUID idHoaDon);

    void confirmThanhToan(UUID hoadonId, XacNhanThanhToanRequest request);

}
