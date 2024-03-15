package com.example.duantotnghiep.repository.mua_hang_not_login_repo;

import com.example.duantotnghiep.entity.LoaiDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoaiDonRepository_not_login extends JpaRepository<LoaiDon, UUID> {

    Optional<LoaiDon> findByTrangThai(Integer id);
}
