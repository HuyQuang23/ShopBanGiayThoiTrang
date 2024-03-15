package com.example.duantotnghiep.request;

import com.example.duantotnghiep.entity.SanPhamChiTiet;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiamGiaRequest {

    private String maGiamGia;
    private String tenGiamGia;
    private Date ngayBatDau;

    private Date ngayKetThuc;

    private Integer hinhThucGiam;
    private BigDecimal donGiaKhiGiam;
    private BigDecimal giaBan;
    private Integer trangThai;
    private Long mucGiam;
    private List<UUID> idsanpham;
    private UUID idDanhMuc;
    private UUID idThuongHieu;
    private UUID idChatLieu;
    private UUID idMauSac;
    private UUID idKieuDe;
    private UUID idSize;
    private UUID idXuatXu;
}
