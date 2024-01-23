package com.bestbuy.Info;

import com.bestbuy.info.StoreSteps;
import com.bestbuy.testbase.StoreTestBase;
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
public class StoresCrudTest extends StoreTestBase {


    static int storeId;
    static String name = "Harrods";
    static String type = "Mall";
    static String address = "123 Coral Street";
    static String address2 = "Knightsbridge";
    static String city = "London";
    static String state = "NV";
    static String zip = "33212";
    static double lat = 51.57620;
    static double lng = 11.2234;
    static String hours = "Mon: 9-9; Tue: 9-9; Wed: 9-9; Thurs: 9-9; Fri: 9-9; Sat: 9-9; Sun: 11-4";
    @Steps
    StoreSteps steps;

    @Title("This will Create new Store")
    @Test
    public void T1() {
        ValidatableResponse response = steps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours);
        response.log().all().statusCode(201);
        storeId = response.extract().path("id");
        System.out.println("Product ID : " + storeId);
    }

    @Title("This will read a new Store")
    @Test
    public void T2() {

        HashMap<String, Object> storeMap = steps.readStore(storeId);
        Assert.assertThat(storeMap, hasValue(name));
        storeId = (int) storeMap.get("id");

    }


    @Title("This will update store")
    @Test
    public void T3() {
        name = "Westfeild" + TestUtils.getRandomValue();
        type = "SuperMall" + TestUtils.getRandomValue();
        steps.updateStore(storeId, name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(200);

        HashMap<String, Object> storeMap = steps.readStore(storeId);
        Assert.assertThat(storeMap, hasValue(name));
    }

    @Title("This will delete store")
    @Test
    public void T4() {

        steps.deleteStore(storeId).statusCode(200);

    }

}
