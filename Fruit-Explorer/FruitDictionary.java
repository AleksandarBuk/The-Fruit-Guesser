import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FruitDictionary {
    private Map<String, List<String>> fruitDictionary;

    public FruitDictionary() {
        fruitDictionary = new HashMap<>();
        initializeDictionary();
    }

    private void initializeDictionary() {
        // Apple
        addFruitWithClues(
            "Apple",
            "What fruit is Red and Round?",
            "It's liked because it's Juicy and Sweet.",
            "It's known how Healthy and Nutritious it is.",
            "It grows on a Tree with Leaves.",
            "It can be used for a pie."
        );

        // Banana
        addFruitWithClues(
            "Banana",
            "What fruit is Yellow and Curved?",
            "It's Healthy and Energizing.",
            "It's Sweet and Tasty.",
            "It's a Tropical Fruit.",
            "Peel Before Eating.",
            "This fruit is Yellow and Curved."
        );

        // Pear
        addFruitWithClues(
            "Pear",
            "What fruit is Green and Pear-shaped?",
            "This fruit is Sweet and Juicy.",
            "Not Found in Tropics.",
            "It Grows on a Tree.",
            "It's very Crunchy and Refreshing."
        );

        // Orange
        addFruitWithClues(
            "Orange",
            "What fruit is Orange and Citrusy?",
            "Round and Spherical.",
            "Peel to Reveal.",
            "Rich in Vitamin C.",
            "Fresh and Tangy."
        );

        // Strawberry
        addFruitWithClues(
            "Strawberry",
            "What fruit is Red and Heart-shaped?",
            "Juicy and Flavorful.",
            "Small Red Seeds.",
            "Sweet and Fragrant.",
            "Perfect for Desserts."
        );

        // Grape
        addFruitWithClues(
            "Grape",
            "What fruit is Purple or Green?",
            "Small and Round.",
            "Grows in Clusters.",
            "Sweet and Juicy.",
            "Used for Wine Too."
        );

        // Pineapple
        addFruitWithClues(
            "Pineapple",
            "What fruit is Yellow and Spiky?",
            "Tropical and Exotic.",
            "Sweet and Tart.",
            "Core is Inedible.",
            "Great for Cocktails."
        );

        // Watermelon
        addFruitWithClues(
            "Watermelon",
            "What fruit is Green and Juicy?",
            "Large and Heavy.",
            "Sweet and Refreshing.",
            "Full of Seeds.",
            "Perfect for Summer."
        );

        // Kiwi
        addFruitWithClues(
            "Kiwi",
            "What fruit is Green and Fuzzy?",
            "Small and Oval.",
            "Eat the Skin Too.",
            "Sweet and Tangy.",
            "Loaded with Vitamin C."
        );

        // Blueberry
        addFruitWithClues(
            "Blueberry",
            "Which fruit is High in vitiamin C?",
            "What fruit is Blue and Tiny?",
            "Small and Round.",
            "High in Antioxidants.",
            "Sweet and Tart.",
            "Great for Muffins."
        );
        
        addFruitWithClues(
        "Walnut",
        "What fruit grows in Autumn?",
        "It looks like a brain.",
        "It's rich in vitamin B6.",
        "There is a saying ''Tough like a ____''",
        "This vitamin can cause alergy."
        
        );
        
    }




    public List<String> getCluesForFruit(String fruitName) {
        return fruitDictionary.getOrDefault(fruitName, new ArrayList<>());
    }
    
    public List<String> getFruitNames() {
        return new ArrayList<>(fruitDictionary.keySet());
    }
    
    private void addFruitWithClues(String fruit, String... clues) {
        List<String> clueList = new ArrayList<>();
        for (String clue : clues) {
            clueList.add(clue);
        }
        fruitDictionary.put(fruit, clueList);
    }
}
