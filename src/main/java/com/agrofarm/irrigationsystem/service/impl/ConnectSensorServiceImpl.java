package com.agrofarm.irrigationsystem.service.impl;

import com.agrofarm.irrigationsystem.entity.PlotConfiguration;
import com.agrofarm.irrigationsystem.exception.ResourceNotConfigureException;
import com.agrofarm.irrigationsystem.service.IConnectSensorService;
import com.agrofarm.irrigationsystem.utils.AlertSystem;
import com.agrofarm.irrigationsystem.utils.AppProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class ConnectSensorServiceImpl implements IConnectSensorService {

    private AppProperties appProperties;

    @Override
    @Retryable(
            value = {ResourceNotConfigureException.class}, maxAttempts = 4, backoff = @Backoff(2000))
    public int connectServer(PlotConfiguration plotConfiguration) {
        int statusCode = 0;
        var client = HttpClient.newBuilder().build();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(appProperties.getUrl()+plotConfiguration.getPlot().getId()))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int atemp = RetrySynchronizationManager.getContext().getRetryCount();
            if (response.statusCode() == HttpStatus.LOCKED.value()) {
                log.info("ERROR: Device not configure. Retry Number : {}" + " " +LocalDateTime.now(),atemp);
                if(atemp > 2)
                    AlertSystem.sendMail(plotConfiguration.getPlot());
                throw new ResourceNotConfigureException(plotConfiguration.getPlot().getIdentifier() + " Attemp: " +atemp, HttpStatus.LOCKED);
            }
            log.info("Start Irrigation :: Execution Time - {}" + plotConfiguration.getPlot().getId() + " on thread " + Thread.currentThread().getName() +" " + LocalDateTime.now());
            statusCode= response.statusCode();
        } catch (IOException ex) {
            log.error("ERROR: Unexpected response status: " + ex.getCause());
            Thread.currentThread().interrupt();
            throw new NullPointerException("This error message if for demo only.");
        }catch(InterruptedException ex){
            log.error("ERROR: Unexpected response status: " + ex.getCause());
            Thread.currentThread().interrupt();
        }
        return statusCode;
    }

}
