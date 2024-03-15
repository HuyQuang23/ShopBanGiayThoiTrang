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
@Table(name = "danhmuc")
public class DanhMuc {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "tendanhmuc")
    private String tenDanhMuc;

    @Column(name = "trangthai")
    private Integer trangThai;

    @OneToMany(mappedBy = "danhMuc",fetch = FetchType.LAZY)
    @JsonManagedReference
    List<SanPham> sanPhamList;

}
