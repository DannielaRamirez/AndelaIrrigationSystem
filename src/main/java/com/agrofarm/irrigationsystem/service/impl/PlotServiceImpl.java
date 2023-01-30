package com.agrofarm.irrigationsystem.service.impl;

import com.agrofarm.irrigationsystem.dto.PlotConfigurationDTO;
import com.agrofarm.irrigationsystem.dto.PlotDTO;
import com.agrofarm.irrigationsystem.entity.Plot;
import com.agrofarm.irrigationsystem.entity.PlotConfiguration;
import com.agrofarm.irrigationsystem.exception.ResourceNotFoundException;
import com.agrofarm.irrigationsystem.repository.IPlotConfigurationRepository;
import com.agrofarm.irrigationsystem.repository.IPlotRepository;
import com.agrofarm.irrigationsystem.service.IPlotService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PlotServiceImpl implements IPlotService {

    private IPlotRepository plotRepository;

    private IPlotConfigurationRepository plotConfigurationRepository;
    private ModelMapper modelMapper;

    @Override
    public PlotDTO createPlot(PlotDTO plotDTO) {
        PlotConfiguration plotConfiguration = plotConfigurationRepository.save(modelMapper.map(plotDTO.getPlotConfiguration(), PlotConfiguration.class));
        plotDTO.getPlotConfiguration().setId(plotConfiguration.getId());
        Plot plot = plotRepository.save(modelMapper.map(plotDTO, Plot.class));
        return modelMapper.map(plot, PlotDTO.class);
    }

    @Override
    public PlotDTO editPlot(PlotDTO plotDTO) {
        Plot existPlot = plotRepository.findById(plotDTO.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Identifier: " + plotDTO.getIdentifier() + "doesn't exist", HttpStatus.NOT_FOUND.toString()));

        if(plotDTO.getPlotConfiguration() != null){
            PlotConfiguration plotConfiguration =saveConfiguration(existPlot.getPlotConfiguration().getId(),plotDTO.getPlotConfiguration());
            existPlot.setPlotConfiguration(plotConfiguration);
        }
        if(plotDTO.getIdCrop() != null){existPlot.setIdCrop(plotDTO.getIdCrop());}
        if(plotDTO.getIdRegion() != null){existPlot.setIdRegion(plotDTO.getIdRegion());}
        if(plotDTO.getArea() != null){existPlot.setArea(plotDTO.getArea());}
        Plot plot = plotRepository.save(existPlot);
        return modelMapper.map(plot, PlotDTO.class);
    }


    private PlotConfiguration saveConfiguration(Long idPlotConfiguation, PlotConfigurationDTO plotConfigurationDTO){

        PlotConfiguration exitPlotConfiguration = plotConfigurationRepository.findById(idPlotConfiguation)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PlotConfiguration: " +plotConfigurationDTO.getId() +"doesn't exist", HttpStatus.NOT_FOUND.toString()));

        if(plotConfigurationDTO.getIdIrrigationType() != null){exitPlotConfiguration.setIdIrrigationType(plotConfigurationDTO.getIdIrrigationType());}
        if(plotConfigurationDTO.getDurationMinutes() != null){exitPlotConfiguration.setDurationMinutes(plotConfigurationDTO.getDurationMinutes());}
        if(plotConfigurationDTO.getSlotTime() != null){exitPlotConfiguration.setSlotTime(plotConfigurationDTO.getSlotTime());}
        if(plotConfigurationDTO.getAmountWaterLiters() != null){exitPlotConfiguration.setAmountWaterLiters(plotConfigurationDTO.getAmountWaterLiters());}
        return plotConfigurationRepository.save(modelMapper.map(exitPlotConfiguration, PlotConfiguration.class));
    }


    @Override
    public List<PlotDTO> getAllPlot() {
        List<Plot> listPlot = plotRepository.findAll();
        return listPlot.stream().map(p -> modelMapper.map(p, PlotDTO.class)).toList();

    }
}
