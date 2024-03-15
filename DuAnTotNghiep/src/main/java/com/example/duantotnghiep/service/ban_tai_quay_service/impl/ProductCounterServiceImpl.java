package com.example.duantotnghiep.service.ban_tai_quay_service.impl;

import com.example.duantotnghiep.entity.SpGiamGia;
import com.example.duantotnghiep.mapper.ChiTietSanPhamCustom;
import com.example.duantotnghiep.repository.ChiTietSanPhamRepository;
import com.example.duantotnghiep.repository.SpGiamGiaRepository;
import com.example.duantotnghiep.response.DetailQuantityToSizeReponse;
import com.example.duantotnghiep.response.SanPhamGetAllResponse;
import com.example.duantotnghiep.service.ban_tai_quay_service.ProductCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductCounterServiceImpl implements ProductCounterService {

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private SpGiamGiaRepository spGiamGiaRepository;

    public Long getGiaGiamCuoiCung(UUID id) {
        long sumPriceTien = 0L;
        long sumPricePhanTram = 0L;
        List<SpGiamGia> spGiamGiaList = spGiamGiaRepository.findBySanPham_Id(id);

        for (SpGiamGia spGiamGia : spGiamGiaList) {
            long mucGiam = spGiamGia.getMucGiam();
            if (spGiamGia.getGiamGia().getHinhThucGiam() == 1) {
                System.out.println(mucGiam + "đ");
                sumPriceTien += mucGiam;
            }
            if (spGiamGia.getGiamGia().getHinhThucGiam() == 2) {
                System.out.println(mucGiam + "%");
                long donGiaAsLong = spGiamGia.getDonGia().longValue();
                double giamGia = (double) mucGiam / 100;
                long giaTienSauGiamGia = (long) (donGiaAsLong * giamGia);
                sumPricePhanTram += giaTienSauGiamGia;
            }
        }
        return sumPriceTien + sumPricePhanTram;
    }

    @Override
    public List<ChiTietSanPhamCustom> getAll(Integer pageNumber, Integer pageSize) {
        List<ChiTietSanPhamCustom> resultList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Object[]> queryResult = chiTietSanPhamRepository.getAll(pageable);
        for (Object[] result : queryResult.getContent()) {
            UUID idSp = (UUID) result[0];
            UUID idCtsp = (UUID) result[1];
            String imgage = (String) result[2];
            String tenSanPham = (String) result[3];
            BigDecimal giaBan = (BigDecimal) result[4];
            Integer soLuong = (Integer) result[5];
            String mauSac = (String) result[6];
            Integer size = (Integer) result[7];
            String chatLieu = (String) result[8];
            BigDecimal giaGiam = new BigDecimal(getGiaGiamCuoiCung(idSp));

            ChiTietSanPhamCustom chiTietSanPhamCustom = new ChiTietSanPhamCustom(
                    idCtsp, imgage, tenSanPham, giaBan, giaGiam, soLuong, mauSac, size, chatLieu);
            resultList.add(chiTietSanPhamCustom);
        }
        return resultList;
    }

    @Override
    public List<ChiTietSanPhamCustom> searchByName(String name) {
        List<ChiTietSanPhamCustom> resultList = new ArrayList<>();
        List<Object[]> queryResult = chiTietSanPhamRepository.searchByName(name);
        for (Object[] result : queryResult) {
            UUID idSp = (UUID) result[0];
            UUID idCtsp = (UUID) result[1];
            String imgage = (String) result[2];
            String tenSanPham = (String) result[3];
            BigDecimal giaBan = (BigDecimal) result[4];
            Integer soLuong = (Integer) result[5];
            String mauSac = (String) result[6];
            Integer size = (Integer) result[7];
            String chatLieu = (String) result[8];
            BigDecimal giaGiam = new BigDecimal(getGiaGiamCuoiCung(idSp));

            ChiTietSanPhamCustom chiTietSanPhamCustom = new ChiTietSanPhamCustom(
                    idCtsp, imgage, tenSanPham, giaBan, giaGiam, soLuong, mauSac, size, chatLieu);
            resultList.add(chiTietSanPhamCustom);
        }
        return resultList;
    }

    @Override
    public List<ChiTietSanPhamCustom> filterBrand(String name) {
        List<ChiTietSanPhamCustom> resultList = new ArrayList<>();
        List<Object[]> queryResult = chiTietSanPhamRepository.filterBrand(name);
        for (Object[] result : queryResult) {
            UUID idSp = (UUID) result[0];
            UUID idCtsp = (UUID) result[1];
            String imgage = (String) result[2];
            String tenSanPham = (String) result[3];
            BigDecimal giaBan = (BigDecimal) result[4];
            Integer soLuong = (Integer) result[5];
            String mauSac = (String) result[6];
            Integer size = (Integer) result[7];
            String chatLieu = (String) result[8];
            BigDecimal giaGiam = new BigDecimal(getGiaGiamCuoiCung(idSp));

            ChiTietSanPhamCustom chiTietSanPhamCustom = new ChiTietSanPhamCustom(
                    idCtsp, imgage, tenSanPham, giaBan, giaGiam, soLuong, mauSac, size, chatLieu);
            resultList.add(chiTietSanPhamCustom);
        }
        return resultList;
    }

    @Override
    public List<ChiTietSanPhamCustom> filterCategory(String name) {
        List<ChiTietSanPhamCustom> resultList = new ArrayList<>();
        List<Object[]> queryResult = chiTietSanPhamRepository.filterCategory(name);
        for (Object[] result : queryResult) {
            UUID idSp = (UUID) result[0];
            UUID idCtsp = (UUID) result[1];
            String imgage = (String) result[2];
            String tenSanPham = (String) result[3];
            BigDecimal giaBan = (BigDecimal) result[4];
            Integer soLuong = (Integer) result[5];
            String mauSac = (String) result[6];
            Integer size = (Integer) result[7];
            String chatLieu = (String) result[8];
            BigDecimal giaGiam = new BigDecimal(getGiaGiamCuoiCung(idSp));

            ChiTietSanPhamCustom chiTietSanPhamCustom = new ChiTietSanPhamCustom(
                    idCtsp, imgage, tenSanPham, giaBan, giaGiam, soLuong, mauSac, size, chatLieu);
            resultList.add(chiTietSanPhamCustom);
        }
        return resultList;
    }

    @Override
    public List<ChiTietSanPhamCustom> filterSole(String name) {
        List<ChiTietSanPhamCustom> resultList = new ArrayList<>();
        List<Object[]> queryResult = chiTietSanPhamRepository.filterSole(name);
        for (Object[] result : queryResult) {
            UUID idSp = (UUID) result[0];
            UUID idCtsp = (UUID) result[1];
            String imgage = (String) result[2];
            String tenSanPham = (String) result[3];
            BigDecimal giaBan = (BigDecimal) result[4];
            Integer soLuong = (Integer) result[5];
            String mauSac = (String) result[6];
            Integer size = (Integer) result[7];
            String chatLieu = (String) result[8];
            BigDecimal giaGiam = new BigDecimal(getGiaGiamCuoiCung(idSp));

            ChiTietSanPhamCustom chiTietSanPhamCustom = new ChiTietSanPhamCustom(
                    idCtsp, imgage, tenSanPham, giaBan, giaGiam, soLuong, mauSac, size, chatLieu);
            resultList.add(chiTietSanPhamCustom);
        }
        return resultList;
    }

    @Override
    public List<ChiTietSanPhamCustom> filterOrigin(String name) {
        List<ChiTietSanPhamCustom> resultList = new ArrayList<>();
        List<Object[]> queryResult = chiTietSanPhamRepository.filterOrigin(name);
        for (Object[] result : queryResult) {
            UUID idSp = (UUID) result[0];
            UUID idCtsp = (UUID) result[1];
            String imgage = (String) result[2];
            String tenSanPham = (String) result[3];
            BigDecimal giaBan = (BigDecimal) result[4];
            Integer soLuong = (Integer) result[5];
            String mauSac = (String) result[6];
            Integer size = (Integer) result[7];
            String chatLieu = (String) result[8];
            BigDecimal giaGiam = new BigDecimal(getGiaGiamCuoiCung(idSp));

            ChiTietSanPhamCustom chiTietSanPhamCustom = new ChiTietSanPhamCustom(
                    idCtsp, imgage, tenSanPham, giaBan, giaGiam, soLuong, mauSac, size, chatLieu);
            resultList.add(chiTietSanPhamCustom);
        }
        return resultList;
    }

    @Override
    public List<ChiTietSanPhamCustom> filterSize(Integer size) {
        List<ChiTietSanPhamCustom> resultList = new ArrayList<>();
        List<Object[]> queryResult = chiTietSanPhamRepository.filterSize(size);
        for (Object[] result : queryResult) {
            UUID idSp = (UUID) result[0];
            UUID idCtsp = (UUID) result[1];
            String imgage = (String) result[2];
            String tenSanPham = (String) result[3];
            BigDecimal giaBan = (BigDecimal) result[4];
            Integer soLuong = (Integer) result[5];
            String mauSac = (String) result[6];
            Integer ssize = (Integer) result[7];
            String chatLieu = (String) result[8];
            BigDecimal giaGiam = new BigDecimal(getGiaGiamCuoiCung(idSp));

            ChiTietSanPhamCustom chiTietSanPhamCustom = new ChiTietSanPhamCustom(
                    idCtsp, imgage, tenSanPham, giaBan, giaGiam, soLuong, mauSac, ssize, chatLieu);
            resultList.add(chiTietSanPhamCustom);
        }
        return resultList;
    }

    @Override
    public List<ChiTietSanPhamCustom> filterMaterial(String name) {
        List<ChiTietSanPhamCustom> resultList = new ArrayList<>();
        List<Object[]> queryResult = chiTietSanPhamRepository.filterMaterial(name);
        for (Object[] result : queryResult) {
            UUID idSp = (UUID) result[0];
            UUID idCtsp = (UUID) result[1];
            String imgage = (String) result[2];
            String tenSanPham = (String) result[3];
            BigDecimal giaBan = (BigDecimal) result[4];
            Integer soLuong = (Integer) result[5];
            String mauSac = (String) result[6];
            Integer ssize = (Integer) result[7];
            String chatLieu = (String) result[8];
            BigDecimal giaGiam = new BigDecimal(getGiaGiamCuoiCung(idSp));

            ChiTietSanPhamCustom chiTietSanPhamCustom = new ChiTietSanPhamCustom(
                    idCtsp, imgage, tenSanPham, giaBan, giaGiam, soLuong, mauSac, ssize, chatLieu);
            resultList.add(chiTietSanPhamCustom);
        }
        return resultList;
    }

    @Override
    public List<ChiTietSanPhamCustom> filterColor(String name) {
        List<ChiTietSanPhamCustom> resultList = new ArrayList<>();
        List<Object[]> queryResult = chiTietSanPhamRepository.filterColor(name);
        for (Object[] result : queryResult) {
            UUID idSp = (UUID) result[0];
            UUID idCtsp = (UUID) result[1];
            String imgage = (String) result[2];
            String tenSanPham = (String) result[3];
            BigDecimal giaBan = (BigDecimal) result[4];
            Integer soLuong = (Integer) result[5];
            String mauSac = (String) result[6];
            Integer ssize = (Integer) result[7];
            String chatLieu = (String) result[8];
            BigDecimal giaGiam = new BigDecimal(getGiaGiamCuoiCung(idSp));

            ChiTietSanPhamCustom chiTietSanPhamCustom = new ChiTietSanPhamCustom(
                    idCtsp, imgage, tenSanPham, giaBan, giaGiam, soLuong, mauSac, ssize, chatLieu);
            resultList.add(chiTietSanPhamCustom);
        }
        return resultList;
    }

}
