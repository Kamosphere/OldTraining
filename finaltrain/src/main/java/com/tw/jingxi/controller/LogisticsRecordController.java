package com.tw.jingxi.controller;

import com.tw.jingxi.entity.LogisticsRecord;
import com.tw.jingxi.entity.ProductSnap;
import com.tw.jingxi.repository.InventoryRepository;
import com.tw.jingxi.repository.LogisticsRecordRepository;
import com.tw.jingxi.repository.OrderRepository;
import com.tw.jingxi.repository.ProductSnapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/logisticsRecords")
public class LogisticsRecordController {
    @Autowired
    private LogisticsRecordRepository logisticsRecordRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductSnapRepository productSnapRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public LogisticsRecord getLogisticsRecord(@PathVariable Long id) throws Exception {
        LogisticsRecord logisticsRecord = logisticsRecordRepository.findById(id).orElseThrow(() -> new Exception("logisticsRecord "+id+" not found"));
        return logisticsRecord;
    }

    @PutMapping("{id}/orders/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable Long id, @PathVariable Long orderId, @RequestParam String logisticsStatus) throws Exception {
        LogisticsRecord logisticsRecord = logisticsRecordRepository.findLogisticsRecordByIdAndOrderId(id, orderId);
        String nowDate = String.valueOf(new Date(System.currentTimeMillis()));
        if (logisticsRecord == null) {
            throw new Exception("logisticsRecord is invalid in"+"expressId:"+id+"orderId:"+orderId);
        }
        final boolean isExpressAlreadyShippedOrSigned = logisticsRecord.getLogisticsStatus().equals("shipping") || logisticsRecord.getLogisticsStatus().equals("signed");
        if (logisticsStatus.equals("shipping") && isExpressAlreadyShippedOrSigned) {
            throw new Exception(id+"is in "+ logisticsRecord.getLogisticsStatus());
        } else if (logisticsStatus.equals("signed")) {
            String result = checkWhetherCanSignExpress(logisticsRecord);
            if (!result.equals("success")) {
                throw new Exception(result);
            }
            updateOrderStatusAndInventories(orderId, nowDate);
        }
        updateLogisticsStatus(logisticsStatus, id, orderId, nowDate);
    }

    private void updateOrderStatusAndInventories(Long orderId, String nowDate) {
        orderRepository.updateOrderStatusToFinished(orderId, "finished", nowDate);
        updateInventoriesAfterSignedOff(orderId);
    }

    private void updateLogisticsStatus(String expressStatus, Long id, Long orderId, String nowDate) {
        if (expressStatus.equals("shipping")) {
            logisticsRecordRepository.updateLogisticsStatusWithShipping(id, orderId, nowDate);
        } else if (expressStatus.equals("signed")) {
            logisticsRecordRepository.updateLogisticsStatusWithSigned(id, orderId, nowDate);
        }
    }

    private String checkWhetherCanSignExpress(LogisticsRecord logisticsRecord) {
        if (logisticsRecord.getLogisticsStatus().equals("readyToShip")) {
            return "The logisticsRecord hasn't been shipped yet.";
        }
        if (logisticsRecord.getLogisticsStatus().equals("signed")) {
            return "The logisticsRecord has already been signed off.";
        }
        return "success";
    }

    private void updateInventoriesAfterSignedOff(Long orderId) {
        List<ProductSnap> products = productSnapRepository.findProductSnapByOrderId(orderId);
        for (ProductSnap product : products) {
            inventoryRepository.updateCountById(product.getId(), -product.getPurchaseCount());
            inventoryRepository.updateLockedCount(product.getId(), -product.getPurchaseCount());
        }
    }

}