package com.example.duantotnghiep.service.file_service.impl;

import com.example.duantotnghiep.entity.ChatLieu;
import com.example.duantotnghiep.service.file_service.IFileUploaderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FileUploaderServiceImpl implements IFileUploaderService {
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;
    public List<ChatLieu> invoiceExcelReaderService() {
        return null;
    }
    @Override
    public void uploadFile(MultipartFile file) {
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }

    }
}
