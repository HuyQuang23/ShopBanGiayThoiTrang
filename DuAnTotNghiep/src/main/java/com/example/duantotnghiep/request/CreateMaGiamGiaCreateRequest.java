package com.example.duantotnghiep.request;

import com.example.duantotnghiep.entity.SpGiamGia;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaGiamGiaCreateRequest {

    @NotBlank(message = "Vui lòng nhập mã giảm giá")
    private String maGiamGia;

    @NotBlank(message = "Vui lòng nhập tên giảm giá")
    private String tenGiamGia;

    @NotBlank(message = "Vui lòng nhập mô tả giảm giá")
    private String moTa;

    @NotBlank(message = "Vui lòng nhập loại giảm giá")
    private String loaiGiamGia;

    @NotNull(message = "Ngày bắt đầu không được bỏ trống")
    private Date ngayBatDau;

    @NotNull(message = "Ngày kết thúc không được bỏ trống")
    private Date ngayKetThuc;

    @NotNull(message = "Giá trị giảm giá không được bỏ trống")
    @Min(value = 0, message = "Giá trị giảm giá phải lớn hơn hoặc bằng 0")
    private Integer giaTriGiam;

    @NotNull(message = "Hình thức giảm giá không được bỏ trống")
    private Integer hinhThucGiam;

    @NotNull(message = "Điều kiện giảm giá không được bỏ trống")
    private Integer dieuKienGiamGia;

}
