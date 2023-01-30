package com.agrofarm.irrigationsystem.controller;

import com.agrofarm.irrigationsystem.dto.CropDTO;
import com.agrofarm.irrigationsystem.service.ICropService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping ("/gestion")
public class CropController {

    ICropService cropService;
    @PostMapping("/crop")
    public ResponseEntity<CropDTO> createCrop(@RequestBody CropDTO cropDTO){
       CropDTO createdCrop = cropService.createCrop(cropDTO);
       return new ResponseEntity<>(createdCrop, HttpStatus.CREATED);
    }
}
