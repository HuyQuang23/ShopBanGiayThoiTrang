package com.example.duantotnghiep.controller.mua_hang_not_login_controller;

import com.example.duantotnghiep.service.mua_hang_not_login_impl.GioHangChiTietServiceImpl_not_login;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/gio-hang-chi-tiet-not-login")
public class GioHangChiTietController_not_login {


    @Autowired
    GioHangChiTietServiceImpl_not_login gioHangServiceImpl_not_login;

    @GetMapping("hien-thi")
    public ResponseEntity<?> show(@RequestParam UUID idgh){
        return ResponseEntity.ok(gioHangServiceImpl_not_login.loadGH(idgh));
    }

    @PostMapping("/them-san-pham")
    public ResponseEntity<String> themSanPhamVaoGioHangChiTiet(@RequestParam UUID idGioHang, @RequestParam UUID idSanPhamChiTiet, @RequestParam int soLuong) {
        try {
            gioHangServiceImpl_not_login.themSanPhamVaoGioHangChiTiet(idGioHang, idSanPhamChiTiet, soLuong);
            return ResponseEntity.ok("Sản phẩm đã được thêm vào giỏ hàng chi tiết.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không thể thêm sản phẩm vào giỏ hàng chi tiết.");
        }
    }

    @PutMapping("/update-quantity")
    public ResponseEntity<String> capNhatSoLuong(@RequestParam UUID idgiohangchitiet, @RequestParam int quantity) {
        try {
            gioHangServiceImpl_not_login.capNhatSoLuong(idgiohangchitiet, quantity);
            return ResponseEntity.ok("Số lượng đã được cập nhật.(-> nên xem lại Console log)");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm trong giỏ hàng.");
        }
    }
    @DeleteMapping("/xoa-san-pham")
    public ResponseEntity<String> xoaSanPhamKhoiGioHang(@RequestParam UUID idgiohangchitiet) {
        try {
            gioHangServiceImpl_not_login.xoaSanPhamKhoiGioHang(idgiohangchitiet);
            return ResponseEntity.ok("Sản phẩm đã được xóa khỏi giỏ hàng chi tiết.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm trong giỏ hàng.");
        }
    }
    //xóa tất cả sản phẩm trong giỏ hàng
    @DeleteMapping("/xoa-tat-ca-san-pham")
    public ResponseEntity<String> xoaTatCaSanPhamKhoiGioHang(@RequestParam UUID idGioHang) {
        try {
            gioHangServiceImpl_not_login.xoaTatCaSanPhamKhoiGioHang(idGioHang);
            return ResponseEntity.ok("Tất cả sản phẩm đã được xóa khỏi giỏ hàng.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Không tìm thấy giỏ hàng.");
        }
    }

    // lấy tổng tiền
    @GetMapping("/total-amount")
    public ResponseEntity<?> getAmount(@RequestParam UUID idgh){
        return ResponseEntity.ok(gioHangServiceImpl_not_login.getTongTien(idgh));
    }
    //lấy tên với tổng tiền trên từng sp
    @GetMapping("/name-quantity")
    public ResponseEntity<?> getNameQuantity(@RequestParam UUID idgh){
        return ResponseEntity.ok(gioHangServiceImpl_not_login.getNameAndQuantity(idgh));
    }
}
