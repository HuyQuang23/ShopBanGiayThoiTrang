package com.example.duantotnghiep.controller.ban_tai_quay_controller;

import com.example.duantotnghiep.request.CreateKhachRequest;
import com.example.duantotnghiep.response.KhachHangResponse;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.service.ban_tai_quay_service.impl.CustomerCounterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/khach-hang/")
public class CustomerCounterController {

    @Autowired
    private CustomerCounterServiceImpl khachHangService;

    @GetMapping("hien-thi")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(khachHangService.getKhachHang());
    }

    @GetMapping("search")
    public ResponseEntity<List<KhachHangResponse>> search(@RequestParam(name = "key") String key) {
        return new ResponseEntity<>(khachHangService.findByKeyToKhachHang(key), HttpStatus.OK);
    }

    @GetMapping("detail")
    public ResponseEntity<KhachHangResponse> search(@RequestParam(name = "id") UUID id) {
        return new ResponseEntity<>(khachHangService.detailKhachHang(id), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createKhachHang(@RequestBody CreateKhachRequest createKhachRequest) {
        return new ResponseEntity<>(khachHangService.createKhachHang(createKhachRequest), HttpStatus.CREATED);
    }

    @PutMapping("update-hoa-don")
    public ResponseEntity<MessageResponse> updateHoaDon(
            @RequestParam(name = "id") UUID id,
            @RequestParam(name = "idHoaDon") UUID idHoaDon,
            @RequestParam(name = "idGioHang") UUID idGioHang
    ) {
        return new ResponseEntity<>(khachHangService.updateHoaDon(id, idHoaDon, idGioHang), HttpStatus.OK);
    }

}
