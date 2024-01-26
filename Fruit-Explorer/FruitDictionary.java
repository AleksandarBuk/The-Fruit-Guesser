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
        addFruitWithClues("Apple", "Red and Round", "Juicy and Sweet", "Healthy and Nutritious", "Tree with Leaves", "Delicious to Eat");
        addFruitWithClues("Banana", "Yellow and Curved", "Sweet and Tasty", "Peel Before Eating", "Tropical Fruit", "Healthy and Energizing");
        addFruitWithClues("Pear", "Green and Pear-shaped", "Sweet and Juicy", "Grows on a Tree", "Not Found in Tropics", "Crunchy and Refreshing");
        addFruitWithClues("Orange", "Orange and Citrusy", "Round and Spherical", "Peel to Reveal", "Rich in Vitamin C", "Fresh and Tangy");
        addFruitWithClues("Strawberry", "Red and Heart-shaped", "Juicy and Flavorful", "Small Red Seeds", "Sweet and Fragrant", "Perfect for Desserts");
        addFruitWithClues("Grape", "Purple or Green", "Small and Round", "Grows in Clusters", "Sweet and Juicy", "Used for Wine Too");
        addFruitWithClues("Pineapple", "Yellow and Spiky", "Tropical and Exotic", "Sweet and Tart", "Core is Inedible", "Great for Cocktails");
        addFruitWithClues("Watermelon", "Green and Juicy", "Large and Heavy", "Sweet and Refreshing", "Full of Seeds", "Perfect for Summer");
        addFruitWithClues("Kiwi", "Green and Fuzzy", "Small and Oval", "Eat the Skin Too", "Sweet and Tangy", "Loaded with Vitamin C");
        addFruitWithClues("Blueberry", "Blue and Tiny", "Small and Round", "High in Antioxidants", "Sweet and Tart", "Great for Muffins");
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
