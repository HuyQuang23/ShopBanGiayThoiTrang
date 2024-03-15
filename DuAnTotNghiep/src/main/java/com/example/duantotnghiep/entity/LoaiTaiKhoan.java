package com.example.duantotnghiep.entity;

import com.example.duantotnghiep.enums.TypeAccountEnum;
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
@Table(name = "loaitaikhoan")
public class LoaiTaiKhoan {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "tenloaitaikhoan")
    @Enumerated(EnumType.STRING)
    private TypeAccountEnum name;

    @Column(name = "trangthai")
    private Integer trangThai;

    @OneToMany(mappedBy = "loaiTaiKhoan", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<TaiKhoan> taiKhoanList;

}
