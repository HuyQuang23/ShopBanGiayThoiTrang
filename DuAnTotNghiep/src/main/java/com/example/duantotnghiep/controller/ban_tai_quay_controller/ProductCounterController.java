package com.example.duantotnghiep.controller.ban_tai_quay_controller;

import com.example.duantotnghiep.mapper.ChiTietSanPhamCustom;
import com.example.duantotnghiep.service.ban_tai_quay_service.impl.ProductCounterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/chi-tiet-sp/")
public class ProductCounterController {

    @Autowired
    private ProductCounterServiceImpl chiTietSanPhamService;

    @GetMapping("hien-thi")
    public ResponseEntity<List<ChiTietSanPhamCustom>> getAll(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok(chiTietSanPhamService.getAll(pageNumber, pageSize));
    }

    @GetMapping("search-name")
    public ResponseEntity<List<ChiTietSanPhamCustom>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(chiTietSanPhamService.searchByName(name));
    }

    @GetMapping("filter-brand")
    public ResponseEntity<List<ChiTietSanPhamCustom>> filterBrand(@RequestParam String name) {
        return ResponseEntity.ok(chiTietSanPhamService.filterBrand(name));
    }

    @GetMapping("filter-category")
    public ResponseEntity<List<ChiTietSanPhamCustom>> filterCategory(@RequestParam String name) {
        return ResponseEntity.ok(chiTietSanPhamService.filterCategory(name));
    }

    @GetMapping("filter-sole")
    public ResponseEntity<List<ChiTietSanPhamCustom>> filterSole(@RequestParam String name) {
        return ResponseEntity.ok(chiTietSanPhamService.filterSole(name));
    }

    @GetMapping("filter-origin")
    public ResponseEntity<List<ChiTietSanPhamCustom>> filterOrigin(@RequestParam String name) {
        return ResponseEntity.ok(chiTietSanPhamService.filterOrigin(name));
    }

    @GetMapping("filter-size")
    public ResponseEntity<List<ChiTietSanPhamCustom>> filterSize(@RequestParam Integer size) {
        return ResponseEntity.ok(chiTietSanPhamService.filterSize(size));
    }

    @GetMapping("filter-material")
    public ResponseEntity<List<ChiTietSanPhamCustom>> filterMaterial(@RequestParam String name) {
        return ResponseEntity.ok(chiTietSanPhamService.filterMaterial(name));
    }

    @GetMapping("filter-color")
    public ResponseEntity<List<ChiTietSanPhamCustom>> filterColor(@RequestParam String name) {
        return ResponseEntity.ok(chiTietSanPhamService.filterColor(name));
    }

    @GetMapping("tien-cuoi-cung")
    public Long getGiaGiamCuoiCung(@RequestParam UUID id) {
        return chiTietSanPhamService.getGiaGiamCuoiCung(id);
    }

}
