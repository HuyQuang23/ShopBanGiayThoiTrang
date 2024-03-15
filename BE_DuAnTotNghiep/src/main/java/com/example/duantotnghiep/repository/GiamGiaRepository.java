package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.GiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.duantotnghiep.response.GiamGiaDetailResponse;
import com.example.duantotnghiep.response.GiamGiaResponse;
import com.example.duantotnghiep.response.KhachHangResponse;
import com.example.duantotnghiep.response.ProductDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface GiamGiaRepository extends JpaRepository<GiamGia, UUID> {

    @Query("SELECT DISTINCT new com.example.duantotnghiep.response.GiamGiaResponse(gg.id, gg.tenGiamGia, gg.maGiamGia, gg.ngayBatDau, gg.ngayKetThuc, gg.hinhThucGiam, gg.trangThai, spgg.mucGiam) " +
            "FROM GiamGia gg " +
            "JOIN gg.spGiamGiaList spgg " +
            "JOIN spgg.sanPham sp " +
            "ORDER BY gg.ngayBatDau DESC")
    List<GiamGiaResponse> listGiamGia();

    @Query("SELECT DISTINCT new com.example.duantotnghiep.response.GiamGiaResponse(gg.id, gg.tenGiamGia, gg.maGiamGia, gg.ngayBatDau, gg.ngayKetThuc, gg.hinhThucGiam, gg.trangThai, spgg.mucGiam) " +
            "FROM GiamGia gg " +
            "JOIN gg.spGiamGiaList spgg " +
            "JOIN spgg.sanPham sp " +
            "ORDER BY gg.ngayBatDau DESC")
    Page<GiamGiaResponse> listGiamGias(Pageable pageable);

    @Query("SELECT new com.example.duantotnghiep.response.GiamGiaDetailResponse(gg.id,sp.tenSanPham,spgg.donGiaKhiGiam,sp.giaBan) "
            +
            "FROM GiamGia gg " +
            "JOIN gg.spGiamGiaList spgg " +
            "JOIN spgg.sanPham sp " +
            "where gg.id = :id ")
    List<GiamGiaDetailResponse> listGiamGiaDetail(@Param("id") UUID id);

    @Query("SELECT new com.example.duantotnghiep.response.GiamGiaResponse(gg.id,gg.tenGiamGia,gg.maGiamGia,gg.ngayBatDau,gg.ngayKetThuc,gg.hinhThucGiam,gg.trangThai,spgg.mucGiam) "
            +
            "FROM GiamGia gg " +
            "JOIN gg.spGiamGiaList spgg " +
            " JOIN spgg.sanPham sp " +
            "WHERE gg.maGiamGia LIKE :key OR gg.tenGiamGia LIKE :key ")
    List<GiamGiaResponse> findbyValueString(@Param("key") String key);

    @Query("SELECT new com.example.duantotnghiep.response.GiamGiaResponse(gg.id,gg.tenGiamGia,gg.maGiamGia,gg.ngayBatDau,gg.ngayKetThuc,gg.hinhThucGiam,gg.trangThai,spgg.mucGiam) "
            +
            "FROM GiamGia gg " +
            "JOIN gg.spGiamGiaList spgg " +
            " JOIN spgg.sanPham sp " +
            "WHERE  gg.ngayBatDau >=  :key1 and  gg.ngayKetThuc <= :key2")
    List<GiamGiaResponse> findbyValueDate(@Param("key1") Date key1, @Param("key2") Date key2);

    @Query("SELECT new com.example.duantotnghiep.response.GiamGiaResponse(gg.id,gg.tenGiamGia,gg.maGiamGia,gg.ngayBatDau,gg.ngayKetThuc,gg.hinhThucGiam,gg.trangThai,spgg.mucGiam) "
            +
            "FROM GiamGia gg " +
            "JOIN gg.spGiamGiaList spgg " +
            " JOIN spgg.sanPham sp " +
            "WHERE gg.trangThai = :key")
    List<GiamGiaResponse> findbyValueStatus(@Param("key") Integer key);
    //

    @Query("SELECT new com.example.duantotnghiep.response.ProductDetailResponse(sp.id, i.tenImage, sp.tenSanPham,s.size,kd.tenDe,ms.tenMauSac,ct.tenChatLieu,sp.trangThai) "
            +
            "FROM SanPham sp " +
            "JOIN sp.thuongHieu th " +
            "JOIN sp.danhMuc dm " +
            "JOIN sp.xuatXu xx " +
            "JOIN sp.listSanPhamChiTiet spct " +
            "JOIN spct.size s" +
            " JOIN spct.mauSac ms " +
            "JOIN sp.kieuDe kd " +
            "JOIN spct.chatLieu ct " +
            "JOIN sp.listImage i WHERE sp.tenSanPham  LIKE :key   ")
    List<ProductDetailResponse> ProductDetailResponse(@Param("key") String key);

    @Query("SELECT new com.example.duantotnghiep.response.ProductDetailResponse(sp.id, i.tenImage, sp.tenSanPham,s.size,kd.tenDe,ms.tenMauSac,ct.tenChatLieu,sp.trangThai,i.id,s.id,ms.id,ct.id,kd.id) " +
            "FROM SanPhamChiTiet spct " +
            "JOIN spct.size s" +
            " JOIN spct.mauSac ms " +
            "JOIN spct.sanPham sp " +
            "JOIN sp.kieuDe kd " +
            "JOIN sp.thuongHieu th " +
            "JOIN sp.danhMuc dm " +
            "JOIN sp.xuatXu xx " +
            "JOIN spct.chatLieu ct " +
            "JOIN sp.listImage i ")
    List<ProductDetailResponse> ListProductResponse();

    @Query("SELECT new com.example.duantotnghiep.response.ProductDetailResponse(sp.id, i.tenImage, sp.tenSanPham,s.size,kd.tenDe,ms.tenMauSac,ct.tenChatLieu,sp.trangThai) "
            +
            "FROM SanPhamChiTiet spct " +
            "JOIN spct.size s" +
            " JOIN spct.mauSac ms " +
            "JOIN spct.sanPham sp " +
            "JOIN sp.kieuDe kd " +
            "JOIN sp.thuongHieu th " +
            "JOIN sp.danhMuc dm " +
            "JOIN sp.xuatXu xx " +
            "JOIN spct.chatLieu ct " +
            "JOIN sp.listImage i where s.id =:id or ms.id = :id or kd.id = :id or sp.id = :id or th.id = :id or dm.id = :id or xx.id = :id or ct.id = :id")
    List<ProductDetailResponse> ListSearchDanhMuc(@Param("id") UUID id);

    @Query("SELECT COUNT(spgg.id) FROM GiamGia gg JOIN gg.spGiamGiaList spgg WHERE spgg.sanPham.id = :productId")
    int countRecordsByProductId(@Param("productId") UUID productId);

    boolean existsByTenGiamGia(String tenGiamGia);

    @Query("SELECT COUNT(spgg) " +
            "FROM SpGiamGia spgg " +
            "JOIN spgg.giamGia gg " +
            "WHERE spgg.sanPham.id = :productId ")
    int countByProductId(@Param("productId") UUID productId);

    @Query("SELECT sp.id FROM SanPham sp" +
            " JOIN sp.listSanPhamChiTiet spct " +
            " WHERE sp.danhMuc.id = :danhMucId or sp.thuongHieu.id = :danhMucId or sp.xuatXu.id = :danhMucId or spct.chatLieu.id  = :danhMucId or sp.kieuDe.id =:danhMucId or spct.mauSac.id =:danhMucId or spct.size.id =:danhMucId ")
    List<UUID> findProductIdsByDanhMucId(@Param("danhMucId") UUID danhMucId);
}
