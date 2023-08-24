package org.davivienda.domain.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.davivienda.domain.model.Fruit;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FruitSyncService extends AbstractService {

    @Inject
    DynamoDbClient dynamoDB;

    public List<Fruit> findAll() {
        return dynamoDB.scanPaginator(scanRequest()).items().stream()
                .map(Fruit::from)
                .collect(Collectors.toList());
    }

    public void add(Fruit fruit) {
        dynamoDB.putItem(putRequest(fruit));
    }

    public Fruit get(String name) {
        return Fruit.from(dynamoDB.getItem(getRequest(name)).item());
    }

    public Fruit update(String name,Fruit fruit) {
        Fruit persistentFruit =  Fruit.from(dynamoDB.getItem(getRequest(name)).item());

        String newName = fruit.getName();
        persistentFruit.setName(newName);
        persistentFruit.setDescription( fruit.getDescription());

        add(persistentFruit);

        return  persistentFruit;
    }
}
