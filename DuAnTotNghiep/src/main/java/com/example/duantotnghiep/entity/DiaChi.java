package com.example.duantotnghiep.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diachi")
public class DiaChi {

    @Id
    private UUID id;

    @Column(name = "diachi")
    private String diaChi;

    @Column(name = "xa")
    private String xa;

    @Column(name = "huyen")
    private String huyen;

    @Column(name = "tinh")
    private String tinh;

    @Column(name = "quocgia")
    private String quocGia;

    @Column(name = "trangthai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "idtaikhoan")
    private TaiKhoan taiKhoan;
}
