package com.example.duantotnghiep.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SizeProductDetailResponse {

    private UUID id;

    private Integer size;

    private String kieuDe;

    private String chatLieu;

    private String mauSac;

    private Integer soLuong;
}
