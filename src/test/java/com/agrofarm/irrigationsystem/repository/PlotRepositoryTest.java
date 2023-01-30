package com.agrofarm.irrigationsystem.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlotRepositoryTest {
   @Autowired
    IPlotRepository iPlotRepository;

    @Test
    void getAllPlotTest() {
        assertThat(iPlotRepository.findAll()).size().isPositive();
    }

}
