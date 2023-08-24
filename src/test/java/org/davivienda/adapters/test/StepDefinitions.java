package org.davivienda.adapters.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.inject.Inject;
import org.davivienda.domain.model.Fruit;
import org.davivienda.domain.services.FruitSyncService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    @Inject
    FruitSyncService fruitSyncService;
    Fruit fruit;


    @Given("una fruta")
    public void una_fruta() {
        Fruit fruit = new Fruit();
        fruit.setName("manzana");
        fruit.setDescription("es una fruta de mucho alimento");
    }

    @When("yo actualizo una fruta")
    public void yo_actualizo_una_fruta() {
        Fruit fruitMandarina = new Fruit();
        fruitMandarina.setName("mandarina");
        fruitMandarina.setDescription("es una fruta muy bonita");
        this.fruit = fruitSyncService.update("manzana", fruitMandarina);
    }

    @Then("yo obtengo la fruta actualizada")
    public void yo_obtengo_la_fruta_actualizada() {
        Fruit fruitMandarina = new Fruit();
        fruitMandarina.setName("mandarina");
        fruitMandarina.setDescription("es una fruta muy bonita");
        assertEquals(fruitMandarina.getName(), fruit.getName());
    }











}
