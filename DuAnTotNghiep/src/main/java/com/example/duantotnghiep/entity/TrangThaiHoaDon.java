package com.example.duantotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trangthaihoadon")
public class TrangThaiHoaDon {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "trangthai")
    private Integer trangThai;

    @Column(name = "thoigian")
    private Date thoiGian;

    @Column(name = "ghichu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "idhoadon")
    @JsonBackReference
    private HoaDon hoaDon;

}
