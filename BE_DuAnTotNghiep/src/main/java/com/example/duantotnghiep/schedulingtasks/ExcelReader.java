package com.example.duantotnghiep.schedulingtasks;


import com.example.duantotnghiep.entity.ChatLieu;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Component
public class ExcelReader {
    public List<ChatLieu> readExcel(MultipartFile file) {
        List<ChatLieu> entities = new ArrayList<>();
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                ChatLieu entity = new ChatLieu();
                String idAsString = getStringValue(cellIterator.next());
                entity.setId(UUID.fromString(idAsString));
                // Assuming the first cell is an ID, adjust accordingly
                entity.setTenChatLieu(getStringValue(cellIterator.next()));

                // Set other fields similarly

                entities.add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return entities;
    }

    private String getStringValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Handle numeric values if needed
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }
}