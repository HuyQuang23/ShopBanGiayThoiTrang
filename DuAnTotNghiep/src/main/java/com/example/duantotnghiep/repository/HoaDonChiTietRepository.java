package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.HoaDonChiTiet;
import com.example.duantotnghiep.response.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query(value = "SELECT TOP 1 hd.trangthai, LD.tenloaidon,\n" +
            "HD.diachi,\n" +
            "HD.tennguoinhan, HD.sdtnguoinhan, TTHD.ghichu\n" +
            "FROM\n" +
            "hoadon HD JOIN loaidon LD ON HD.idloaidon = LD.id\n" +
            "JOIN trangthaihoadon TTHD ON TTHD.idhoadon = HD.id\n" +
            "WHERE TTHD.idhoadon = ?1\n" +
            "ORDER BY TTHD.thoigian DESC", nativeQuery = true)
    ThongTinDonHang getThongTinDonHang(UUID idHoaDon);


    @Query(value = "SELECT IM.tenimage, SP.tensanpham, SP.giaban, HDCT.dongiasaugiam, HDCT.soluong FROM hoadon HD \n" +
            "LEFT JOIN hoadonchitiet HDCT ON HD.id = HDCT.idhoadon\n" +
            "LEFT JOIN sanphamchitiet SPCT ON SPCT.id = HDCT.idsanphamchitiet\n" +
            "LEFT JOIN sanpham SP ON SP.id = SPCT.idsanpham\n" +
            "LEFT JOIN spgiamgia SPGG ON SPGG.idsanpham = SP.id\n" +
            "LEFT JOIN image IM ON IM.idsanpham = SP.id WHERE IM.isdefault = 1 AND HD.id = ?1  AND (\n" +
            "                           spgg.id IS NULL\n" +
            "                            OR ( \n" +
            "                               spgg.id IS NOT NULL\n" +
            "                               AND spgg.id = (\n" +
            "                                   SELECT MIN(spgg_inner.id)\n" +
            "                                    FROM spgiamgia spgg_inner\n" +
            "                                    WHERE spgg_inner.idsanpham = sp.id)))", nativeQuery = true)

    List<SanPhamHoaDonChiTietResponse> getSanPhamHDCT(UUID idHoaDon);

    @Query(value = "SELECT HD.ma, HTTT.sotientra, HTTT.ngaytao, LHTT.tenloai, HTTT.phuongthucthanhtoan, HTTT.ghichu, HTTT.trangthai, TKNV.fullname FROM hoadon HD\n" +
            "LEFT JOIN hinhthucthanhtoan HTTT ON HD.id = HTTT.idhoadon LEFT JOIN loaihinhthucthanhtoan LHTT ON HTTT.idloai = LHTT.id\n" +
            "JOIN taikhoan TKNV ON HD.idnhanvien = TKNV.id WHERE HD.id = ?1\n", nativeQuery = true)
    List<HinhThucThanhToanResponse> getLichSuThanhToan(UUID idHoaDon);

    @Query("SELECT new com.example.duantotnghiep.response.TrangThaiHoaDonResponse(tthd.trangThai, tthd.thoiGian, tknv.name, tthd.ghiChu) " +
            "FROM HoaDon hd " +
            "JOIN hd.trangThaiHoaDonList tthd " +
            "JOIN hd.taiKhoanNhanVien tknv WHERE hd.id = :id ORDER BY tthd.thoiGian ASC ")
    List<TrangThaiHoaDonResponse> getAllTrangThaiHoaDon(@Param("id") UUID id);



}
