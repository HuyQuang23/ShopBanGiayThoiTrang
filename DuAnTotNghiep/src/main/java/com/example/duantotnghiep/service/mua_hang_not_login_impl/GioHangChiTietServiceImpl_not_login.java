package com.example.duantotnghiep.service.mua_hang_not_login_impl;

import com.example.duantotnghiep.entity.GioHang;
import com.example.duantotnghiep.entity.GioHangChiTiet;
import com.example.duantotnghiep.entity.SanPhamChiTiet;
import com.example.duantotnghiep.mapper.GioHangCustom_not_login;
import com.example.duantotnghiep.mapper.NameAndQuantityCart_Online;
import com.example.duantotnghiep.mapper.TongTienCustom_Online;
import com.example.duantotnghiep.repository.mua_hang_not_login_repo.ChiTietSanPhamRepository_not_login;
import com.example.duantotnghiep.repository.mua_hang_not_login_repo.GioHangChiTietRepository_not_login;
import com.example.duantotnghiep.repository.mua_hang_not_login_repo.GioHangRepository_not_login;
import com.example.duantotnghiep.service.mua_hang_not_login_service.GioHangChiTietService_not_login;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GioHangChiTietServiceImpl_not_login implements GioHangChiTietService_not_login {
    @Autowired
    private GioHangChiTietRepository_not_login gioHangChiTietRepository;

    @Autowired
    GioHangRepository_not_login gioHangRepository_not_login;

    @Autowired
    ChiTietSanPhamRepository_not_login chiTietSanPhamRepository_not_login;

    @Override
    public void themSanPhamVaoGioHangChiTiet(UUID idGioHang, UUID idSanPhamChiTiet, int soLuong) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepository.findByGioHang_IdAndSanPhamChiTiet_Id(idGioHang, idSanPhamChiTiet);

        if (gioHangChiTiet != null) {
            // Sản phẩm đã tồn tại trong giỏ hàng chi tiết, cập nhật số lượng
            gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() + soLuong);

            SanPhamChiTiet sanPhamChiTiet = chiTietSanPhamRepository_not_login.findById(idSanPhamChiTiet).get();

            // Trừ số lượng sản phẩm trong kho của phiên bản ban đầu
            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - soLuong);

            gioHangChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        } else {
            gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setId(UUID.randomUUID());

            GioHang gioHang = new GioHang();
            gioHang.setId(idGioHang);

            gioHangChiTiet.setGioHang(gioHang);

            SanPhamChiTiet sanPhamChiTiet = chiTietSanPhamRepository_not_login.findById(idSanPhamChiTiet).get();

            // Trừ số lượng sản phẩm trong kho của phiên bản ban đầu
            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - soLuong);

            gioHangChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
            gioHangChiTiet.setSoLuong(soLuong);
        }

        gioHangChiTietRepository.save(gioHangChiTiet);
    }



    @Override
    public List<GioHangCustom_not_login> loadGH(UUID idgh) {
        return gioHangChiTietRepository.loadOnGioHang(idgh);
    }

    @Override
    public List<TongTienCustom_Online> getTongTien(UUID idgh) {
        return gioHangChiTietRepository.getTongTien(idgh);
    }

    @Override
    public List<NameAndQuantityCart_Online> getNameAndQuantity(UUID idgh) {
        return gioHangChiTietRepository.getNameAndQuantity(idgh);
    }

    public void capNhatSoLuong(UUID idgiohangchitiet, int soLuongMoi) {
        Optional<GioHangChiTiet> optionalGioHangChiTiet = gioHangChiTietRepository.findById(idgiohangchitiet);

        if (optionalGioHangChiTiet.isPresent()) {
            GioHangChiTiet gioHangChiTiet = optionalGioHangChiTiet.get();

            int soLuongCu = gioHangChiTiet.getSoLuong();
            int soLuongHienTai = gioHangChiTiet.getSanPhamChiTiet().getSoLuong(); // Số lượng hiện tại trong kho

            // Xử lý tình huống khi số lượng mới nhỏ hơn hoặc bằng số lượng hiện tại trong kho
            int soLuongThayDoi = soLuongMoi - soLuongCu; // Số lượng thay đổi
            gioHangChiTiet.setSoLuong(soLuongMoi);

            // Cập nhật số lượng trong kho bằng cách cộng số lượng thay đổi
            int soLuongMoiTrongKho = soLuongHienTai - soLuongThayDoi;
            gioHangChiTiet.getSanPhamChiTiet().setSoLuong(soLuongMoiTrongKho);

            gioHangChiTietRepository.save(gioHangChiTiet);
        } else {
            // Xử lý tình huống khi không tìm thấy sản phẩm trong giỏ hàng
            System.out.println("ID sản phẩm chi tiết không tồn tại");
        }
    }


    // Xóa sản phẩm khỏi giỏ hàng và cộng trả lại số lượng vào kho
    public void xoaSanPhamKhoiGioHang(UUID idgiohangchitiet) {
        Optional<GioHangChiTiet> optionalGioHangChiTiet = gioHangChiTietRepository.findById(idgiohangchitiet);

        if (optionalGioHangChiTiet.isPresent()) {
            GioHangChiTiet gioHangChiTiet = optionalGioHangChiTiet.get();

            // Lấy số lượng sản phẩm cần xóa từ chi tiết giỏ hàng
            int soLuongXoa = gioHangChiTiet.getSoLuong();

            // Lấy sản phẩm tương ứng
            SanPhamChiTiet sanPhamChiTiet = gioHangChiTiet.getSanPhamChiTiet();

            // Cộng số lượng vào kho
            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() + soLuongXoa);

            // Xóa sản phẩm khỏi giỏ hàng chi tiết
            gioHangChiTietRepository.delete(gioHangChiTiet);
        } else {
            // Xử lý tình huống khi không tìm thấy sản phẩm trong giỏ hàng
            System.out.println("Không tìm thấy sản phẩm trong giỏ hàng");
        }
    }




    // Xóa tất cả sản phẩm khỏi giỏ hàng và cộng trả lại số lượng vào kho
    public void xoaTatCaSanPhamKhoiGioHang(UUID idGioHang) {
        Optional<GioHang> gioHangOptional = gioHangRepository_not_login.findById(idGioHang);

        if (gioHangOptional.isPresent()) {
            GioHang gioHang = gioHangOptional.get();

            // Lấy danh sách chi tiết giỏ hàng
            List<GioHangChiTiet> chiTietList = gioHang.getGioHangChiTietList();

            for (GioHangChiTiet chiTiet : chiTietList) {
                int soLuongXoa = chiTiet.getSoLuong();
                SanPhamChiTiet sanPhamChiTiet = chiTiet.getSanPhamChiTiet();

                // Cộng số lượng vào kho
                sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() + soLuongXoa);
            }

            // Xóa tất cả chi tiết giỏ hàng
            gioHangChiTietRepository.deleteAll(chiTietList);

            // Đặt lại danh sách chi tiết giỏ hàng thành rỗng
            gioHang.setGioHangChiTietList(new ArrayList<>());
            gioHangRepository_not_login.save(gioHang);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id trong giỏ hàng");
        }
    }



}
