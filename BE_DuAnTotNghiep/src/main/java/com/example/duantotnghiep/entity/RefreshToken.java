package com.example.duantotnghiep.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "refeshtoken")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "thoi_gian_het_han")
    private LocalDate thoiGianHetHan;

    @ManyToOne
    @JoinColumn(name = "tai_Khoan_id")
    private TaiKhoan taiKhoan;

}