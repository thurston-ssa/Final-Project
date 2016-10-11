package com.ssa.ironyard.fitness.serviceTests;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.ssa.ironyard.fitness.utils.FileLoadingService;

public class LoadingServiceTest {

    //@Test
    public void test() throws URISyntaxException, IOException {
        FileLoadingService fls = new FileLoadingService();
        fls.intialize();
    }

}
