package com.example.duantotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "xuatxu")
public class XuatXu {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "tenxuatxu")
    private String tenXuatXu;

    @Column(name = "trangthai")
    private Integer trangThai;

    @OneToMany(mappedBy = "xuatXu",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SanPham> sanPhamList;

}
