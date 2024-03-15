package com.example.duantotnghiep.repository.mua_hang_not_login_repo;

import com.example.duantotnghiep.entity.SanPhamChiTiet;
import com.example.duantotnghiep.mapper.ChiTietSanPhamCustom;
import com.example.duantotnghiep.response.DetailQuantityToSizeReponse;
import com.example.duantotnghiep.response.SanPhamGetAllResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietSanPhamRepository_not_login extends JpaRepository<SanPhamChiTiet, UUID> {
    //load sản phẩm với 1 ảnh mặc định
    @Query("SELECT new com.example.duantotnghiep.mapper.ChiTietSanPhamCustom" +
            "(i.tenImage, spct.id, sp.tenSanPham, sp.giaBan, spgg.donGiaKhiGiam, spgg.mucGiam,spct.soLuong, kd.tenDe, ms.tenMauSac, s.size, cl.tenChatLieu) " +
            "FROM SanPham sp " +
            "LEFT JOIN sp.spGiamGiaList spgg " +
            "LEFT JOIN spgg.giamGia gg " +
            "JOIN sp.listSanPhamChiTiet spct " +
            "JOIN sp.listImage i " +
            "JOIN spct.size s " +
            "JOIN sp.kieuDe kd " +
            "JOIN spct.chatLieu cl " +
            "JOIN spct.mauSac ms " +
            "WHERE i.isDefault = TRUE AND " +
            "(spgg IS NOT NULL AND spgg.donGiaKhiGiam <> 0) " +
            "OR spgg IS NULL")
    List<ChiTietSanPhamCustom> getAll();

    //tìm kiếm sản phẩm theo tên
    @Query("SELECT new com.example.duantotnghiep.mapper.ChiTietSanPhamCustom" +
            "(i.tenImage, spct.id, sp.tenSanPham, sp.giaBan, spgg.donGiaKhiGiam, spgg.mucGiam,spct.soLuong, kd.tenDe, ms.tenMauSac, s.size, cl.tenChatLieu) " +
            "FROM SanPham sp " +
            "LEFT JOIN sp.spGiamGiaList spgg " +
            "LEFT JOIN spgg.giamGia gg " +
            "JOIN sp.listSanPhamChiTiet spct " +
            "JOIN sp.listImage i " +
            "JOIN spct.size s " +
            "JOIN sp.kieuDe kd " +
            "JOIN spct.chatLieu cl " +
            "JOIN spct.mauSac ms " +
            "WHERE i.isDefault = TRUE AND sp.tenSanPham = :name")
    List<ChiTietSanPhamCustom> searchByName(String name);

    @Query("SELECT new com.example.duantotnghiep.response.SanPhamGetAllResponse(" +
            "sp.id, sp.tenSanPham, i.tenImage, th.tenThuongHieu, dm.tenDanhMuc, xx.tenXuatXu, sp.giaBan) " +
            "FROM SanPham sp " +
            "JOIN sp.danhMuc dm " +
            "JOIN sp.xuatXu xx " +
            "JOIN sp.thuongHieu th " +
            "JOIN sp.listSanPhamChiTiet spct " +
            "JOIN sp.listImage i " +
            "WHERE sp.id = :id AND i.isDefault = true ")
    SanPhamGetAllResponse getByIdSp(@Param("id") UUID id);

    @Query("SELECT new com.example.duantotnghiep.response.DetailQuantityToSizeReponse(spct.soLuong)" +
            " FROM SanPhamChiTiet spct WHERE spct.sanPham.id = :id AND spct.size.size = :size")
    DetailQuantityToSizeReponse getDetailQuantityToSizeReponse(@Param("id") UUID id, @Param("size") Integer size);

    //1.tìm kiếm sản phẩm theo idDanhMuc
    @Query(value = "SELECT IMG.TenImage,SCT.Id,SP.TenSanPham,SCT.GiaBan\n" +
            "            FROM SanPhamChiTiet SCT \n" +
            "            JOIN SanPham SP ON SCT.IdSanPham = SP.Id\n" +
            "            JOIN DanhMuc DM ON SP.IdDanhMuc = DM.Id \n" +
            "            JOIN XuatXu XS ON SP.IdXuatXu = XS.Id\n" +
            "            JOIN Image IMG ON IMG.idsanphamchitiet = SCT.Id\n" +
            "            JOIN ThuongHieu TH ON SP.IdThuongHieu = TH.Id\n" +
            "            JOIN ChatLieu CL ON SCT.IdChatLieu = CL.Id\n" +
            "            JOIN Size S ON SCT.IdSize = S.Id\n" +
            "            JOIN MauSac MS ON SCT.IdMauSac = MS.Id       \n" +
            "            WHERE IMG.isDefault = 'true' AND DM.Id = ?1", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByIdDanhMuc(UUID idDanhMuc);

    //2.tìm kiếm sản phẩm theo idXuatSu
    @Query(value = "SELECT IMG.TenImage,SCT.Id,SP.TenSanPham,SCT.GiaBan\n" +
            "            FROM SanPhamChiTiet SCT \n" +
            "            JOIN SanPham SP ON SCT.IdSanPham = SP.Id\n" +
            "            JOIN DanhMuc DM ON SP.IdDanhMuc = DM.Id \n" +
            "            JOIN XuatXu XS ON SP.IdXuatXu = XS.Id\n" +
            "            JOIN Image IMG ON IMG.idsanphamchitiet = SCT.Id\n" +
            "            JOIN ThuongHieu TH ON SP.IdThuongHieu = TH.Id\n" +
            "            JOIN ChatLieu CL ON SCT.IdChatLieu = CL.Id\n" +
            "            JOIN Size S ON SCT.IdSize = S.Id\n" +
            "            JOIN MauSac MS ON SCT.IdMauSac = MS.Id       \n" +
            "            WHERE IMG.isDefault = 'true' AND XS.Id = ?1", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByIdXuatSu(UUID idXuatSu);

    //3.tìm kiếm sản phẩm theo idThuongHieu
    @Query(value = "SELECT IMG.TenImage,SCT.Id,SP.TenSanPham,SCT.GiaBan\n" +
            "            FROM SanPhamChiTiet SCT \n" +
            "            JOIN SanPham SP ON SCT.IdSanPham = SP.Id\n" +
            "            JOIN DanhMuc DM ON SP.IdDanhMuc = DM.Id \n" +
            "            JOIN XuatXu XS ON SP.IdXuatXu = XS.Id\n" +
            "            JOIN Image IMG ON IMG.idsanphamchitiet = SCT.Id\n" +
            "            JOIN ThuongHieu TH ON SP.IdThuongHieu = TH.Id\n" +
            "            JOIN ChatLieu CL ON SCT.IdChatLieu = CL.Id\n" +
            "            JOIN Size S ON SCT.IdSize = S.Id\n" +
            "            JOIN MauSac MS ON SCT.IdMauSac = MS.Id       \n" +
            "            WHERE IMG.isDefault = 'true' AND TH.Id = ?1", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByIdThuongHieu(UUID idThuongHieu);

    //4.tìm kiếm sản phẩm theo idKieuDe
    @Query(value = "SELECT IMG.TenImage,SCT.Id,SP.TenSanPham,SCT.GiaBan\n" +
            "            FROM SanPhamChiTiet SCT \n" +
            "            JOIN SanPham SP ON SCT.IdSanPham = SP.Id\n" +
            "            JOIN DanhMuc DM ON SP.IdDanhMuc = DM.Id \n" +
            "            JOIN XuatXu XS ON SP.IdXuatXu = XS.Id\n" +
            "            JOIN Image IMG ON IMG.idsanphamchitiet = SCT.Id\n" +
            "            JOIN ThuongHieu TH ON SP.IdThuongHieu = TH.Id\n" +
            "            JOIN ChatLieu CL ON SCT.IdChatLieu = CL.Id\n" +
            "            JOIN Size S ON SCT.IdSize = S.Id\n" +
            "            JOIN MauSac MS ON SCT.IdMauSac = MS.Id       \n" +
            "            WHERE IMG.isDefault = 'true' AND KD.Id = ?1", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByIdKieuDe(UUID idKieuDe);

    //5.tìm kiếm sản phẩm theo idChatLieu
    @Query(value = "SELECT IMG.TenImage,SCT.Id,SP.TenSanPham,SCT.GiaBan\n" +
            "            FROM SanPhamChiTiet SCT \n" +
            "            JOIN SanPham SP ON SCT.IdSanPham = SP.Id\n" +
            "            JOIN DanhMuc DM ON SP.IdDanhMuc = DM.Id \n" +
            "            JOIN XuatXu XS ON SP.IdXuatXu = XS.Id\n" +
            "            JOIN Image IMG ON IMG.idsanphamchitiet = SCT.Id\n" +
            "            JOIN ThuongHieu TH ON SP.IdThuongHieu = TH.Id\n" +
            "            JOIN ChatLieu CL ON SCT.IdChatLieu = CL.Id\n" +
            "            JOIN Size S ON SCT.IdSize = S.Id\n" +
            "            JOIN MauSac MS ON SCT.IdMauSac = MS.Id       \n" +
            "            WHERE IMG.isDefault = 'true' AND CL.Id = ?1", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByIdChatLieu(UUID idChatLieu);

    //6.tìm kiếm sản phẩm theo idMauSac
    @Query(value = "SELECT IMG.TenImage,SCT.Id,SP.TenSanPham,SCT.GiaBan\n" +
            "            FROM SanPhamChiTiet SCT \n" +
            "            JOIN SanPham SP ON SCT.IdSanPham = SP.Id\n" +
            "            JOIN DanhMuc DM ON SP.IdDanhMuc = DM.Id \n" +
            "            JOIN XuatXu XS ON SP.IdXuatXu = XS.Id\n" +
            "            JOIN Image IMG ON IMG.idsanphamchitiet = SCT.Id\n" +
            "            JOIN ThuongHieu TH ON SP.IdThuongHieu = TH.Id\n" +
            "            JOIN ChatLieu CL ON SCT.IdChatLieu = CL.Id\n" +
            "            JOIN Size S ON SCT.IdSize = S.Id\n" +
            "            JOIN MauSac MS ON SCT.IdMauSac = MS.Id       \n" +
            "            WHERE IMG.isDefault = 'true' AND MS.Id = ?1", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByIdMauSac(UUID idMauSac);

}
