package com.example.duantotnghiep.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class XacNhanThanhToanRequest {
    private BigDecimal thanhTien;

    private String ghiChu;

    private Integer hinhThuc;
}
