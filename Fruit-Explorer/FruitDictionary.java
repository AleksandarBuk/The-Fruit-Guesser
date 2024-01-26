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
        addFruitWithClues("Apple", "Red", "Juicy", "Healthy", "Tree");
        addFruitWithClues("Banana", "Yellow", "Sweet", "Peel", "Tropical");
        addFruitWithClues("Pear", "Green", "Sweet", "Tree", "Not Tropical");
        // Add more fruits with clues here...
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
