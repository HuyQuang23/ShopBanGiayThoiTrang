package com.example.duantotnghiep.service.giam_gia_service;

import com.example.duantotnghiep.request.GiamGiaRequest;
import com.example.duantotnghiep.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface GiamGiaService {

    List<GiamGiaResponse> getAll();

    Page<GiamGiaResponse> getAll(Pageable pageable);

    List<ProductDetailResponse> getAllProduct();

    List<GiamGiaResponse> findbyValueString(String key);

    List<ProductDetailResponse> findbyProduct(String key);

    List<GiamGiaResponse> findbyValueDate(Date key1, Date key2);

    List<GiamGiaResponse> findbyValueStatus(Integer key);

    List<GiamGiaResponse> checkAndSetStatus();

    List<ProductDetailResponse> ListSearch(UUID id);

    List<GiamGiaDetailResponse> ListGiamGiaDeatil(UUID id);

    MessageResponse createGiamGia(GiamGiaRequest createKhachRequest);

    boolean isTenGiamGiaExists(String tenGiamGia);

    boolean checkProductRecordCount(UUID productId);

}
