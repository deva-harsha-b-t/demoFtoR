package com.dtl.dojoproduct;

public class productModel {
    private int id;
    private int rating;
    private int noOfReviews;
    private String cat;
    private String ProductName;
    private String ProductSlag;
    private String ProductDescription;
    private String ProductImageURL;


    public productModel(int id, int rating, int noOfReviews, String cat, String productName, String productSlag, String productDescription, String productImageURL) {
        this.id = id;
        this.rating = rating;
        this.noOfReviews = noOfReviews;
        this.cat = cat;
        this.ProductName = productName;
        this.ProductSlag = productSlag;
        this.ProductDescription = productDescription;
        this.ProductImageURL = productImageURL;
    }

    public productModel() {
    }

    public String getProductImageURL() {
        return ProductImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        ProductImageURL = productImageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNoOfReviews() {
        return noOfReviews;
    }

    public void setNoOfReviews(int noOfReviews) {
        this.noOfReviews = noOfReviews;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductSlag() {
        return ProductSlag;
    }

    public void setProductSlag(String productSlag) {
        ProductSlag = productSlag;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }
}
