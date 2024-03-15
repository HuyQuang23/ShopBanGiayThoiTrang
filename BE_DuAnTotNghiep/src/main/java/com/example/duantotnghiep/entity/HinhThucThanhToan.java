package com.example.duantotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hinhthucthanhtoan")
public class HinhThucThanhToan {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "ngaytao")
    private Date ngayThanhToan;

    @Column(name = "ngaycapnhap")
    private Date ngayCapNhap;

    @Column(name = "magiaodinh")
    private String codeTransaction;

    @Column(name = "sotientra")
    private BigDecimal tongSoTien;

    @Column(name = "phuongthucthanhtoan")
    private Integer phuongThucThanhToan;

    @Column(name = "ghichu")
    private String ghiChu;

    @Column(name = "trangthai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "idhoadon")
    @JsonBackReference
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "idtaikhoan")
    @JsonBackReference
    private TaiKhoan taiKhoan;

    @ManyToOne
    @JoinColumn(name = "idloai")
    @JsonBackReference
    private LoaiHinhThucThanhToan loaiHinhThucThanhToan;
}
