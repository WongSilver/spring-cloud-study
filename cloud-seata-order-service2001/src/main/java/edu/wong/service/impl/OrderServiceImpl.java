package edu.wong.service.impl;

import edu.wong.apis.AccountFeignApi;
import edu.wong.apis.StorageFeignApi;
import edu.wong.entities.Order;
import edu.wong.mapper.OrderMapper;
import edu.wong.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StorageFeignApi storageFeignApi;
    @Resource
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "create-order-transaction", rollbackFor = Exception.class)
    public void create(Order order) {
        // xid全局事务检查
        String xid = RootContext.getXID();

        // 1. 新建订单
        log.info("-------------> 开始新建订单, XID: {}", xid);
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);

        Order orderFromDB;
        if (result > 0) {
            orderFromDB = orderMapper.selectOne(order);
            log.info("-------------> 新建订单成功, OrderInfo: {}", orderFromDB);

            // 2. 扣减库存
            log.info("-------------> 开始扣减库存");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("-------------> 扣减库存成功");

            // 3. 扣减账户余额
            log.info("-------------> 开始扣减余额");
            accountFeignApi.decrease(order.getUserId(), order.getMoney());
            log.info("-------------> 扣余额存成功");

            // 4. 修改订单状态
            log.info("-------------> 开始修改订单状态");
            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("id", orderFromDB.getId());
            criteria.andEqualTo("status", 0);
            orderFromDB.setStatus(1);
            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);
            log.info("-------------> 修改订单状态成功");


        }
        log.info("-------------> 结束新建订单, XID: {}", xid);
    }
}
