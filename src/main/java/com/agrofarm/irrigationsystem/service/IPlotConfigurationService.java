package com.agrofarm.irrigationsystem.service;

import com.agrofarm.irrigationsystem.entity.PlotConfiguration;

import java.util.List;

public interface IPlotConfigurationService {
    List<PlotConfiguration> findConfigurationActive();

}
