package practice3;

import java.math.BigDecimal;

public class PriceCaculator {
    private Order order;
    private BigDecimal subTotal;
    private BigDecimal tax;
    private BigDecimal grandTotal;

    public PriceCaculator(Order orderIn) {
        order = orderIn;
        subTotal  = new BigDecimal(0);
    }

    public BigDecimal calculate() {
        // Subtract discounts to get total price
        subTotal = getTotalPrice().subtract(getDiscounts());

        // calculate tax
        tax = subTotal.multiply(order.getTax());

        // calculate GrandTotal
        grandTotal = subTotal.add(tax);

        return grandTotal;
    }

    private BigDecimal getDiscounts() {
        BigDecimal result = tax = new BigDecimal(0);
        for (BigDecimal discount : order.getDiscounts()) {
            result = result.add(discount);
        }
        return result;
    }

    // Total up line items
    private BigDecimal getTotalPrice() {
        BigDecimal result = tax = new BigDecimal(0);
        for (OrderLineItem lineItem : order.getOrderLineItemList()) {
            result = result.add(lineItem.getPrice());
        }
        return result;
    }
}