package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, UUID> {

    List<ThuongHieu> findByTrangThai(Integer trangThai);
}
