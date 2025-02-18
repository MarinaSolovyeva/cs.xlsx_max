package com.xlsx.cs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/xlsx")
@Tag(name = "File Controller", description = "Работа с файлами")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/max-num")
    @Operation(summary = "Поиск N-ного максимального числа из файла с расширением xlsx")
    public Integer getNMaxNum(@RequestParam String filePath, @RequestParam int n) {
        return fileService.getNMaxNum(filePath, n);
    }


}
