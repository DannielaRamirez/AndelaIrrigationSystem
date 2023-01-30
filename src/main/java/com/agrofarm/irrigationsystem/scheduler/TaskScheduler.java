package com.agrofarm.irrigationsystem.scheduler;

import com.agrofarm.irrigationsystem.service.IConnectSensorService;
import com.agrofarm.irrigationsystem.service.IPlotConfigurationService;
import com.agrofarm.irrigationsystem.service.IUpsateStatusService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

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
        Map<Long, Integer> plot = new HashMap<>();
        List<Object[]> plotToIrrigator =plotConfigurationService.findConfigurationActive();

        plotToIrrigator.stream().forEach(obj ->plot.put((Long) obj[0], (Integer)obj[1]));

        plot.entrySet()
                .stream()
                .forEach(p -> threadPoolTaskScheduler.scheduleAtFixedRate(new RunnableTask(p.getKey()), p.getValue()));
    }

    class RunnableTask implements Runnable {
        private Long plotId;
        public RunnableTask(Long plotId) {
            this.plotId = plotId;
        }

        @Override
        public void run() {
            try{
                log.info("CALL SENSOR plotId: " + plotId + " " + Thread.currentThread().getName());
                int response = connectSensorService.connectServer(plotId);
            if(response == 200){
                upsateStatusService.updateStatus(plotId);
            }
            }catch(Exception e){
             log.error("Irrigation Fail" + e.getCause());
             Thread.currentThread().interrupt();
             throw new RuntimeException(e);
            }
        }
    }
}