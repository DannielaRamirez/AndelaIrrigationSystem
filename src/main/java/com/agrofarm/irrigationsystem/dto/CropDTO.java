package com.agrofarm.irrigationsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CropDTO {
    private Long id;
    private String crop;
    private Float waterM3ByArea;

}
