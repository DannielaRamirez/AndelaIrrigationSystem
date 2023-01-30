package com.agrofarm.irrigationsystem.service;

import java.io.IOException;

public interface IConnectSensorService {

    int connectServer(Long iPlotId) throws IOException, InterruptedException;


}
