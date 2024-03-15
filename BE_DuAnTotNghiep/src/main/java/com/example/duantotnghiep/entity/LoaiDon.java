package com.example.duantotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loaidon")
public class LoaiDon {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "tenloaidon")
    private String tenLoaiDon;

    @Column(name = "trangthai")
    private Integer trangThai;

    @OneToMany(mappedBy = "loaiDon",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<HoaDon> hoaDonList;
}
