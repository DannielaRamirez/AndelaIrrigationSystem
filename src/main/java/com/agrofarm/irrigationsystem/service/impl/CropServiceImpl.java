package com.agrofarm.irrigationsystem.service.impl;

import com.agrofarm.irrigationsystem.dto.CropDTO;
import com.agrofarm.irrigationsystem.entity.Crop;
import com.agrofarm.irrigationsystem.repository.ICropRepository;
import com.agrofarm.irrigationsystem.service.ICropService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CropServiceImpl implements ICropService {

    private ICropRepository cropRepository;

     ModelMapper modelMapper;

    @Override
    public CropDTO createCrop(CropDTO cropDTO) {
        Crop createdCrop = cropRepository.save(modelMapper.map(cropDTO, Crop.class));
        return modelMapper.map(createdCrop, CropDTO.class);
    }
}
