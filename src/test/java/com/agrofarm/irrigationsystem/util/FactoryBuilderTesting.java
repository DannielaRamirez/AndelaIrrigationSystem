package com.agrofarm.irrigationsystem.util;

import com.agrofarm.irrigationsystem.dto.PlotConfigurationDTO;
import com.agrofarm.irrigationsystem.dto.PlotDTO;
import com.agrofarm.irrigationsystem.entity.Plot;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;


public class FactoryBuilderTesting {

    @Mock
    private ModelMapper mapper = new ModelMapper();


    public <T> T buildPlot( String type)
    {
        PlotConfigurationDTO plotConfigurationDTO = new PlotConfigurationDTO();
        plotConfigurationDTO.setSlotTime(20);
        plotConfigurationDTO.setAmountWaterLiters(9.9F);
        plotConfigurationDTO.setDurationMinutes(30);
        plotConfigurationDTO.setIdIrrigationType(4);
        PlotDTO plotDTO = new PlotDTO();
        plotDTO.setIdentifier("MOTH1865");
        plotDTO.setIdRegion(4);
        plotDTO.setIdCrop(3);
        plotDTO.setArea(198787.10F);
        plotDTO.setPlotConfiguration(plotConfigurationDTO);

        if(type.equals("entity"))
            return (T) mapper.map(plotDTO, Plot.class);
        return (T) plotDTO;
    }

    }


