package com.example.csvReader.controller;

import com.example.csvReader.dto.FolderDTO;
import com.example.csvReader.facade.FolderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(name = "/api")
public class FolderController {

    @Autowired
    private FolderFacade folderFacade;

    @PostMapping(name = "/csv/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleCsvUpload(@RequestParam("file") MultipartFile file) {
        folderFacade.save(file);
    }

    @GetMapping(name = "/folder")
    public Page<FolderDTO> findByName(@RequestParam(name = "item_name") String itemName,
                                      @RequestParam(name = "sort") String sortProperty,
                                      @RequestParam(name = "page") int page,
                                      @RequestParam(name = "limit") int limit) {
        return folderFacade.findByName(itemName, sortProperty, PageRequest.of(page, limit));
    }
}
