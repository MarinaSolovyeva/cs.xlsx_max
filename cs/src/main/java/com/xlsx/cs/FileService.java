package com.xlsx.cs;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FileService {

    public int getNMaxNum(String filePath, int n) {
        validateFilePath(filePath);
        File file = new File(filePath);
        List<Integer> numbers = readNumbersFromXlsx(file);
        if (n <= 0 || n > numbers.size()) {
            throw new IllegalArgumentException("Введенное значение должно быть больше количества чисел в файле");
        }
        return findNMaxNumByPartition(numbers, 0, numbers.size() - 1, n - 1);
    }

    private List<Integer> readNumbersFromXlsx(File file) {
        List<Integer> numbers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    numbers.add((int) cell.getNumericCellValue());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении файла: " + e.getMessage(), e);
        }
        return numbers;
    }


    private int findNMaxNumByPartition(List<Integer> nums, int left, int right, int k) {
        if (left == right) return nums.get(left);

        Random rand = new Random();
        int randomValueIdx = left + rand.nextInt(right - left + 1);
        randomValueIdx = partitionByDesc(nums, left, right, randomValueIdx);

        if (k == randomValueIdx) return nums.get(k);
        else if (k < randomValueIdx) return findNMaxNumByPartition(nums, left, randomValueIdx - 1, k);
        else return findNMaxNumByPartition(nums, randomValueIdx + 1, right, k);
    }

    private int partitionByDesc(List<Integer> nums, int left, int right, int randomValueIdx) {
        int randomValue = nums.get(randomValueIdx);
        swapElements(nums, randomValueIdx, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (nums.get(i) > randomValue) {
                swapElements(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swapElements(nums, storeIndex, right);
        return storeIndex;
    }

    private void swapElements(List<Integer> nums, int i, int j) {
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

    private void validateFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Путь к файлу не должен быть пустым.");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не найден: " + filePath);
        }

        if (!file.isFile()) {
            throw new IllegalArgumentException("Указанный путь не является файлом: " + filePath);
        }

        if (!filePath.toLowerCase().endsWith(".xlsx")) {
            throw new IllegalArgumentException("Файл должен иметь расширение .xlsx");
        }
    }
}
