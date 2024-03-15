package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.KieuDe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KieuDeRepository extends JpaRepository<KieuDe, UUID> {

    List<KieuDe> findByTrangThai(Integer trangThai);
}
