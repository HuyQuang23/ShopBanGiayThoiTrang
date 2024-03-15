package com.example.duantotnghiep.response;

import com.example.duantotnghiep.entity.SpGiamGia;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GiamGiaResponse {

    private UUID id;

    private String maGiamGia;

    private String tenGiamGia;

    private Date ngayBatDau;

    private Date ngayKetThuc;

    private Integer hinhThucGiam;

    private Integer trangThai;
    private Long mucGiam;

    public GiamGiaResponse(UUID id, String maGiamGia, String tenGiamGia, Date ngayBatDau, Date ngayKetThuc, Integer hinhThucGiam, Integer trangThai) {
        this.id = id;
        this.maGiamGia = maGiamGia;
        this.tenGiamGia = tenGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hinhThucGiam = hinhThucGiam;
        this.trangThai = trangThai;
    }
//    public GiamGiaResponse(UUID id, String maGiamGia, String tenGiamGia, Date ngayBatDau, Date ngayKetThuc, Integer hinhThucGiam, Integer trangThai, Long mucGiam) {
//        this.id = id;
//        this.maGiamGia = maGiamGia;
//        this.tenGiamGia = tenGiamGia;
//        this.ngayBatDau = ngayBatDau;
//        this.ngayKetThuc = ngayKetThuc;
//        this.hinhThucGiam = hinhThucGiam;
//        this.trangThai = trangThai;
//        this.mucGiam = mucGiam;
//    }

//    public GiamGiaResponse(UUID id, String maGiamGia, String tenGiamGia, Date ngayBatDau, Date ngayKetThuc, Integer hinhThucGiam, Integer trangThai) {
//        this.id = id;
//        this.maGiamGia = maGiamGia;
//        this.tenGiamGia = tenGiamGia;
//        this.ngayBatDau = ngayBatDau;
//        this.ngayKetThuc = ngayKetThuc;
//        this.hinhThucGiam = hinhThucGiam;
//        this.trangThai = trangThai;
//    }
}
