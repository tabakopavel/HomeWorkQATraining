package com.epam;

import com.epam.Driver.Driver;
import org.testng.annotations.*;
import org.testng.annotations.Test;

@Test
public class BaseScenario {

    @BeforeClass
    public void init() {
        Driver.init();
    }

    @AfterClass(alwaysRun = false)
    public void clean() {
        Driver.clean();
    }
}