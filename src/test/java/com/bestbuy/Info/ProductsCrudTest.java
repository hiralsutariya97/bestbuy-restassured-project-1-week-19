package com.bestbuy.Info;

import com.bestbuy.info.ProductSteps;
import com.bestbuy.testbase.ProductTestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCrudTest extends ProductTestBase {

    static String name = "Macbook" + TestUtils.getRandomValue();
    static String type = "laptop";
    static double price = 1999.99;
    static int shipping = 0;
    static String upc = TestUtils.getRandomValue();
    static String description = "Latest Model";
    static String manufacturer = "Apple";
    static String model = "ABC123";
    static String url = "https://google.com/" + TestUtils.getRandomValue() + ".jpg";
    static String image = TestUtils.getRandomValue();
    static int productId;

    @Steps
    ProductSteps steps;

    @Title("This will create new Product")
    @Test
    public void T1() {
        ValidatableResponse response = steps.createProduct(name, type, price, upc,
                shipping, description, manufacturer, model, image, url);
        response.log().all().statusCode(201);
        productId = response.extract().path("id");
        System.out.println("Product ID : " + productId);

    }

    @Title("This will read a new Product")
    @Test
    public void T2() {

        HashMap<String, Object> productMap = steps.readProduct(productId);
        Assert.assertThat(productMap, hasValue(name));
        productId = (int) productMap.get("id");

    }

    @Title("This will update product")
    @Test
    public void T3() {
        name = "Iphone" + TestUtils.getRandomValue();
        type = "Mobile";
        steps.updateProduct(productId, name, type, price, upc, shipping, description,
                manufacturer, model, image, url).statusCode(200);

        HashMap<String, Object> studentMap = steps.readProduct(productId);

        Assert.assertThat(studentMap, hasValue(name));
    }

    @Title("This will delete product")
    @Test
    public void T4() {

        steps.deleteProduct(productId).statusCode(200);


    }

}
