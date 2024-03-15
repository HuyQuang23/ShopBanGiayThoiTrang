package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.HinhThucThanhToan;

import com.example.duantotnghiep.response.HinhThucThanhToanResponse;
import com.example.duantotnghiep.response.ThongTinDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.duantotnghiep.response.TransactionResponse;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HinhThucThanhToanRepository extends JpaRepository<HinhThucThanhToan, UUID> {


    @Query("SELECT new com.example.duantotnghiep.response.TransactionResponse(httt.codeTransaction, httt.tongSoTien, httt.phuongThucThanhToan) "
            +
            "FROM HinhThucThanhToan httt JOIN httt.hoaDon hd where hd.id = :id")
    List<TransactionResponse> findAllTran(@Param("id") UUID id);

}
