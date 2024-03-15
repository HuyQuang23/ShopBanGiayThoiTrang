package com.example.duantotnghiep.service.thuoc_tinh_dong_san_pham_service.impl;

import com.example.duantotnghiep.entity.KieuDe;
import com.example.duantotnghiep.repository.KieuDeRepository;
import com.example.duantotnghiep.service.thuoc_tinh_dong_san_pham_service.KieuDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KieuDeServiceImpl implements KieuDeService {

    @Autowired
    private KieuDeRepository kieuDeRepository;

    @Override
    public List<KieuDe> getAll() {
        return kieuDeRepository.findByTrangThai(1);
    }
}
