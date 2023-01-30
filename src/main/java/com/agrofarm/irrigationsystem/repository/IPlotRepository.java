package com.agrofarm.irrigationsystem.repository;


import com.agrofarm.irrigationsystem.entity.Plot;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IPlotRepository extends JpaRepository<Plot, Long> {
}
