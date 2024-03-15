package com.example.duantotnghiep.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GiamGiaDetailResponse {
    private UUID id;
    private String productName;
    private BigDecimal donGiaKhiGiam;
   private BigDecimal giaBan;

}
