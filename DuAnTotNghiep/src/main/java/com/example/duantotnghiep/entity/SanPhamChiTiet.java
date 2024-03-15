package com.example.duantotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sanphamchitiet")
public class SanPhamChiTiet {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idsanpham")
    @JsonBackReference
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "idmausac")
    @JsonBackReference
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "idchatlieu")
    @JsonBackReference
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "idsize")
    @JsonBackReference
    private Size size;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "qrcode")
    private String qrcode;

    @Column(name = "trangthai")
    private Integer trangThai;

    @OneToMany(mappedBy = "sanPhamChiTiet", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<GioHangChiTiet> gioHangChiTietList;

    @OneToMany(mappedBy = "sanPhamChiTiet", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<HoaDonChiTiet> hoaDonChiTietList;


}
