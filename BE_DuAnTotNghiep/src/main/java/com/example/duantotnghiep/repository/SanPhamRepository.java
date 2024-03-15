package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.SanPham;
import com.example.duantotnghiep.mapper.ChiTietSanPhamCustom;
import com.example.duantotnghiep.mapper.GioHangCustom;
import com.example.duantotnghiep.mapper.SoLuongGioHangCustom;
import com.example.duantotnghiep.mapper.TongTienCustom;
import com.example.duantotnghiep.response.SanPhamResponse;
import com.example.duantotnghiep.response.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {

    @Query(value = "SELECT TOP 8 sp.id, sp.tensanpham, im.tenimage, sp.giaban FROM sanpham sp JOIN sanphamchitiet spct ON sp.id = spct.idsanpham JOIN \n" +
            "image im ON spct.id = im.idsanphamchitiet ORDER BY sp.ngaytao DESC", nativeQuery = true)
    List<SanPhamResponse> getNewProduct();

}
