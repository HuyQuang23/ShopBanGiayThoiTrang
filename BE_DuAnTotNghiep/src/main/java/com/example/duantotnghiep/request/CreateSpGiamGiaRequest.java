package com.example.duantotnghiep.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSpGiamGiaRequest {

    @NotNull(message = "Không được để trông sản phẩm")
    private UUID idSanPham;

    @NotNull(message = "Vui lòng điền số lượng mã")
    @Positive(message = "Số lượng phải là số dương")
    private Integer soLuongMa;

    @NotNull(message = "Đơn giá không được để trống")
    private BigDecimal donGia;

    @NotNull(message = "Vui lòng điền giới hạn số tiền")
    private BigDecimal gioiHanSoTien;
}
