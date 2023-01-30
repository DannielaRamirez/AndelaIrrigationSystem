package com.agrofarm.irrigationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlotDTO {
    private Long id;
    private String identifier;
    private Integer idRegion;
    private Integer idCrop;
    private Float area;
    private PlotConfigurationDTO plotConfiguration;

}
