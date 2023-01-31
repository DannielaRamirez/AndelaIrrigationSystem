package com.agrofarm.irrigationsystem.service.impl;

import com.agrofarm.irrigationsystem.entity.PlotConfiguration;
import com.agrofarm.irrigationsystem.repository.IPlotConfigurationRepository;
import com.agrofarm.irrigationsystem.service.IPlotConfigurationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PlotConfigurationServiceImpl implements IPlotConfigurationService {

    private IPlotConfigurationRepository iPlotConfigurationRepository;

    @Override
    public List<PlotConfiguration> findConfigurationActive() {
        return iPlotConfigurationRepository.findConfigurationActive();
    }
}
