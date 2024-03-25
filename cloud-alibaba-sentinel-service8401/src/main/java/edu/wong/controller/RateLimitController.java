package edu.wong.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RateLimitController {
    @GetMapping("/rateLimitByUrl")
    public String rateLimitByUrl() {
        return "限流测试未使用注解";
    }

    @GetMapping("/rateLimitByResource")
    @SentinelResource(value = "ByResource", blockHandler = "byResourceHandler")
    public String rateLimitByResource() {
        return "限流测试使用注解, 返回指定的字符串";
    }

    public String byResourceHandler(BlockException blockException) {
        return "服务不可用, 这是自定义返回的字符串";
    }

    @GetMapping("/rateLimitByFallback/{i}")
    @SentinelResource(value = "rateLimitByFallback", blockHandler = "byBlockHandler", fallback = "byFallback")
    public String rateLimitByFallback(@PathVariable("i") Integer i) {
        if (i == 0) {
            throw new RuntimeException("i == 0 异常");
        }
        return "使用注解并使用, Fallback";
    }

    public String byBlockHandler(@PathVariable("i") Integer i, BlockException blockException) {
        log.error("配置了自定义限流, {}", blockException.getMessage());
        return "服务不可用, 这是自定义返回的字符串";
    }

    public String byFallback(@PathVariable("i") Integer i, Throwable throwable) {
        log.error("程序逻辑异常, {}", throwable.getMessage());
        return "逻辑异常, 这是自定义返回的字符串";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "testHotKeyBlockHandler")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "testHotKey ...";
    }

    public String testHotKeyBlockHandler(@RequestParam(value = "p1", required = false) String p1,
                                         @RequestParam(value = "p2", required = false) String p2, BlockException blockException) {
        return "testHotKey blockException ...";
    }

}
