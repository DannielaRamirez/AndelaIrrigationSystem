package com.agrofarm.irrigationsystem.controller;

import com.agrofarm.irrigationsystem.dto.PlotConfigurationDTO;
import com.agrofarm.irrigationsystem.dto.PlotDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class PlotControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectmapper;

    @Test
    void createPlotTest() throws Exception {
        PlotConfigurationDTO plotConfigurationDTO = new PlotConfigurationDTO
                (null, 4, 15, 18000, 4.9F, true);
        PlotDTO plotDTO = new PlotDTO(null, "IOPS0986", 5, 3, 8976.89F, plotConfigurationDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/gestion/plot")
                        .content(objectmapper.writeValueAsString(plotDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }


    @Test
    void editPlot() throws Exception {
        PlotConfigurationDTO plotConfigurationDTO = new PlotConfigurationDTO();
        plotConfigurationDTO.setSlotTime(20);
        plotConfigurationDTO.setAmountWaterLiters(9.9F);
        PlotDTO plotDTO = new PlotDTO();
        plotDTO.setArea(1000.10F);
        plotDTO.setPlotConfiguration(plotConfigurationDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/gestion/plot/{id}", 1)
                        .content(objectmapper.writeValueAsString(plotDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.area").value("1000.1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.plotConfiguration.slotTime").value("20"));
    }


    @Test
    void getAllPlotTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/gestion/plot")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists()
                );
    }


}



