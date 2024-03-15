package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.TaiKhoan;
import com.example.duantotnghiep.response.KhachHangResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import java.util.UUID;

public interface KhachHangRepository extends JpaRepository<TaiKhoan, UUID> {

    @Query("SELECT new com.example.duantotnghiep.response.KhachHangResponse(tk.id, tk.name, tk.email, tk.soDienThoai, dc.diaChi) " +
            "FROM TaiKhoan tk JOIN tk.diaChiList dc WHERE tk.loaiTaiKhoan.name = 'USER' AND dc.trangThai = 1")
    List<KhachHangResponse> findlistKhachHang();

    @Query("SELECT new com.example.duantotnghiep.response.KhachHangResponse(tk.id, tk.name, tk.email, tk.soDienThoai, dc.diaChi) " +
            "FROM TaiKhoan tk JOIN tk.diaChiList dc WHERE tk.name = :key OR tk.soDienThoai = :key OR tk.email = :key AND dc.trangThai = 1")
    List<KhachHangResponse> findByKeyToKhachHang(@Param("key") String key);

    @Query("SELECT new com.example.duantotnghiep.response.KhachHangResponse(tk.id, tk.name, tk.email, tk.soDienThoai, dc.diaChi) " +
            "FROM TaiKhoan tk JOIN tk.diaChiList dc JOIN tk.hoaDonKhachHangList hd WHERE hd.id = :id")
    KhachHangResponse findByKhachHangByIdHoaDon(@Param("id") UUID id);

    @Query("SELECT new com.example.duantotnghiep.response.KhachHangResponse(tk.id, tk.name, tk.email, tk.soDienThoai, dc.diaChi) " +
            "FROM TaiKhoan tk JOIN tk.diaChiList dc WHERE tk.id = :id AND dc.trangThai = 1")
    KhachHangResponse detailKhachHang(@Param("id") UUID id);

}
