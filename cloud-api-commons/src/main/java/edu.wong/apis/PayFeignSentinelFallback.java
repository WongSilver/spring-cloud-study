package edu.wong.apis;

import edu.wong.entities.PayDTO;
import edu.wong.util.Result;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelFallback implements PayFeignSentinelApi {
    @Override
    public Result<PayDTO> getPayByOrderNo(String orderNo) {
        return Result.fail("服务不可达");
    }
}
