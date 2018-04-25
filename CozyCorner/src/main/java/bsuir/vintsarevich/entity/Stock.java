package bsuir.vintsarevich.entity;

import java.util.Objects;

public class Stock {
    Integer productId;
    Integer percent;
    String date;

    public Stock() {
    }

    public Stock(Integer productId, Integer percent, String date) {
        this.productId = productId;
        this.percent = percent;
        this.date = date;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "productId=" + productId +
                ", percent=" + percent +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(productId, stock.productId) &&
                Objects.equals(percent, stock.percent) &&
                Objects.equals(date, stock.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, percent, date);
    }
}
