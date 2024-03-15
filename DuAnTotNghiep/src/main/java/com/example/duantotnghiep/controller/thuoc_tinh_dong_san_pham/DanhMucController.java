package com.example.duantotnghiep.controller.thuoc_tinh_dong_san_pham;

import com.example.duantotnghiep.entity.DanhMuc;
import com.example.duantotnghiep.service.thuoc_tinh_dong_san_pham_service.impl.DanhMucServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/danh-muc/")
public class DanhMucController {

    @Autowired
    private DanhMucServiceImpl danhMucService;

    @GetMapping("show")
    public ResponseEntity<List<DanhMuc>> getAllSanPhamGiamGia() {
        return new ResponseEntity<>(danhMucService.getAll(), HttpStatus.OK);
    }
}
