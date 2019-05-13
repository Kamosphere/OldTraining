package com.tw.jingxi.entity;

public class OrderInfo {
    private Long productId;
    private Integer purchaseCount;

    public OrderInfo(Long productId, Integer purchaseCount) {
        this.productId = productId;
        this.purchaseCount = purchaseCount;
    }

    public OrderInfo() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }
}
