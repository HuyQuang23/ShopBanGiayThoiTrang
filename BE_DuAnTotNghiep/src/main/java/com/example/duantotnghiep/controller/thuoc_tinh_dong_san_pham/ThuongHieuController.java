package com.example.duantotnghiep.controller.thuoc_tinh_dong_san_pham;

import com.example.duantotnghiep.entity.ThuongHieu;
import com.example.duantotnghiep.service.thuoc_tinh_dong_san_pham_service.impl.ThuongHieuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/thuong-hieu/")
public class ThuongHieuController {

    @Autowired
    private ThuongHieuServiceImpl thuongHieuService;

    @GetMapping("hien-thi")
    public ResponseEntity<List<ThuongHieu>> getAllSanPhamGiamGia() {
        return new ResponseEntity<>(thuongHieuService.getALL(), HttpStatus.OK);
    }
}
