package com.example.duantotnghiep.service.account_service;

import com.example.duantotnghiep.entity.TaiKhoan;
import com.example.duantotnghiep.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface TaiKhoanService {
    Optional<TaiKhoan> findByUserName(String username);

    @Service
    class TaiKhoanServiceImpl implements TaiKhoanService {

        @Autowired
        private TaiKhoanRepository taiKhoanRepository;

        @Override
        public Optional<TaiKhoan> findByUserName(String username) {
            return taiKhoanRepository.findByUsername(username);
        }
    }
}
