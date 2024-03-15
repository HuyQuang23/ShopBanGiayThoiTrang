package com.example.duantotnghiep.repository.mua_hang_not_login_repo;

import com.example.duantotnghiep.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface KhachHangRepository_not_login extends JpaRepository<TaiKhoan, UUID> {
    @Query(value = "SELECT * FROM taikhoan where email = ?1 AND sodienthoai=?2",nativeQuery = true)
    List<TaiKhoan> getKhachHangByEmailAndSdt(String email, String sodienthoai);
}
