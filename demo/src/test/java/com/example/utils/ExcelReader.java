package com.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    // Reads data for a specific scenario name from the Excel sheet
    public static Map<String, String> getTestData(String scenarioName) {
        Map<String, String> data = new HashMap<>();
        // This path assumes the project root is the working directory
        String filePath = "E:\\Learning\\demo\\src\\test\\resources\\test_data.xlsx"; 

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0); 

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell scenarioCell = row.getCell(0);
                // Compare the scenario name from Excel with the name passed from the step
                if (scenarioCell != null && scenarioCell.getStringCellValue().trim().equals(scenarioName.trim())) {
                    
                    // Found the row, now read all columns
                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                        String header = headerRow.getCell(j).getStringCellValue();
                        Cell cell = row.getCell(j);

                        String value = "";
                        if (cell != null) {
                             if (cell.getCellType() == CellType.STRING) {
                                value = cell.getStringCellValue();
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                // For simplicity, treating all numeric data as integers cast to String
                                value = String.valueOf((int)cell.getNumericCellValue());
                            }
                        }
                        data.put(header, value);
                    }
                    return data; // Return the data map immediately
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
            e.printStackTrace();
        }
        return data; // Return empty map if not found
    }
}