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
public class ProductDetailResponse {

    private UUID id;
    private String image;
    private String productName;
    private Integer SizeName;
    private String KieuDeName;
    private String MauSacName;
    private String ChatLieuName;
    private Integer trangThai;
    private  UUID idImage;
    private  UUID idSize;
    private  UUID idMauSac;
    private  UUID idChatLieu;
    private  UUID idKieuDe;

    public ProductDetailResponse(UUID id, String image, String productName, Integer sizeName, String kieuDeName, String mauSacName, String chatLieuName, Integer trangThai) {
        this.id = id;
        this.image = image;
        this.productName = productName;
        SizeName = sizeName;
        KieuDeName = kieuDeName;
        MauSacName = mauSacName;
        ChatLieuName = chatLieuName;
        this.trangThai = trangThai;
    }
}