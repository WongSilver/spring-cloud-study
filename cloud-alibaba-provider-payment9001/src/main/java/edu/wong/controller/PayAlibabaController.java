package edu.wong.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import edu.wong.entities.PayDTO;
import edu.wong.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/pay/nacos/{id}")
    public String getPayInfo(@PathVariable("id") Integer id) {
        return "nacos registry serverPost: " + serverPort + ", id: " + id;
    }

    // openfeign和sentinel
    @GetMapping("/pay/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
    public Result<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        // 模拟查询
        PayDTO payDTO = new PayDTO(1024, orderNo, "pay" + IdUtil.simpleUUID(), 1, BigDecimal.valueOf(9.9));
        return Result.success(payDTO);
    }

    public Result<PayDTO> handlerBlockHandler(String orderNo, BlockException e) {
        return Result.fail("服务提供者" + e.getMessage());
    }
}
