package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.entity.HoaDon;
import com.example.duantotnghiep.response.HoaDonDTOResponse;
import com.example.duantotnghiep.response.HoaDonResponse;
import com.example.duantotnghiep.response.IdGioHangResponse;
import com.example.duantotnghiep.response.OrderCounterCartsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {

    @Query("SELECT NEW com.example.duantotnghiep.response.HoaDonResponse(hd.id, hd.ma, tknv.name, hd.ngayTao, tkkh.name, ld.tenLoaiDon, hd.trangThai)" +
            "FROM HoaDon hd " +
            "JOIN hd.loaiDon ld " +
            "JOIN hd.taiKhoanNhanVien tknv " +
            "LEFT JOIN hd.taiKhoanKhachHang tkkh " +
            "WHERE hd.trangThai = 1 AND ld.trangThai = 2 " +
            "ORDER BY hd.ngayTao DESC")
    Page<HoaDonResponse> viewHoaDonTaiQuay(Pageable pageable);

    @Query("SELECT NEW com.example.duantotnghiep.response.HoaDonResponse(hd.id, hd.ma, tknv.name, hd.ngayTao, tkkh.name, ld.tenLoaiDon, hd.trangThai)" +
            " FROM HoaDon hd " +
            "JOIN hd.loaiDon ld " +
            "JOIN hd.taiKhoanNhanVien tknv " +
            "LEFT JOIN hd.taiKhoanKhachHang tkkh " +
            "WHERE hd.trangThai = 1 AND ld.trangThai = 2 AND hd.ma = :ma " +
            "ORDER BY hd.ngayTao DESC")
    List<HoaDonResponse> findByCodeOrder(@Param("ma") String ma);

    @Query("SELECT NEW com.example.duantotnghiep.response.OrderCounterCartsResponse" +
            "(tkkh.id, hd.ma, tkkh.name, hd.ngayTao, dc.diaChi, tkkh.email, tkkh.soDienThoai)" +
            "FROM HoaDon hd " +
            "JOIN hd.taiKhoanKhachHang tkkh " +
            "LEFT JOIN tkkh.diaChiList dc WHERE hd.id = :id")
    OrderCounterCartsResponse findByHoaDon(@Param("id") UUID id);

    @Query("SELECT new com.example.duantotnghiep.response.IdGioHangResponse(gh.id) " +
            "FROM HoaDon hd " +
            "JOIN hd.taiKhoanKhachHang tk " +
            "JOIN tk.gioHangList gh WHERE hd.trangThai = 1 AND gh.trangThai = 1 AND hd.id = :id")
    IdGioHangResponse showIdGioHangCt(@Param("id") UUID id);

    @Query("SELECT NEW com.example.duantotnghiep.response.HoaDonDTOResponse(hd.id, hd.ma, hd.tenNguoiNhan, hd.sdtNguoiNhan, hd.thanhTien, SUM(hdct.tienGiamGia), hd.ngayTao, tknv.name, ld.tenLoaiDon, hd.trangThai)\n" +
            "FROM HoaDon hd\n" +
            "LEFT JOIN hd.hoaDonChiTietList hdct\n" +
            "LEFT JOIN hd.taiKhoanNhanVien tknv\n" +
            "JOIN hd.loaiDon ld\n" +
            "WHERE (:trangThaiHD IS NULL OR hd.trangThai = :trangThaiHD) AND (:loaiDon IS NULL OR ld.trangThai = :loaiDon) AND (:tenNhanVien IS NULL OR tknv.name = :tenNhanVien)" +
            "AND (:ma IS NULL OR hd.ma LIKE %:ma%) AND (:soDienThoai IS NULL OR hd.sdtNguoiNhan LIKE %:soDienThoai%)\n" +
            "GROUP BY hd.id, hd.ma, hd.tenNguoiNhan, hd.sdtNguoiNhan, hd.thanhTien, hd.ngayTao, tknv.name, ld.tenLoaiDon, hd.trangThai\n" +
            "ORDER BY hd.ngayTao DESC")
    Page<HoaDonDTOResponse> getAllHoaDonAdmin(@Param("trangThaiHD") Integer trangThaiHD, @Param("loaiDon") Integer loaiDon, @Param("tenNhanVien") String tenNhanVien, @Param("ma") String ma, @Param("soDienThoai") String soDienThoai, Pageable pageable);

    // TODO Hiển thị hóa đơn Staff
    @Query("SELECT NEW com.example.duantotnghiep.response.HoaDonDTOResponse(hd.id, hd.ma, hd.tenNguoiNhan, hd.sdtNguoiNhan, hd.thanhTien, SUM(hdct.tienGiamGia), hd.ngayTao, tknv.name, ld.tenLoaiDon, hd.trangThai)\n" +
            "FROM HoaDon hd\n" +
            "LEFT JOIN hd.hoaDonChiTietList hdct\n" +
            "LEFT JOIN hd.taiKhoanNhanVien tknv\n" +
            "JOIN hd.loaiDon ld\n" +
            "WHERE (:trangThaiHD IS NULL OR hd.trangThai = :trangThaiHD) AND (:loaiDon IS NULL OR ld.trangThai = :loaiDon) " +
            "AND (:ma IS NULL OR hd.ma LIKE %:ma%) AND (:soDienThoai IS NULL OR hd.sdtNguoiNhan LIKE %:soDienThoai%) AND tknv.username = :username\n" +
            "GROUP BY hd.id, hd.ma,  hd.tenNguoiNhan, hd.sdtNguoiNhan, hd.thanhTien, hd.ngayTao, tknv.name, ld.tenLoaiDon, hd.trangThai\n" +
            "ORDER BY hd.ngayTao DESC")
    Page<HoaDonDTOResponse> getAllHoaDonStaff(@Param("trangThaiHD") Integer trangThaiHD, @Param("loaiDon") Integer loaiDon, @Param("ma") String ma, @Param("soDienThoai") String soDienThoai, @Param("username") String username, Pageable pageable);

    // TODO Hiển thị hóa đơn chưa thanh toán cho staff
    @Query("SELECT NEW com.example.duantotnghiep.response.HoaDonDTOResponse(hd.id, hd.ma, hd.tenNguoiNhan, hd.sdtNguoiNhan, hd.thanhTien, SUM(hdct.tienGiamGia), hd.ngayTao, tknv.name, ld.tenLoaiDon, hd.trangThai)\n" +
            "FROM HoaDon hd\n" +
            "LEFT JOIN hd.hoaDonChiTietList hdct\n" +
            "LEFT JOIN hd.taiKhoanNhanVien tknv\n" +
            "JOIN hd.loaiDon ld\n" +
            "WHERE hd.trangThai = 1 AND (:loaiDon IS NULL OR ld.trangThai = :loaiDon) " +
            "AND (:ma IS NULL OR hd.ma LIKE %:ma%) AND (:soDienThoai IS NULL OR hd.sdtNguoiNhan LIKE %:soDienThoai%) " +
            "GROUP BY hd.id, hd.ma, hd.tenNguoiNhan, hd.sdtNguoiNhan, hd.thanhTien, hd.ngayTao, tknv.name, ld.tenLoaiDon, hd.trangThai\n" +
            "ORDER BY hd.ngayTao DESC")
    Page<HoaDonDTOResponse> getAllHoaDonCTTStaff(@Param("loaiDon") Integer loaiDon, @Param("ma") String ma, @Param("soDienThoai") String soDienThoai, Pageable pageable);

}
