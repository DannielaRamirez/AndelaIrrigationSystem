package com.agrofarm.irrigationsystem.service;

import com.agrofarm.irrigationsystem.entity.PlotConfiguration;

import java.io.IOException;

public interface IConnectSensorService {

    int connectServer(PlotConfiguration plotConfiguration) throws IOException, InterruptedException;


}
