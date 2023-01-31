package com.agrofarm.irrigationsystem.repository;

import com.agrofarm.irrigationsystem.entity.PlotConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPlotConfigurationRepository extends JpaRepository<PlotConfiguration, Long> {
@Query(value= "select c from PlotConfiguration c where c.isActive = true")
List<PlotConfiguration> findConfigurationActive();
}
