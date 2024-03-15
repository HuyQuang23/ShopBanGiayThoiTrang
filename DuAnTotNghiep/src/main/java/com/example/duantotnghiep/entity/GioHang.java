package com.example.duantotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "giohang")
public class GioHang {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idtaikhoan")
    @JsonBackReference
    private TaiKhoan taiKhoan;

    @Column(name = "ngaytao")
    private Date ngayTao;

    @Column(name = "ngaycapnhat")
    private Date ngayCapNhat;

    @Column(name = "ghichu")
    private String ghiChu;

    @Column(name = "trangthai")
    private Integer trangThai;

    @OneToMany(mappedBy = "gioHang",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<GioHangChiTiet> gioHangChiTietList;
}
