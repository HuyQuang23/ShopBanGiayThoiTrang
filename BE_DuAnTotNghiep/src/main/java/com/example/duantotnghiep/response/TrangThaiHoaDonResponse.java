package com.example.duantotnghiep.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrangThaiHoaDonResponse {

    private Integer trangThai;

    private Date thoiGian;

    private String tenNhanVien;

    private String ghiChu;
}
