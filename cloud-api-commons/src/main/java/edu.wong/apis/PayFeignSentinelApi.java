package edu.wong.apis;

import edu.wong.entities.PayDTO;
import edu.wong.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-pay-provider", fallback = PayFeignSentinelFallback.class)
public interface PayFeignSentinelApi {


    @GetMapping("/pay/nacos/get/{orderNo}")
    Result<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}
