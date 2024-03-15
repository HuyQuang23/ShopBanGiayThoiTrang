package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, UUID> {

    List<DanhMuc> findByTrangThai(Integer trangThai);

}
