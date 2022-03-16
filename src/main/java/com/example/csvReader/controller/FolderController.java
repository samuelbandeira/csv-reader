package com.example.csvReader.controller;

import com.example.csvReader.dto.FolderDTO;
import com.example.csvReader.facade.FolderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api")
public class FolderController {

    @Autowired
    private FolderFacade folderFacade;

    @PostMapping(value = "/csv/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleCsvUpload(@RequestBody MultipartFile file) {
        folderFacade.save(file);
    }

    @GetMapping(value = "/folders")
    public Page<FolderDTO> findByName(@RequestParam(name = "item_name", required = false) String itemName,
                                      @RequestParam(name = "sort", required = false) String sortProperty,
                                      @RequestParam(name = "page",required = false) int page,
                                      @RequestParam(name = "limit", required = false) int limit) {
        return folderFacade.findByName(itemName, sortProperty, PageRequest.of(page, limit));
    }
}
