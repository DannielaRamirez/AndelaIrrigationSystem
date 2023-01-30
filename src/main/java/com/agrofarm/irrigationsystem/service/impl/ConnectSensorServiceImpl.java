package com.agrofarm.irrigationsystem.service.impl;

import com.agrofarm.irrigationsystem.exception.ResourceNotConfigure;
import com.agrofarm.irrigationsystem.service.IConnectSensorService;
import com.agrofarm.irrigationsystem.utils.AppProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
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
            value = {ResourceNotConfigure.class}, maxAttempts = 3, backoff = @Backoff(2000))
    public int connectServer(Long plotId) {

        int statusCode = 0;
        var client = HttpClient.newBuilder().build();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(appProperties.getUrl()+plotId))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatus.LOCKED.value()) {
                log.info("ERROR: Device not configure " + LocalDateTime.now() );
                throw new ResourceNotConfigure();
            }
            log.info("Start Irrigation :: Execution Time - {}" + plotId + " on thread " + Thread.currentThread().getName() +" " + LocalDateTime.now());
            statusCode= response.statusCode();
        } catch (IOException | InterruptedException e) {
            log.error("ERROR: Unexpected response status: " + e.getCause());
            Thread.currentThread().interrupt();
        }
        return statusCode;
    }
}
