package com.xlsx.cs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/xlsx")
@Tag(name = "File Controller", description = "Чтение и обработка данных из файлов с расширением xlsx")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/max-num")
    @Operation(summary = "Найти N-оe максимальное значение в файле с расширением xlsx",
            description = "Считывает целые числа из первого столбца файла и возвращает N-е по величине число.")
    public Integer getNthMaxNumber(
            @Parameter(description = "Полный путь к файлу с расширением xlsx", example = "D:\\=Projects\\Projects\\Interview\\interview-xlms-max\\TestFile.xlsx")
            @RequestParam String filePath,

            @Parameter(description = "Значение N, какой искать по величине максимум", example = "3")
            @RequestParam int n) {
        return fileService.getNMaxNum(filePath, n);
    }

}
