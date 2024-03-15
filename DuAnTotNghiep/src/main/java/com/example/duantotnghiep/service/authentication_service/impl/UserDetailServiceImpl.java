package com.example.duantotnghiep.service.authentication_service.impl;

import com.example.duantotnghiep.entity.TaiKhoan;
import com.example.duantotnghiep.model.UserCustomDetails;
import com.example.duantotnghiep.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private TaiKhoanRepository phatTuRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<TaiKhoan> phatTu = phatTuRepository.findByUsername(username);
        return new UserCustomDetails(phatTu.get());
    }
}
