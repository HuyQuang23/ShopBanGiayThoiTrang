package com.example.duantotnghiep.service.hoa_don_service;

import com.example.duantotnghiep.request.TrangThaiHoaDonRequest;

import java.util.UUID;

public interface TrangThaiHoaDonService {
    void confirmOrder(UUID hoadonId, TrangThaiHoaDonRequest request);
}
