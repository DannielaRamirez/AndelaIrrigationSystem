package com.agrofarm.irrigationsystem.service;

import com.agrofarm.irrigationsystem.dto.PlotDTO;

import java.util.List;

public interface IPlotService  {

     PlotDTO createPlot(PlotDTO plotDTO);
     PlotDTO editPlot(PlotDTO plotDTO);
     List<PlotDTO> getAllPlot();
}
