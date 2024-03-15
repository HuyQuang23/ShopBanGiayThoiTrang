package com.example.duantotnghiep.service.file_service;

import com.example.duantotnghiep.entity.ChatLieu;

import java.util.List;

public interface IExcelDataService {
    List<ChatLieu> getExcelDataAsList();

    int saveExcelData(List<ChatLieu> invoices);
}
