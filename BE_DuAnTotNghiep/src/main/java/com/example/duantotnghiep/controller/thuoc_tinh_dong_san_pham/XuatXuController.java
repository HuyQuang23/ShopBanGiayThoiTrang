package com.example.duantotnghiep.controller.thuoc_tinh_dong_san_pham;

import com.example.duantotnghiep.entity.XuatXu;
import com.example.duantotnghiep.service.thuoc_tinh_dong_san_pham_service.impl.XuatXuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/xuat-xu/")
public class XuatXuController {

    @Autowired
    private XuatXuServiceImpl xuatXuService;

    @GetMapping("show")
    public ResponseEntity<List<XuatXu>> getAllSanPhamGiamGia() {
        return new ResponseEntity<>(xuatXuService.getAll(), HttpStatus.OK);
    }
}
