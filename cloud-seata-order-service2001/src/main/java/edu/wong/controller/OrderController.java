package edu.wong.controller;

import edu.wong.entities.Order;
import edu.wong.service.OrderService;
import edu.wong.util.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public Result<Order> create(Order order) {
        orderService.create(order);
        return Result.success(order);
    }

}
