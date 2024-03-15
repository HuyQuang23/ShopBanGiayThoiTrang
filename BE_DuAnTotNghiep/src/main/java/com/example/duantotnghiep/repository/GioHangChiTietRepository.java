package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.GioHang;
import com.example.duantotnghiep.entity.GioHangChiTiet;
import com.example.duantotnghiep.mapper.GioHangCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.duantotnghiep.entity.GioHangChiTiet;
import com.example.duantotnghiep.mapper.GioHangCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, UUID> {

    @Query("SELECT sp.id, ghct.id, i.tenImage, sp.tenSanPham, sp.giaBan,ghct.soLuong, s.size, cl.tenChatLieu,ms.tenMauSac " +
            "FROM SanPhamChiTiet spct " +
            "JOIN spct.sanPham sp " +
            "JOIN sp.listImage i " +
            "JOIN spct.size s " +
            "JOIN spct.mauSac ms " +
            "JOIN spct.chatLieu cl " +
            "JOIN spct.gioHangChiTietList ghct " +
            "JOIN ghct.gioHang gh " +
            "JOIN gh.taiKhoan tk " +
            "WHERE i.isDefault = true AND tk.id = :id")
    Page<Object[]> loadOnGioHang(@Param("id") UUID id, Pageable pageable);

    // Tìm mục trong giỏ hàng chi tiết dựa trên idGioHang và idSanPhamChiTiet
    GioHangChiTiet findByGioHangAndSanPhamChiTiet_Id(GioHang gioHang, UUID idSanPhamChiTiet);

    GioHangChiTiet findByGioHang(GioHang gioHang);

}
