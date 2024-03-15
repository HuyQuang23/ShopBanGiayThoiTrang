package com.example.duantotnghiep.repository.mua_hang_not_login_repo;

import com.example.duantotnghiep.entity.HoaDon;
import com.example.duantotnghiep.response.IdGioHangResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HoaDonRepository_not_login extends JpaRepository<HoaDon, UUID> {

    @Query("SELECT new com.example.duantotnghiep.response.IdGioHangResponse(gh.id) " +
            "FROM HoaDon hd " +
            "JOIN hd.taiKhoanKhachHang tk " +
            "JOIN tk.gioHangList gh WHERE hd.trangThai = 1 AND tk.name = :name")
    IdGioHangResponse showIdGioHangCt(@Param("name") String name);
}
