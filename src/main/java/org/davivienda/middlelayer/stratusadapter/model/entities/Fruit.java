package org.davivienda.middlelayer.stratusadapter.model.entities;

import lombok.Data;
import org.davivienda.middlelayer.stratusadapter.services.FruitAbstractService;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.Objects;

@Data
public class Fruit {
    private String name;
    private String description;
    public Fruit() {
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Fruit)) {
            return false;
        }
        Fruit other = (Fruit) obj;
        return Objects.equals(other.name, this.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    public static Fruit from(Map<String, AttributeValue> item) {
        Fruit fruit = new Fruit();
        if (item != null && !item.isEmpty()) {
            fruit.setName(item.get(FruitAbstractService.FRUIT_NAME_COL).s());
            fruit.setDescription(item.get(FruitAbstractService.FRUIT_DESC_COL).s());
        }
        return fruit;
    }
}
