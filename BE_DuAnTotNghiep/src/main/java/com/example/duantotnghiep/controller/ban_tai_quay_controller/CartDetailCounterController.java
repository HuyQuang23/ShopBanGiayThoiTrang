package com.example.duantotnghiep.controller.ban_tai_quay_controller;

import com.example.duantotnghiep.mapper.GioHangCustom;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.service.ban_tai_quay_service.impl.CartDetailCounterServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gio-hang-chi-tiet/")
public class CartDetailCounterController {

    @Autowired
    private CartDetailCounterServiceImpl gioHangChiTietService;

    @GetMapping("hien-thi")
    public ResponseEntity<List<GioHangCustom>> show(
            @RequestParam(name = "id") UUID id,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok(gioHangChiTietService.loadGH(id, pageNumber, pageSize));
    }

    @PostMapping("them-san-pham")
    public ResponseEntity<MessageResponse> themSanPhamVaoGioHangChiTiet(
            @RequestParam(name = "idGioHang") UUID idGioHang,
            @RequestParam(name = "idSanPhamChiTiet") UUID idSanPhamChiTiet,
            @RequestParam(name = "soLuong") int soLuong) {
        return new ResponseEntity<>(
                gioHangChiTietService.themSanPhamVaoGioHangChiTiet(idGioHang, idSanPhamChiTiet, soLuong),
                HttpStatus.CREATED);
    }

    @PostMapping("them-san-pham-qrcode")
    public ResponseEntity<MessageResponse> themSanPhamVaoGioHangChiTietQrcode(
            @RequestParam(name = "idGioHang") UUID idGioHang,
            @RequestParam(name = "qrCode") String qrCode) {
        return new ResponseEntity<>(
                gioHangChiTietService.themSanPhamVaoGioHangChiTietQrCode(idGioHang, qrCode),
                HttpStatus.CREATED);
    }

    @PutMapping("update-quantity")
    public ResponseEntity<String> capNhatSoLuong(
            @RequestParam(name = "idgiohangchitiet") UUID idgiohangchitiet,
            @RequestParam(name = "quantity") Integer quantity) {
        try {
            gioHangChiTietService.capNhatSoLuong(idgiohangchitiet, quantity);
            return ResponseEntity.ok("Số lượng đã được cập nhật.(-> nên xem lại Console log)");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm trong giỏ hàng.");
        }
    }

    @DeleteMapping("delete_product")
    public ResponseEntity<Void> deleteProductInCart(@RequestParam("id") UUID id) {
        gioHangChiTietService.deleteProductInCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
