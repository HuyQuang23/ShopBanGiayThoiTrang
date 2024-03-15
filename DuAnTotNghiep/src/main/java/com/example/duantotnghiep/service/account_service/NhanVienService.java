package com.example.duantotnghiep.service.account_service;

import com.example.duantotnghiep.response.NhanVienResponse;

import java.util.List;

public interface NhanVienService {

    List<NhanVienResponse> getAllPage(Integer pageNumber, Integer pageSize);

    NhanVienResponse getList(String name);

}
