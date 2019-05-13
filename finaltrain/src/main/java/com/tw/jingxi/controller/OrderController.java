package com.tw.jingxi.controller;

import com.tw.jingxi.entity.*;
import com.tw.jingxi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/orders")
public class OrderController {
    private String ipAddress="49.140.86.22";

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductSnapRepository productSnapRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private LogisticsRecordRepository logisticsRecordRepository;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody List<OrderInfo> orderinfo) throws Exception {
        UserOrder order = new UserOrder();
        Long orderId = orderRepository.saveAndFlush(order).getId();
        String result = createProductSnaps(orderinfo, orderId);
        if (result.equals("success")) {
            order.setTotalPrice(countTotalPrice(orderId));
            lockInventories(orderinfo);
        }
        HttpHeaders responseHeaders = setLocationInHeaders(orderId);
        return new ResponseEntity<>(orderRepository.save(order), responseHeaders, HttpStatus.CREATED);

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserOrder updateOrderStatus(@PathVariable Long id, @RequestParam(value = "orderStatus", required = false, defaultValue = "unPaid") String orderStatus) throws Exception {
        UserOrder order = orderRepository.findById(id).orElseThrow(() -> new Exception("Order"+id+" not found!"));
        if (!isThisOrderPaidOrWithdrawnOrFinished(order, orderStatus)) {
            throw new Exception(id+"is in "+order.getStatus());
        }
        if (orderStatus.equals("paid")) {
            createLogisticsRecord(id);
        } else if (orderStatus.equals("withdrawn")) {
            unlockInventoriesByOrderId(id);
        }
        updateOrderStatusByInputState(id, orderStatus);
        return orderRepository.findUserOrderById(id);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserOrder getOrder(@PathVariable Long id) throws Exception {
        return orderRepository.findById(id).orElseThrow(() -> new Exception("Product"+id+" not found!"));
    }

    @GetMapping
    public List<UserOrder> getUserOrders(@RequestParam Long userId) {
        return orderRepository.findByUserId(userId);
    }

    private void updateOrderStatusByInputState(Long id, String orderStatus) {
        String nowDate = String.valueOf(new Date(System.currentTimeMillis()));
        if (orderStatus.equals("paid")) {
            orderRepository.updateOrderStatusToPaid(id, orderStatus, nowDate);
        } else if (orderStatus.equals("withdrawn")) {
            orderRepository.updateOrderStatusToWithdrawn(id, orderStatus, nowDate);
        }
    }

    private Boolean isThisOrderPaidOrWithdrawnOrFinished(UserOrder order, String orderStatus) {
        final boolean isBeenPaidOrWithdrawnOrFinished = order.getStatus().equals("paid") || order.getStatus().equals("withdrawn") || order.getStatus().equals("finished");
        return (!orderStatus.equals("paid") || !isBeenPaidOrWithdrawnOrFinished) && (!orderStatus.equals("withdrawn") || !isBeenPaidOrWithdrawnOrFinished);
    }

    private HttpHeaders setLocationInHeaders(Long orderId) {
        URI location = URI.create("http://"+ipAddress+":8083/orders/" + orderId);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        return responseHeaders;
    }

    private String createProductSnaps(List<OrderInfo> orderInfo, Long orderId) throws Exception{
        for (OrderInfo info : orderInfo) {
            Product product = productRepository.findById(info.getProductId()).orElseThrow(() -> new Exception("product "+info.getProductId()+" not found!"));
            Inventory inventory = inventoryRepository.findInventoryById(info.getProductId());
            if (inventory.getCount() - inventory.getLockedCount() < info.getPurchaseCount()) {
                throw new Exception(info.getProductId()+" locked!");
            }
            ProductSnap productSnap = new ProductSnap(product.getId(), orderId, product.getName(), product.getDescription(), String.valueOf(product.getPrice()), info.getPurchaseCount());
            productSnapRepository.save(productSnap);
        }
        return "success";
    }

    private String countTotalPrice(Long orderId) {
        List<ProductSnap> products = productSnapRepository.findProductSnapByOrderId(orderId);
        int totalPrice = 0;
        for (ProductSnap product : products) {
            totalPrice += Integer.parseInt(product.getPurchasePrice()) * product.getPurchaseCount();
        }
        return String.valueOf(totalPrice);
    }

    private void lockInventories(List<OrderInfo> orderInfo) {
        for (OrderInfo info : orderInfo) {
            inventoryRepository.updateLockedCount(info.getProductId(), info.getPurchaseCount());
        }
    }

    private void unlockInventoriesByOrderId(Long orderId) {
        List<ProductSnap> products = productSnapRepository.findProductSnapByOrderId(orderId);
        for (ProductSnap product : products) {
            inventoryRepository.updateLockedCount(product.getId(), -product.getPurchaseCount());
        }
    }

    private void createLogisticsRecord(Long orderId) {
        LogisticsRecord logisticsRecord = new LogisticsRecord(orderId, "readyToShip");
        logisticsRecordRepository.save(logisticsRecord);
    }
}