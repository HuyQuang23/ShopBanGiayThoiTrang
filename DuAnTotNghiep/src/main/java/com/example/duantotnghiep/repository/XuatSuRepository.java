package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.XuatXu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface XuatSuRepository extends JpaRepository<XuatXu, UUID> {

    List<XuatXu> findByTrangThai(Integer trangThai);
}
