package br.com.caelum.demokafka;

import java.math.BigDecimal;
import java.util.StringJoiner;
import java.util.UUID;

public class PurchaseFinished {

    private UUID id;
    private String product;
    private BigDecimal price;

    private PurchaseFinished() {}

    public PurchaseFinished(String product, BigDecimal price) {
        this.id = UUID.randomUUID();
        this.product = product;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PurchaseFinished.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("product='" + product + "'")
                .add("price=" + price)
                .toString();
    }
}
