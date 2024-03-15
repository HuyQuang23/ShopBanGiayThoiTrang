package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SizeRepository extends JpaRepository<Size, UUID> {

    List<Size> findByTrangThai(Integer trangThai);
}
