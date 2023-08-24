package org.davivienda.adapters.test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.davivienda.domain.model.Fruit;
import org.davivienda.domain.services.FruitSyncService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FruitResourcesTest {


    @InjectMock
    FruitSyncService fruitSyncService;


    @Test
    public void testgatAll() {


        List<Fruit> list = new ArrayList<>();
        Fruit fruit = new Fruit();
        fruit.setName("manzana");
        fruit.setDescription("es una fruta de mucho alimento");
        list.add(fruit);

        Mockito.when(  fruitSyncService.findAll()  ).thenReturn(list);


        given()
                .when().get("/fruits")
                .then()
                .statusCode(200)
                .body( "isEmpty()", is(false) );

    }

    @Test
    public void testGetSingle() {

        String name = "manzana";
        Fruit fruit = new Fruit();
        fruit.setName("manzana");
        fruit.setDescription("es una fruta de mucho alimento");
        Mockito.when(  fruitSyncService.get( name   )  ).thenReturn(fruit);


        given()
                .when().get("/fruits/manzana")
                .then()
                .statusCode(200)
                .body( "name", is(name) );
    }

    @Test
    public void testAdd() {

        List<Fruit> list = new ArrayList<>();
        Fruit fruit = new Fruit();
        fruit.setName("manzana");
        fruit.setDescription("es una fruta de mucho alimento");
        list.add(fruit);

        Mockito.doNothing().when(fruitSyncService).add(fruit);  ;
        Mockito.when(  fruitSyncService.findAll()  ).thenReturn(list);

        given()
                .body("{\"name\":\"manzana\",\t\"description\":\"es una fruta de mucho alimento\"}")
                .header("Content-Type", "application/json")
                .when()
                .post("/fruits")
                .then()
                .statusCode(200);

    }

}
