package com.example.duantotnghiep.service.ban_tai_quay_service;

import com.example.duantotnghiep.response.MessageResponse;

import java.util.UUID;

public interface CartCounterService {

    MessageResponse updateGioHang(UUID idGioHang, UUID idAccount);
}
