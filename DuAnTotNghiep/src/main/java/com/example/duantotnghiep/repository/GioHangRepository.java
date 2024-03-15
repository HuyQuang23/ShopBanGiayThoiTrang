package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, UUID> {

    @Query("SELECT gh FROM GioHang gh where gh.id = :id")
    GioHang findByGioHang(UUID id);
}
