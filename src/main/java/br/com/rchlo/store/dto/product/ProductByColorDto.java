package br.com.rchlo.store.dto.product;

import br.com.rchlo.store.domain.Color;

public class ProductByColorDto {

    private final String color;
    private final long amount;

    public ProductByColorDto(Color color, long amount) {
        this.color = color.getDescription();
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "ProductByColorDto{" +
            "color='" + color + '\'' +
            ", amount=" + amount +
            '}';
    }
}
