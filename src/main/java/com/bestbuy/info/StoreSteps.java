package com.bestbuy.info;
import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class StoreSteps {

    @Step("Create new Store with name: {0}, type: {1}, address: {2}, address2: {3}, city: {4}, " +
            "state: {5}, zip: {6}, lat: {7}, lng: {8}, hours: {9}")

    public ValidatableResponse createStore(String name, String type, String address, String address2, String city,
                                           String state, String zip, Double lat, Double lng, String hours) {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post(EndPoints.CREATE_STORES)
                .then().log().all();
    }
    @Step("Read Store with Id: {0}")
    public HashMap<String, Object> readStore(int storeId) {
        return SerenityRest.given().log().all()
                .when()
                .pathParam("storeID", storeId)
                .get(Path.STORES + EndPoints.GET_SINGLE_STORE_BY_ID)
                .then().statusCode(200)
                .extract().path("");
    }
    @Step("Update store with Id: {0}, name: {1}, type: {2}, address: {3}, address2: {4}, city: {5}, state: {6}, zip: {7}, lat: {8}, lng: {9}, hours: {10}")
    public ValidatableResponse updateStore(int storeId, String name, String type, String address, String address2, String city,
                                           String state, String zip, Double lat, Double lng, String hours) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("storeID", storeId)
                .body(storePojo)
                .when()
                .patch(Path.STORES + EndPoints.UPDATE_STORE_BY_ID)
                .then().log().all();
    }
    @Step("Delete Store with Id: {0}")

    public ValidatableResponse deleteStore(int storeId) {
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeId)
                .when()
                .delete(Path.STORES + EndPoints.DELETE_STORE_BY_ID)
                .then();
    }


}
