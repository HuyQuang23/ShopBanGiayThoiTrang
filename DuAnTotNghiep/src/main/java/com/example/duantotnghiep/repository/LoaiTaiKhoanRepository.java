package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.LoaiTaiKhoan;
import com.example.duantotnghiep.enums.TypeAccountEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoaiTaiKhoanRepository extends JpaRepository<LoaiTaiKhoan, UUID> {

    Optional<LoaiTaiKhoan> findByName(TypeAccountEnum name);
}
