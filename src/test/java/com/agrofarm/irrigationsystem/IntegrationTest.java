package com.agrofarm.irrigationsystem;

import com.agrofarm.irrigationsystem.controller.PlotControllerTest;
import com.agrofarm.irrigationsystem.repository.PlotRepositoryTest;
import com.agrofarm.irrigationsystem.service.PlotServiceTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
@SelectClasses( {PlotControllerTest.class, PlotServiceTest.class, PlotRepositoryTest.class} )
public class IntegrationTest {

}



