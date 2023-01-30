package com.agrofarm.irrigationsystem.service;

import com.agrofarm.irrigationsystem.dto.PlotDTO;
import com.agrofarm.irrigationsystem.util.FactoryBuilderTesting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class PlotServiceTest {
    @Autowired
    IPlotService iPlotService;

    @Test
    void saveTest() {
        FactoryBuilderTesting factoryBuilder = new FactoryBuilderTesting();
        PlotDTO plotBuilder = factoryBuilder.buildPlot("dto");
        PlotDTO plot = iPlotService.createPlot(plotBuilder);
        assertThat(plot.getId()).isPositive();
    }
}
