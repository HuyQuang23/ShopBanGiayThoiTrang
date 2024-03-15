package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.TrangThaiHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface TrangThaiHoaDonRepository extends JpaRepository<TrangThaiHoaDon, UUID> {
}

