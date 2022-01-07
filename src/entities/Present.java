package entities;


public final class Present {

    private final String productName;
    private final Double price;
    private final String category;

    public Present(final Double price, final String productName, final String category) {
        this.price = price;
        this.productName = productName;
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }
}
