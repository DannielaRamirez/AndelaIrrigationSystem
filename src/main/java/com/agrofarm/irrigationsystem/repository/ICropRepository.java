package com.agrofarm.irrigationsystem.repository;

import com.agrofarm.irrigationsystem.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICropRepository extends JpaRepository<Crop, Long> {

}
