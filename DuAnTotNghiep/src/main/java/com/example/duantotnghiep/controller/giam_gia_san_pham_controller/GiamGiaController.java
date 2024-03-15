package com.example.duantotnghiep.controller.giam_gia_san_pham_controller;

import com.example.duantotnghiep.request.GiamGiaRequest;
import com.example.duantotnghiep.response.*;
import com.example.duantotnghiep.service.giam_gia_service.impl.GiamGiaServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/giam-gia/")
public class GiamGiaController {

    @Autowired
    private GiamGiaServiceimpl Service;

    @GetMapping("show")
    public ResponseEntity<List<GiamGiaResponse>> getAllGiamGia() {
        return new ResponseEntity<>(Service.getAll(), HttpStatus.OK);
    }

    @GetMapping("showhh")
    public ResponseEntity<Page<GiamGiaResponse>> getAllGiamGia(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Page<GiamGiaResponse> resultPage = Service.getAll(PageRequest.of(page, size));
        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }

    // search by
    @GetMapping("searchString_bykey")
    public ResponseEntity<List<GiamGiaResponse>>findByKhachHangByIdHoaDon(@RequestParam(name = "key") String key) {
        return new ResponseEntity<>(Service.findbyValueString(key), HttpStatus.OK);
    }

    @GetMapping("showproduct")
    public ResponseEntity<List<ProductDetailResponse>> getAllProduct() {
        return new ResponseEntity<>(Service.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("detail")
    public ResponseEntity<List<ProductDetailResponse>> search(@RequestParam(name = "id") UUID id) {
        return new ResponseEntity<>(Service.ListSearch(id), HttpStatus.OK);
    }

    @GetMapping("detailList")
    public ResponseEntity<List<GiamGiaDetailResponse>> ListDetail(@RequestParam(name = "id") UUID id) {
        return new ResponseEntity<>(Service.ListGiamGiaDeatil(id), HttpStatus.OK);
    }

    @GetMapping("searchDatebykey")
    public ResponseEntity<List<GiamGiaResponse>> findByKhachHangB(
            @RequestParam(name = "key1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date key1,
            @RequestParam(name = "key2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date key2) {

        List<GiamGiaResponse> result = Service.findbyValueDate(key1, key2);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("searchProduct_bykey")
    public ResponseEntity<List<ProductDetailResponse>>findProduct(@RequestParam(name = "key") String key) {
        return new ResponseEntity<>(Service.findbyProduct(key), HttpStatus.OK);
    }

    @GetMapping("searchStatus_bykey")
    public ResponseEntity<List<GiamGiaResponse>>findByKha(@RequestParam(name = "key") Integer key) {
        return new ResponseEntity<>(Service.findbyValueStatus(key), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createKhachHang(@RequestBody GiamGiaRequest createKhachRequest) {
        return new ResponseEntity<>(Service.createGiamGia(createKhachRequest), HttpStatus.CREATED);
    }

    @GetMapping("checkTenGiamGia")
    public ResponseEntity<Boolean> checkTenGiamGia(@RequestParam String tenGiamGia) {
        boolean exists = Service.isTenGiamGiaExists(tenGiamGia);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("check-product-record-count")
    public ResponseEntity<Boolean> checkProductRecordCount(@RequestParam(name = "idsanpham") UUID productId) {
        boolean result = Service.checkProductRecordCount(productId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
