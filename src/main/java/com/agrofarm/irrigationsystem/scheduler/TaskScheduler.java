package com.agrofarm.irrigationsystem.scheduler;

import com.agrofarm.irrigationsystem.entity.PlotConfiguration;
import com.agrofarm.irrigationsystem.exception.ResourceNotConfigureException;
import com.agrofarm.irrigationsystem.service.IConnectSensorService;
import com.agrofarm.irrigationsystem.service.IPlotConfigurationService;
import com.agrofarm.irrigationsystem.service.IUpsateStatusService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Component
public class TaskScheduler {

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private IConnectSensorService connectSensorService;
    private IUpsateStatusService upsateStatusService;
    private IPlotConfigurationService plotConfigurationService;


    @PostConstruct
    public void scheduleRunnableWithCronTrigger() {

        List<PlotConfiguration> plotToIrrigator =plotConfigurationService.findConfigurationActive();

        plotToIrrigator.stream()
                        .forEach(p -> threadPoolTaskScheduler.scheduleAtFixedRate(new RunnableTask(p), p.getSlotTime()));
    }

    class RunnableTask implements Runnable {
        private PlotConfiguration plotConfiguration;
        public RunnableTask(PlotConfiguration plotConfiguration) {
            this.plotConfiguration = plotConfiguration;
        }

        @Override
        public void run() {
            try {
                log.info("CALL SENSOR plotId: " + plotConfiguration.getPlot().getId() + " " + Thread.currentThread().getName());
                int response = connectSensorService.connectServer(plotConfiguration);
                if (response == 200) {
                    upsateStatusService.updateStatus(plotConfiguration.getPlot().getId());
                }
            }catch (Exception e){
                Thread.currentThread().interrupt();

            }

        }
    }
}