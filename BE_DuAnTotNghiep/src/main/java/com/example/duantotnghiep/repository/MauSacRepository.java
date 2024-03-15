package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, UUID> {

    List<MauSac> findByTrangThai(Integer trangThai);
}
