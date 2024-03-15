package com.example.duantotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spgiamgia")
public class SpGiamGia {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idsanpham")
    @JsonBackReference
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "idgiamgia")
    @JsonBackReference
    private GiamGia giamGia;

    @Column(name = "dongia")
    private BigDecimal donGia;

    @Column(name = "trangthai")
    private Integer trangThai;

    @Column(name = "dongiakhigiam")
    private BigDecimal donGiaKhiGiam;

    @Column(name = "mucgiam")
    private Long mucGiam;

}
