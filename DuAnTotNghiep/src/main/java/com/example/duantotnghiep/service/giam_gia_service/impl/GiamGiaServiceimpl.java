package com.example.duantotnghiep.service.giam_gia_service.impl;


import com.example.duantotnghiep.entity.*;
import com.example.duantotnghiep.repository.*;
import com.example.duantotnghiep.request.GiamGiaRequest;
import com.example.duantotnghiep.response.GiamGiaDetailResponse;
import com.example.duantotnghiep.response.GiamGiaResponse;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.response.ProductDetailResponse;
import com.example.duantotnghiep.service.giam_gia_service.GiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GiamGiaServiceimpl implements GiamGiaService {

    @Autowired
    private GiamGiaRepository Repository;

    @Autowired
    private SpGiamGiaRepository spggRepository;

    @Autowired
    private SanPhamRepository spRepository;

    @Override
    public List<GiamGiaResponse> getAll() {
        return Repository.listGiamGia();
    }

    @Override
    public Page<GiamGiaResponse> getAll(Pageable pageable) {
        return Repository.listGiamGias(pageable);
    }

    @Override
    public List<ProductDetailResponse> getAllProduct() {
        return Repository.ListProductResponse();
    }

    @Override
    public List<GiamGiaResponse> findbyValueString(String key) {
        return Repository.findbyValueString(key);
    }

    @Override
    public List<ProductDetailResponse> findbyProduct(String key) {
        return Repository.ProductDetailResponse(key);
    }

    @Override
    public List<GiamGiaResponse> findbyValueDate(Date key1, Date key2) {
        return Repository.findbyValueDate(key1,key2);
    }


    @Override
    public List<GiamGiaResponse> findbyValueStatus(Integer key) {
        return Repository.findbyValueStatus(key);
    }
    @Override
    public List<GiamGiaResponse> checkAndSetStatus() {
        List<GiamGia> giamGiaList = Repository.findAll();
        Date currentDate = new Date(); // Ngày hiện tại

        for (GiamGia giamGia : giamGiaList) {
            if (giamGia.getNgayKetThuc().before(currentDate)) {
                // Nếu ngày kết thúc đã qua so với ngày hiện tại
                if (giamGia.getTrangThai() == 1) {
                    // Nếu trạng thái là 1 (đang hoạt động), thì cập nhật trạng thái thành 2 (đã kết thúc)
                    giamGia.setTrangThai(2);
                    Repository.save(giamGia);
                }
            }
        }

        return Repository.listGiamGia();
    }

    @Override
    public List<ProductDetailResponse> ListSearch(UUID id) {
        return Repository.ListSearchDanhMuc(id);
    }

    @Override
    public List<GiamGiaDetailResponse> ListGiamGiaDeatil(UUID id) {
        return Repository.listGiamGiaDetail(id);
    }

    @Override
    public MessageResponse createGiamGia(GiamGiaRequest createKhachRequest) {
        // Tạo đối tượng GiamGia
        GiamGia giamGia = new GiamGia();
        giamGia.setId(UUID.randomUUID());
        giamGia.setMaGiamGia(createKhachRequest.getMaGiamGia());
        giamGia.setTenGiamGia(createKhachRequest.getTenGiamGia());
        giamGia.setNgayBatDau(createKhachRequest.getNgayBatDau());
        giamGia.setNgayKetThuc(createKhachRequest.getNgayKetThuc());
        giamGia.setHinhThucGiam(createKhachRequest.getHinhThucGiam());
        giamGia.setTrangThai(createKhachRequest.getTrangThai());
        Repository.save(giamGia);
        for (UUID sanPhamId : createKhachRequest.getIdsanpham()) {
            SanPham sanPham= spRepository.findById(sanPhamId).orElse(null);
            if (sanPham != null) {
                SpGiamGia spGiamGia = new SpGiamGia();
                spGiamGia.setId(UUID.randomUUID());
                spGiamGia.setMucGiam(createKhachRequest.getMucGiam());
                spGiamGia.setGiamGia(giamGia);
                spGiamGia.setSanPham(sanPham);
                if (createKhachRequest.getHinhThucGiam() == 1) {
                    // HinhThucGiam = 1, set donGiaKhiGiam = dongia - mucgiam
                    spGiamGia.setDonGiaKhiGiam(sanPham.getGiaBan().subtract(BigDecimal.valueOf(createKhachRequest.getMucGiam())));
                }else if (createKhachRequest.getHinhThucGiam() == 2) {
                    spGiamGia.setDonGiaKhiGiam(sanPham.getGiaBan().multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(createKhachRequest.getMucGiam()).divide(BigDecimal.valueOf(100)))));
                }
                spggRepository.save(spGiamGia);
            } else {
            }
        }
        // danh muc
        List<UUID> productIds = Repository.findProductIdsByDanhMucId(createKhachRequest.getIdDanhMuc());
        // Associate each product with the discount
        for (UUID sanPhamId : productIds) {
            SanPham sanPham = spRepository.findById(sanPhamId).orElse(null);
            if (sanPham != null) {
                SpGiamGia spGiamGia = new SpGiamGia();
                spGiamGia.setId(UUID.randomUUID());
                spGiamGia.setMucGiam(createKhachRequest.getMucGiam());
                spGiamGia.setGiamGia(giamGia);
                spGiamGia.setSanPham(sanPham);
                if (createKhachRequest.getHinhThucGiam() == 1) {
                    spGiamGia.setDonGiaKhiGiam(sanPham.getGiaBan().subtract(BigDecimal.valueOf(createKhachRequest.getMucGiam())));
                } else if (createKhachRequest.getHinhThucGiam() == 2) {
                    spGiamGia.setDonGiaKhiGiam(sanPham.getGiaBan().multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(createKhachRequest.getMucGiam()).divide(BigDecimal.valueOf(100)))));
                }
                spggRepository.save(spGiamGia);
            } else {
                // Handle the case where the product is not found
            }
        }
        //
        // Trả về thông báo thành công
        return MessageResponse.builder().message("Thêm Thành Công").build();
    }

    @Override
    public boolean isTenGiamGiaExists(String tenGiamGia) {
        return Repository.existsByTenGiamGia(tenGiamGia);
    }

    @Override
    public boolean checkProductRecordCount(UUID productId) {
        int recordCount = Repository.countByProductId(productId);
        return recordCount < 1;
    }



}
