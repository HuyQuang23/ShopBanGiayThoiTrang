package com.example.duantotnghiep.repository.mua_hang_not_login_repo;

import com.example.duantotnghiep.entity.GioHangChiTiet;
import com.example.duantotnghiep.mapper.GioHangCustom_not_login;
import com.example.duantotnghiep.mapper.NameAndQuantityCart_Online;
import com.example.duantotnghiep.mapper.TongTienCustom_Online;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GioHangChiTietRepository_not_login extends JpaRepository<GioHangChiTiet, UUID> {
    //load giỏ hàng chi tiết
    @Query(value = "SELECT ghct.id,img.tenimage,sp.tensanpham,giaban,ghct.soluong  FROM sanphamchitiet sct\n" +
            "                       join sanpham sp on sct.idsanpham=sp.id\n" +
            "                       join size s on sct.idsize = s.id\n" +
            "                       join image img on sp.id = img.idsanpham\n" +
            "                       join giohangchitiet ghct on ghct.idsanphamchitiet = sct.id\n" +
            "                       join giohang gh on gh.id = ghct.idgiohang\n" +
            "                      join mausac ms on ms.id=sct.idmausac\n" +
            "                        where img.isdefault = 'true' and gh.id= ?1 and ghct.soluong > 0 and gh.trangthai = 1\n" +
            "\n" +
            "\t\t\t", nativeQuery = true)
    List<GioHangCustom_not_login> loadOnGioHang(UUID idgh);

    // Tìm mục trong giỏ hàng chi tiết dựa trên idGioHang và idSanPhamChiTiet
    GioHangChiTiet findByGioHang_IdAndSanPhamChiTiet_Id(UUID idGioHang, UUID idSanPhamChiTiet);

    //load tong tien
    @Query(value = "SELECT SUM(giaban * ghct.soluong) AS TongSoTien\n" +
            "FROM sanphamchitiet sct\n" +
            "JOIN sanpham sp ON sct.idsanpham = sp.id\n" +
            "JOIN size s ON sct.idsize = s.id\n" +
            "join image img on sp.id = img.idsanpham\n" +
            "JOIN giohangchitiet ghct ON ghct.idsanphamchitiet = sct.id\n" +
            "JOIN giohang gh ON gh.id = ghct.idgiohang\n" +
            "JOIN mausac ms ON ms.id = sct.idmausac\n" +
            "WHERE img.isdefault = 'true' AND gh.id = :idgh", nativeQuery = true)
    List<TongTienCustom_Online> getTongTien(@Param("idgh") UUID idgh);

    @Query(value = "SELECT\n" +
            "sp.tensanpham,\n" +
            "(sp.giaban * ghct.soluong) AS tongtien\n" +
            "FROM giohang gh\n" +
            "JOIN giohangchitiet ghct ON gh.id = ghct.idgiohang\n" +
            "JOIN sanphamchitiet sct ON ghct.idsanphamchitiet = sct.id\n" +
            "JOIN sanpham sp ON sct.idsanpham = sp.id\n" +
            "WHERE gh.id = :idgh and gh.trangthai=1", nativeQuery = true)
    List<NameAndQuantityCart_Online> getNameAndQuantity(@Param("idgh") UUID idgh);

}
