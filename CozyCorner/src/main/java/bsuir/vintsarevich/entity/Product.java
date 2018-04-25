package bsuir.vintsarevich.entity;

import java.util.Objects;

/**
 * class Product created to work with products
 */
public class Product {

    private Integer id;
    private String type;
    private String nameRu;
    private String nameEn;
    private Integer weight;
    private Double cost;
    private String descriptionRu;
    private String descriptionEn;
    private String imagePath;
    private Integer number;
    private Integer ordered;
    private Integer orderId;
    private Double commonCost;
    private Integer percent;
    private String stockDate;

    public Product() {
    }

    public Product(Integer id, String type, String nameRu, String nameEn, Integer weight, Double cost,
                   String descriptionRu, String descriptionEn, String imagePath, Integer number, Integer ordered,
                   Integer orderId) {
        this.id = id;
        this.type = type;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.weight = weight;
        this.cost = cost;
        this.descriptionRu = descriptionRu;
        this.descriptionEn = descriptionEn;
        this.imagePath = imagePath;
        this.number = number;
        this.ordered = ordered;
        this.orderId = orderId;
    }

    public void setCommonCost() {
        if (percent != null) {
            double newCost = cost * (1 - ((double)percent / 100));
            commonCost = Math.rint(100.0 * (newCost * number)) / 100.0;
        } else {
            commonCost = Math.rint(100.0 * (cost * number)) / 100.0;
        }
    }

    public Double getCommonCost() {
        return commonCost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setCommonCost(Double commonCost) {
        this.commonCost = commonCost;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getStockDate() {
        return stockDate;
    }

    public void setStockDate(String stockDate) {
        this.stockDate = stockDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", nameRu='" + nameRu + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                ", descriptionRu='" + descriptionRu + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", number=" + number +
                ", ordered=" + ordered +
                ", orderId=" + orderId +
                ", commonCost=" + commonCost +
                ", percent=" + percent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(type, product.type) &&
                Objects.equals(nameRu, product.nameRu) &&
                Objects.equals(nameEn, product.nameEn) &&
                Objects.equals(weight, product.weight) &&
                Objects.equals(cost, product.cost) &&
                Objects.equals(descriptionRu, product.descriptionRu) &&
                Objects.equals(descriptionEn, product.descriptionEn) &&
                Objects.equals(imagePath, product.imagePath) &&
                Objects.equals(number, product.number) &&
                Objects.equals(ordered, product.ordered) &&
                Objects.equals(orderId, product.orderId) &&
                Objects.equals(commonCost, product.commonCost) &&
                Objects.equals(percent, product.percent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, nameRu, nameEn, weight, cost, descriptionRu, descriptionEn, imagePath, number, ordered, orderId, commonCost, percent);
    }
}
