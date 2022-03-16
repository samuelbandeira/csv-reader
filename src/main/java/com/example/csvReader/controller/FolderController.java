package com.example.csvReader.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FolderController {

    @PostMapping
    public void handleCsvUpload(@RequestParam("file") MultipartFile file) {

    }
}
