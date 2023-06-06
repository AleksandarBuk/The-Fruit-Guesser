package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FruitGenerator {
    private List<String> fruits;
    private Random random;

    public FruitGenerator() {
        this.fruits = initializeFruits();
        this.random = new Random();
    }

    public String generateRandomFruit() {
        int index = random.nextInt(fruits.size());
        return fruits.get(index);
    }

    private List<String> initializeFruits() {
        List<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("orange");
        fruits.add("kiwi");
        fruits.add("orange");
        fruits.add("watermellon");
        fruits.add("mango");

        return fruits;
    }
}
