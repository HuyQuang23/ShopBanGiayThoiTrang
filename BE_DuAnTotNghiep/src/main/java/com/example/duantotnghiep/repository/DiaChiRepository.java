package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiaChiRepository extends JpaRepository<DiaChi, UUID> {
}
