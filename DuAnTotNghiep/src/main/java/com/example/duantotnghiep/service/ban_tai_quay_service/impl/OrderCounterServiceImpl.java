package com.example.duantotnghiep.service.ban_tai_quay_service.impl;

import com.example.duantotnghiep.entity.*;
import com.example.duantotnghiep.enums.*;
import com.example.duantotnghiep.repository.*;
import com.example.duantotnghiep.request.HoaDonGiaoThanhToanRequest;
import com.example.duantotnghiep.request.HoaDonThanhToanRequest;
import com.example.duantotnghiep.response.HoaDonResponse;
import com.example.duantotnghiep.response.IdGioHangResponse;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.response.OrderCounterCartsResponse;
import com.example.duantotnghiep.service.ban_tai_quay_service.OrderCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class OrderCounterServiceImpl implements OrderCounterService
{

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private LoaiDonRepository loaiDonRepository;

    @Autowired
    private TrangThaiHoaDonRepository trangThaiHoaDonRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    @Transactional
    // TODO Thêm hóa đơn tại quầy
    public HoaDon taoHoaDon(String name) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Optional<TaiKhoan> findByNhanVien = taiKhoanRepository.findByUsername(name);

        Optional<LoaiDon> findByLoaiDon = loaiDonRepository.findByTrangThai(TypeOrderEnums.TAI_QUAY.getValue());

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setId(UUID.randomUUID());
        taiKhoan.setName("Khách lẻ");
        taiKhoan.setTrangThai(1);
        taiKhoanRepository.save(taiKhoan);

        Random rand = new Random();
        int randomNumber = rand.nextInt(100000);
        String maHd = String.format("HD%03d", randomNumber);
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(UUID.randomUUID());
        hoaDon.setMa(maHd);
        hoaDon.setTenNguoiNhan("Khách lẻ");
        hoaDon.setNgayTao(timestamp);
        hoaDon.setTrangThai(StatusOrderEnums.CHO_XAC_NHAN.getValue());
        hoaDon.setTaiKhoanNhanVien(findByNhanVien.get());
        hoaDon.setTaiKhoanKhachHang(taiKhoan);
        hoaDon.setLoaiDon(findByLoaiDon.get());
        hoaDonRepository.save(hoaDon);

        GioHang gioHang = new GioHang();
        gioHang.setId(UUID.randomUUID());
        gioHang.setTaiKhoan(taiKhoan);
        gioHang.setNgayTao(timestamp);
        gioHang.setTrangThai(StatusCartEnums.CHUA_CO_SAN_PHAM.getValue());
        gioHangRepository.save(gioHang);

        TrangThaiHoaDon trangThaiHoaDon = new TrangThaiHoaDon();
        trangThaiHoaDon.setId(UUID.randomUUID());
        trangThaiHoaDon.setTrangThai(StatusOrderDetailEnums.CHO_XAC_NHAN.getValue());
        trangThaiHoaDon.setThoiGian(timestamp);
        trangThaiHoaDon.setGhiChu("Nhân viên tạo đơn cho khách");
        trangThaiHoaDon.setHoaDon(hoaDon);
        trangThaiHoaDonRepository.save(trangThaiHoaDon);
        return hoaDon;
    }

    @Override
    public List<HoaDonResponse> viewHoaDonTaiQuay(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<HoaDonResponse> hoaDonResponses = hoaDonRepository.viewHoaDonTaiQuay(pageable);
        return hoaDonResponses.getContent();
    }

    @Override
    public List<HoaDonResponse> findByCodeOrder(String ma) {
        return hoaDonRepository.findByCodeOrder(ma);
    }

    @Override
    public MessageResponse updateHoaDon(UUID idHoaDon, HoaDonThanhToanRequest hoaDonThanhToanRequest) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHoaDon);
        hoaDon.get().setNgayNhan(timestamp);
        hoaDon.get().setNgayThanhToan(timestamp);
        hoaDon.get().setTienKhachTra(hoaDonThanhToanRequest.getTienKhachTra());
        hoaDon.get().setTienThua(hoaDonThanhToanRequest.getTienThua());
        hoaDon.get().setThanhTien(hoaDonThanhToanRequest.getTongTien());
        hoaDon.get().setTenNguoiNhan(hoaDonThanhToanRequest.getHoTen());
        hoaDon.get().setSdtNguoiNhan(hoaDonThanhToanRequest.getSoDienThoai());
        hoaDon.get().setDiaChi(hoaDonThanhToanRequest.getDiaChi());
        hoaDon.get().setTrangThai(5);
        hoaDonRepository.save(hoaDon.get());

        for (UUID idGioHangChiTiet : hoaDonThanhToanRequest.getGioHangChiTietList()) {
            Optional<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findById(idGioHangChiTiet);
            gioHangChiTiet.get().setTrangThai(StatusCartDetailEnums.DA_THANH_TOAN.getValue());
            gioHangChiTietRepository.save(gioHangChiTiet.get());
            Optional<GioHang> gioHang = gioHangRepository.findById(gioHangChiTiet.get().getGioHang().getId());
            gioHang.get().setTrangThai(StatusCartEnums.DA_THANH_TOAN.getValue());
            gioHangRepository.save(gioHang.get());
            if (gioHangChiTiet.isPresent()) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setId(UUID.randomUUID());
                hoaDonChiTiet.setHoaDon(hoaDon.get());
                hoaDonChiTiet.setSanPhamChiTiet(gioHangChiTiet.get().getSanPhamChiTiet());
                hoaDonChiTiet.setDonGia(gioHangChiTiet.get().getSanPhamChiTiet().getSanPham().getGiaBan());
                hoaDonChiTiet.setSoLuong(gioHangChiTiet.get().getSoLuong());
                hoaDonChiTiet.setTrangThai(1);
                hoaDonChiTietRepository.save(hoaDonChiTiet);

                SanPhamChiTiet sanPhamChiTiet = chiTietSanPhamRepository.findById(gioHangChiTiet.get().getSanPhamChiTiet().getId()).get();
                sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - gioHangChiTiet.get().getSoLuong());
                chiTietSanPhamRepository.save(sanPhamChiTiet);
            }
        }

        TrangThaiHoaDon trangThaiHoaDon = new TrangThaiHoaDon();
        trangThaiHoaDon.setId(UUID.randomUUID());
        trangThaiHoaDon.setTrangThai(StatusOrderDetailEnums.CHO_XAC_NHAN.getValue());
        trangThaiHoaDon.setThoiGian(timestamp);
        trangThaiHoaDon.setGhiChu("Nhân viên xác nhận đơn cho khách");
        trangThaiHoaDon.setHoaDon(hoaDon.get());
        trangThaiHoaDonRepository.save(trangThaiHoaDon);

        return MessageResponse.builder().message("Thanh Toán Thành Công").build();
    }

    @Override
    public MessageResponse updateHoaDonGiaoTaiQuay(UUID idHoaDon, HoaDonGiaoThanhToanRequest hoaDonGiaoThanhToanRequest) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHoaDon);
        hoaDon.get().setNgayNhan(timestamp);
        hoaDon.get().setNgayThanhToan(timestamp);
        hoaDon.get().setTienKhachTra(hoaDonGiaoThanhToanRequest.getTienKhachTra());
        hoaDon.get().setTienThua(hoaDonGiaoThanhToanRequest.getTienThua());
        hoaDon.get().setThanhTien(hoaDonGiaoThanhToanRequest.getTongTien());
        hoaDon.get().setTenNguoiNhan(hoaDonGiaoThanhToanRequest.getHoTen());
        hoaDon.get().setSdtNguoiNhan(hoaDonGiaoThanhToanRequest.getSoDienThoai());
        hoaDon.get().setDiaChi(hoaDonGiaoThanhToanRequest.getDiaChi());
        hoaDon.get().setTienShip(hoaDonGiaoThanhToanRequest.getTienGiao());
        hoaDon.get().setTenNguoiShip(hoaDonGiaoThanhToanRequest.getTenNguoiShip());
        hoaDon.get().setSdtNguoiShip(hoaDonGiaoThanhToanRequest.getSoDienThoaiNguoiShip());
        hoaDon.get().setTrangThai(StatusOrderDetailEnums.XAC_NHAN.getValue());
        hoaDonRepository.save(hoaDon.get());

        for (UUID idGioHangChiTiet : hoaDonGiaoThanhToanRequest.getGioHangChiTietList()) {
            Optional<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findById(idGioHangChiTiet);
            gioHangChiTiet.get().setTrangThai(StatusCartDetailEnums.DA_THANH_TOAN.getValue());
            gioHangChiTietRepository.save(gioHangChiTiet.get());
            Optional<GioHang> gioHang = gioHangRepository.findById(gioHangChiTiet.get().getGioHang().getId());
            gioHang.get().setTrangThai(StatusCartEnums.DA_THANH_TOAN.getValue());
            gioHangRepository.save(gioHang.get());
            if (gioHangChiTiet.isPresent()) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setId(UUID.randomUUID());
                hoaDonChiTiet.setHoaDon(hoaDon.get());
                hoaDonChiTiet.setSanPhamChiTiet(gioHangChiTiet.get().getSanPhamChiTiet());
                hoaDonChiTiet.setDonGia(gioHangChiTiet.get().getSanPhamChiTiet().getSanPham().getGiaBan());
                hoaDonChiTiet.setSoLuong(gioHangChiTiet.get().getSoLuong());
                hoaDonChiTiet.setTrangThai(StatusOrderDetailEnums.XAC_NHAN.getValue());
                hoaDonChiTietRepository.save(hoaDonChiTiet);

                SanPhamChiTiet sanPhamChiTiet = chiTietSanPhamRepository.findById(gioHangChiTiet.get().getSanPhamChiTiet().getId()).get();
                System.out.println(gioHangChiTiet.get().getSanPhamChiTiet().getId());
                sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - gioHangChiTiet.get().getSoLuong());
                chiTietSanPhamRepository.save(sanPhamChiTiet);
            }
        }

        TrangThaiHoaDon trangThaiHoaDon = new TrangThaiHoaDon();
        trangThaiHoaDon.setId(UUID.randomUUID());
        trangThaiHoaDon.setTrangThai(StatusOrderDetailEnums.CHO_XAC_NHAN.getValue());
        trangThaiHoaDon.setThoiGian(timestamp);
        trangThaiHoaDon.setGhiChu("Nhân viên xác nhận đơn cho khách");
        trangThaiHoaDon.setHoaDon(hoaDon.get());
        trangThaiHoaDonRepository.save(trangThaiHoaDon);
        return MessageResponse.builder().message("Thanh Toán Thành Công").build();
    }

    public OrderCounterCartsResponse findByHoaDon(UUID id) {
        return hoaDonRepository.findByHoaDon(id);
    }

    @Override
    public IdGioHangResponse showIdGioHangCt(UUID id) {
        return hoaDonRepository.showIdGioHangCt(id);
    }

}
