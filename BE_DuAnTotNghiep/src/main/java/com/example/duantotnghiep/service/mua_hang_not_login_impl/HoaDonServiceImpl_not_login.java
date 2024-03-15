package com.example.duantotnghiep.service.mua_hang_not_login_impl;

import com.example.duantotnghiep.entity.*;
import com.example.duantotnghiep.enums.StatusOrderEnums;
import com.example.duantotnghiep.enums.TypeOrderEnums;
import com.example.duantotnghiep.repository.mua_hang_not_login_repo.*;
import com.example.duantotnghiep.request.not_login.CreateKhachRequest_not_login;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.service.mua_hang_not_login_service.HoaDonService_not_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class HoaDonServiceImpl_not_login implements HoaDonService_not_login {

    @Autowired
    private ChiTietSanPhamRepository_not_login chiTietSanPhamRepository;

    @Autowired
    private HoaDonRepository_not_login hoaDonRepository;

    @Autowired
    private KhachHangRepository_not_login khachHangRepository_not_login;

    @Autowired
    private GioHangChiTietRepository_not_login gioHangChiTietRepository;

    @Autowired
    private HoaDonChiTietRepository_not_login hoaDonChiTietRepository;

    @Autowired
    private GioHangRepository_not_login gioHangRepository_not_login;

    @Autowired
    private LoaiDonRepository_not_login loaiDonRepository;

    @Autowired
    private DiaChiRepository_not_login diaChiRepository;

    @Override
    public MessageResponse thanhToanKhongDangNhap(CreateKhachRequest_not_login createKhachRequest_not_login) {
        List<TaiKhoan> khachHangList = khachHangRepository_not_login.getKhachHangByEmailAndSdt(createKhachRequest_not_login.getEmail(), createKhachRequest_not_login.getSoDienThoai());
        TaiKhoan khachHang;

        //Step1 : Xử lí khách hàng và địa chỉ
        if (!khachHangList.isEmpty()) {
            // Nếu tài khoản khách hàng đã tồn tại, sử dụng tài khoản đó.
            khachHang = khachHangList.get(0);
        } else {
            // Nếu không tìm thấy tài khoản, tạo tài khoản mới cho khách hàng.
            khachHang = new TaiKhoan();

            khachHang.setId(UUID.randomUUID());

            khachHang.setEmail(createKhachRequest_not_login.getEmail());

            khachHang.setName(createKhachRequest_not_login.getHoTen());

            khachHang.setSoDienThoai(createKhachRequest_not_login.getSoDienThoai());

            khachHangRepository_not_login.save(khachHang);

            //Tạo địa chỉ cho khách luôn

            DiaChi diaChi = new DiaChi();

            diaChi.setId(UUID.randomUUID());

            diaChi.setDiaChi(createKhachRequest_not_login.getDiaChi());

            diaChi.setQuocGia("VietNam");

            diaChi.setTinh(createKhachRequest_not_login.getThanhPho());

            diaChi.setHuyen(createKhachRequest_not_login.getQuanHuyen());

            diaChi.setXa(createKhachRequest_not_login.getPhuongXa());

            diaChi.setTaiKhoan(khachHang);

            diaChiRepository.save(diaChi);

        }//End step 1

        //Step2 : Xử lí hóa đơn

        // Tạo hóa đơn mới và gán thông tin tài khoản khách hàng.
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Random rand = new Random();
        int randomNumber = rand.nextInt(100000);
        String maHd = String.format("HD%03d", randomNumber);

        HoaDon hoaDon = new HoaDon();

        hoaDon.setId(UUID.randomUUID());

        hoaDon.setMa(maHd);

        hoaDon.setNgayTao(timestamp);

        hoaDon.setTrangThai(StatusOrderEnums.CHO_XAC_NHAN.getValue());
        hoaDon.setDiaChi(createKhachRequest_not_login.getDiaChi()+" "+createKhachRequest_not_login.getPhuongXa()+" "+createKhachRequest_not_login.getQuanHuyen()+" "+createKhachRequest_not_login.getThanhPho());
        hoaDon.setSdtNguoiNhan(createKhachRequest_not_login.getSoDienThoai());
        hoaDon.setTenNguoiNhan(createKhachRequest_not_login.getHoTen());
        hoaDon.setTaiKhoanKhachHang(khachHang);

        hoaDon.setLoaiDon(loaiDonRepository.findByTrangThai(TypeOrderEnums.ONLINE.getValue()).get());

        hoaDon.setThanhTien(createKhachRequest_not_login.getTongTien());

        hoaDon.setTienKhachTra(createKhachRequest_not_login.getTienKhachTra());

        hoaDonRepository.save(hoaDon);
        //End step 2

        //Step3 : Xử lí hóa đơn chi tiết
        for (UUID idGioHangChiTiet : createKhachRequest_not_login.getGioHangChiTietList()) {
            // Tạo chi tiết hóa đơn cho mỗi sản phẩm trong giỏ hàng.

            Optional<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findById(idGioHangChiTiet);

            if (gioHangChiTiet.isPresent()) {

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();

                hoaDonChiTiet.setId(UUID.randomUUID());

                hoaDonChiTiet.setHoaDon(hoaDon);

                hoaDonChiTiet.setSanPhamChiTiet(gioHangChiTiet.get().getSanPhamChiTiet());

                hoaDonChiTiet.setDonGia(gioHangChiTiet.get().getSanPhamChiTiet().getSanPham().getGiaBan());

                hoaDonChiTiet.setSoLuong(gioHangChiTiet.get().getSoLuong());

                hoaDonChiTiet.setTrangThai(1);

                hoaDonChiTietRepository.save(hoaDonChiTiet);

            } else {
                System.out.println("giỏ hàng chi tiết không tồn tại !");
                return MessageResponse.builder().message("Thanh Toán thất bại").build();
            }
        }//End step 3

//        //Step 4 : Xử lí hóa đơn chi tiết cập nhật số lượng trong kho
//        for (UUID idGioHangChiTiet : createKhachRequest_not_login.getGioHangChiTietList()) {
//            Optional<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findById(idGioHangChiTiet);
//
//            if (gioHangChiTiet.isPresent()) {
//                // Giảm số lượng sản phẩm trong kho đi số lượng đã bán trong chi tiết hóa đơn
//                gioHangChiTiet.get().getSanPhamChiTiet().setSoLuong(gioHangChiTiet.get().getSanPhamChiTiet().getSoLuong() - gioHangChiTiet.get().getSoLuong());
//                chiTietSanPhamRepository.save(gioHangChiTiet.get().getSanPhamChiTiet());
//            }
//        }//End step 4

        //Step 5 : Cập nhật trạng thái của giỏ hàng thành 2 sau khi thanh toán
        for (UUID idGioHangChiTiet : createKhachRequest_not_login.getGioHangChiTietList()) {

            Optional<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findById(idGioHangChiTiet);

            if (gioHangChiTiet.isPresent()) {
                System.out.println(gioHangChiTiet.get().getGioHang().getTrangThai());
                gioHangChiTiet.get().getGioHang().setTrangThai(2);
                gioHangRepository_not_login.save(gioHangChiTiet.get().getGioHang());
            }

        }//End step 5

        //Step 6 : Đặt số lượng tất cả các sản phẩm trong giỏ hàng về 0
        for (UUID idGioHangChiTiet : createKhachRequest_not_login.getGioHangChiTietList()) {

            Optional<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findById(idGioHangChiTiet);

            if (gioHangChiTiet.isPresent()) {
                gioHangChiTiet.get().setSoLuong(0);
                gioHangChiTietRepository.save(gioHangChiTiet.get());
            }

        }//End step 6

        return MessageResponse.builder().message("Thanh Toán Thành Công").build();

    }

}