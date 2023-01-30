package com.agrofarm.irrigationsystem.controller;


import com.agrofarm.irrigationsystem.dto.PlotDTO;
import com.agrofarm.irrigationsystem.service.IPlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/gestion/plot")
public class PlotController {
    private IPlotService plotService;
    @PostMapping
    public ResponseEntity<PlotDTO> createPlot(@RequestBody PlotDTO plotDTO){
       PlotDTO createdPlot= plotService.createPlot(plotDTO);
       return new ResponseEntity<>(createdPlot, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlotDTO> editPlot(@PathVariable ("id") Long id, @RequestBody PlotDTO plotDTO){
        plotDTO.setId(id);
        PlotDTO editedPlot = plotService.editPlot(plotDTO);
        return new ResponseEntity<>(editedPlot, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PlotDTO>> getAllPlot(){
        List<PlotDTO> listPlotDTO = plotService.getAllPlot();
        return new ResponseEntity<>(listPlotDTO, HttpStatus.OK);
    }
}
