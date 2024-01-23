package com.bestbuy.info;

import com.bestbuy.constants.Path;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class ProductSteps {

    @Step("Create new product with name : {0}, type : {1}, price: {2}, upc:{3}, shipping: {4}, description: {5}," + "manufacture: {6}, model: {7}, image: {8}, url: {9}")
    public ValidatableResponse createProduct(String name, String type, double price, String upc, int shipping,
                                             String description, String manufacturer, String model, String image, String url) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setImage(image);
        productPojo.setUrl(url);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(productpojo)
                .post(EndPoints.CREATE_PRODUCT)
                .then().log().all();
    }

    @Step("Read the Product with Id: {0}")
    public HashMap<String, Object> readProduct(int productId) {
        return SerenityRest.given().log().all()
                .when()
                .pathParam("productID", productId)
                .get(Path.PRODUCTS + EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().statusCode(200)
                .extract().path("");
    }

    @Step("Update the product with Id: {0}, name: {1}, type: {2}, price: {3}, upc: {4}, shipping: {5}, description: {6}," +
            "manufacturer: {7}, model: {8}, image: {9}, url: {10}")
    public ValidatableResponse updateProduct(int productId, String name, String type, double price, String upc, int shipping,
                                             String description, String manufacturer, String model, String image, String url) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("productID", productId)
                .body(productPojo)
                .when()
                .patch(Path.PRODUCTS + EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Delete the product with Id: {0}")

    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest.given().log().all()
                .pathParam("productID", productId)
                .when()
                .delete(Path.PRODUCTS + EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }


}
