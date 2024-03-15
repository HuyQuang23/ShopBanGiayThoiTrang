package com.example.duantotnghiep.controller.giam_gia_san_pham_controller;

import com.example.duantotnghiep.mapper.not_login.*;
import com.example.duantotnghiep.repository.SpGiamGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/san-pham-giam-gia/")
public class SanPhamGiamGiaController {

    @Autowired
    private SpGiamGiaRepository spGiamGiaRepository;

    @GetMapping("show")
    public ResponseEntity<List<loadsanpham_not_login>> show() {
        return ResponseEntity.ok(spGiamGiaRepository.getAllSpGiamGia());
    }
    @GetMapping("show-sp-lien-quan")
    public ResponseEntity<List<loadsanpham_not_login>> showSpLienQuan(@RequestParam UUID idthuonghieu) {
        return ResponseEntity.ok(spGiamGiaRepository.getSanPhamLienQuan(idthuonghieu));
    }


    @GetMapping("show-name-price-image/{name}")
    public ResponseEntity<loadsanpham_not_login> getNamePriceImage(@PathVariable String name) {
        return ResponseEntity.ok(spGiamGiaRepository.getNamePriceImageByIdSanPham(name));
    }

    @GetMapping("show-all-mau-sac/{name}")
    public ResponseEntity<List<loadmausac_not_login>> getAllMauSac(@PathVariable String name) {
        return new ResponseEntity<>(spGiamGiaRepository.getAllMauSac(name), HttpStatus.OK);
    }

    @GetMapping("show-all-size/{name}")
    public ResponseEntity<List<loadsize_not_login>> getAllSize(@PathVariable String name) {
        return new ResponseEntity<>(spGiamGiaRepository.getAllSize(name), HttpStatus.OK);
    }

    @GetMapping("show-all-chat-lieu/{name}")
    public ResponseEntity<List<loadchatlieu_not_login>> getAllChatLieu(@PathVariable String name) {
        return new ResponseEntity<>(spGiamGiaRepository.getAllChatLieu(name), HttpStatus.OK);
    }

    @GetMapping("find-by-mau-sac/{name}")
    public ResponseEntity<List<loadsize_chatlieu_not_login>> findByMauSac(@PathVariable(name = "name") String name, @RequestParam UUID idmausac) {
        return new ResponseEntity<>(spGiamGiaRepository.findSizeChatLieu(name, idmausac), HttpStatus.OK);
    }

    @GetMapping("find-by-size/{name}")
    public ResponseEntity<List<loadmausac_chatlieu_not_login>> findMauSacChatLieuBySize(@PathVariable String
                                                                                                name, @RequestParam UUID idsize) {
        return new ResponseEntity<>(spGiamGiaRepository.findMauSacChatLieu(name, idsize), HttpStatus.OK);
    }

    @GetMapping("find-by-chat-lieu/{name}")
    public ResponseEntity<List<loadmausac_size_not_login>> findSizeMauSacByChatLieu(@PathVariable String
                                                                                            name, @RequestParam UUID idchatlieu) {
        return new ResponseEntity<>(spGiamGiaRepository.findSizeMauSac(name, idchatlieu), HttpStatus.OK);
    }
    
    @GetMapping("find-idspct-soluong/{name}")
    public ResponseEntity<findIdSpctAndSoLuong_not_login> findIdSpctAndSoLuong_not_login(
            @RequestParam UUID idmausac,
            @RequestParam UUID idsize,
            @RequestParam UUID idchatlieu,
            @PathVariable String name) {
        return new ResponseEntity<>(spGiamGiaRepository.findIdspctAndSoluong(idmausac, idsize, idchatlieu, name), HttpStatus.OK);
    }

}