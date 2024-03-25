package edu.wong.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlowLimitService {

    @SentinelResource("common")
    public void common() {
        log.info("into ... common ...");
    }
}
