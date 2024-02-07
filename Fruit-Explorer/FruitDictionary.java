import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FruitDictionary {
    private Map<String, List<String>> fruitDictionary;
    private Map<String, Integer> fruitIndexMap;

    public FruitDictionary() {
        fruitDictionary = new HashMap<>();
        fruitIndexMap = new HashMap<>();
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
            "It can be used for a pie.",
            "Did you know that apples are not only delicious but also packed with essential nutrients?\n Apples are a great source of Vitamin C, which is crucial for a healthy immune system, and they contain soluble fiber that aids in\n digestion and can help regulate blood sugar levels.\n Additionally, apples are rich in antioxidants, substances that can help fight off disease-causing free radicals, making them a powerhouse of health benefits. Eating an apple a day could indeed help keep the doctor away by supporting overall health and preventing various diseases."
        );

        // Banana
        addFruitWithClues(
            "Banana",
            "What fruit is Yellow and Curved?",
            "It's Healthy and Energizing.",
            "It's Sweet and Tasty.",
            "It's a Tropical Fruit.",
            "Peel Before Eating.",
            "This fruit is Yellow and Curved.",
            "Banana Fun Fact\r\n",
            "Did you know that bananas are a fantastic source of energy and more than just a tasty snack?\n They are rich in Vitamin B6, which is vital for brain health and creating neurotransmitters such as serotonin and dopamine, which influence mood.\n Bananas also contain a good amount of potassium, essential for heart health and regulating blood pressure.\n Moreover, their easy-to-digest carbs make them a favorite among athletes for an instant energy boost.\n So, peeling a banana could be the key to uplifting your mood and keeping your body in great shape!"

        );

        // Pear
        addFruitWithClues(
            "Pear",
            "What fruit is Green and Pear-shaped?",
            "This fruit is Sweet and Juicy.",
            "Not Found in Tropics.",
            "It Grows on a Tree.",
            "It's very Crunchy and Refreshing.",
            "Did you know that pears are not only juicy and delicious but also loaded with health benefits?\n Pears are an excellent source of dietary fiber, which is essential for a healthy digestive system. \nThey also contain vitamins C and K, and minerals like copper, all of which contribute to skin health, bone health, and immune function. \nInterestingly, pears have a low glycemic index, making them a smart choice for people managing their blood sugar levels. \nSo, crunching on a pear could be a sweet way to boost your health!"
        );

        // Orange
        addFruitWithClues(
            "Orange",
            "What fruit is Orange and Citrusy?",
            "Round and Spherical.",
            "Peel to Reveal.",
            "Rich in Vitamin C.",
            "Fresh and Tangy.",
            "Did you know that oranges are famous for their vitamin C content, but their health benefits don't stop there?\n They are also a good source of fiber, B vitamins, vitamin A, calcium, and potassium. \nVitamin C is a powerful antioxidant that supports the immune system, helps heal wounds, and promotes healthy skin. \nOranges also contain flavonoids and phytochemicals that can help reduce inflammation and fight off viruses and bacteria.\n Squeezing an orange into your diet can brighten your day with a burst of flavor and a boost of health benefits!"
        );

        // Strawberry
        addFruitWithClues(
            "Strawberry",
            "What fruit is Red and Heart-shaped?",
            "Juicy and Flavorful.",
            "Small Red Seeds.",
            "Sweet and Fragrant.",
            "Perfect for Desserts.",
            "Did you know that strawberries are not just sweet and delicious but also a nutritional powerhouse?\nThey are packed with vitamins, fiber, and particularly high levels of antioxidants known as polyphenols. \nStrawberries are a great source of vitamin C, manganese, folate (vitamin B9), and potassium. \nThey are among the top 20 fruits in antioxidant capacity and are known to promote heart health, help control blood sugar, and even have anti-cancer properties. Including strawberries in your diet can be a delightful way to enhance your health and satisfy your sweet tooth!"
        );

        // Grape
        addFruitWithClues(
            "Grape",
            "What fruit is Purple or Green?",
            "Small and Round.",
            "Grows in Clusters.",
            "Sweet and Juicy.",
            "Used for Wine Too.",
            "Did you know that grapes come in various colors, including purple, green, and red, each with its unique taste and nutritional profile?\nGrapes are a fantastic source of vitamins C and K, and they're rich in antioxidants, including resveratrol, which is found in the skins of \nred grapes and has been linked to heart health and the prevention of certain types of cancer. \nAdditionally, grapes are involved in making wine, showcasing their versatility and cultural significance. \nTheir sweet flavor and juicy texture make them a favorite snack, while their health benefits make them a nutritious choice for any diet."
        );

        // Pineapple
        addFruitWithClues(
            "Pineapple",
            "What fruit is Yellow and Spiky?",
            "Tropical and Exotic.",
            "Sweet and Tart.",
            "Core is Inedible.",
            "Great for Cocktails.",
            "Did you know that pineapples are not just a single fruit but a composite of many flowers whose individual fruitlets fuse together\n around a central core?\n This tropical fruit is not only known for its vibrant taste but also for its bromelain content, an enzyme that can aid in\n digestion and reduce inflammation.\n Pineapples are also packed with vitamins C and manganese, essential for antioxidant defenses and bone health.\n Including pineapple in your diet can be a delicious way to boost your immune system and enjoy a taste of the tropics."
        );

        // Watermelon
        addFruitWithClues(
            "Watermelon",
            "What fruit is Green and Juicy?",
            "Large and Heavy.",
            "Sweet and Refreshing.",
            "Full of Seeds.",
            "Perfect for Summer.",
            "Did you know that watermelons are over 90% water, making them the perfect hydrating snack for hot summer days? \nDespite being mostly water, watermelons are a good source of vitamins A, C, and B6, as well as potassium, which are essential for skin health, immune function, and overall well-being.\n They also contain lycopene, an antioxidant that gives them their red color and is linked to heart health and cancer prevention.\n Watermelon is not just a refreshing snack but also a nutrient-packed fruit that can help you stay healthy and hydrated."
        );

        // Kiwi
        addFruitWithClues(
            "Kiwi", 
            "What fruit is Green and Fuzzy?",
            "Small and Oval.",
            "Eat the Skin Too.",
            "Sweet and Tangy.",
            "Loaded with Vitamin C.",
            "Did you know that kiwi is a small fruit that packs a big nutritional punch? \nIt's an excellent source of vitamin C, even outshining oranges in some measures, and it's also rich in dietary fiber, which aids in digestion. \nKiwis contain a unique enzyme called actinidin, which can help break down proteins and aid in digestion. \nFurthermore, kiwis are a good source of vitamin K and vitamin E, both of which are important for blood clotting and protecting your body from\n oxidative damage. \nEating kiwi can boost your immune system, promote healthy skin, and even help you digest your meals more efficiently."
        );

        // Blueberry
        addFruitWithClues(
            "Blueberry",
            "Which fruit is High in vitiamin C?",
            "What fruit is Blue and Tiny?",
            "Small and Round.",
            "High in Antioxidants.",
            "Sweet and Tart.",
            "Great for Muffins.",
            "Did you know that blueberries are often called a superfood due to their high antioxidant levels, including anthocyanins, which give them their blue color and support heart health? \nThese tiny berries are also rich in vitamins C and K, fiber, and manganese. \nResearch suggests that blueberries can improve memory and brain function, help lower blood pressure, and reduce muscle damage after\n exercise. \nIncluding blueberries in your diet is a tasty way to boost your health and protect your body against disease."

        );
        
        addFruitWithClues(
	        "Walnut",
	        "What fruit grows in Autumn?",
	        "It looks like a brain.",
	        "It's rich in vitamin B6.",
	        "There is a saying ''Tough like a ____''",
	        "This vitamin can cause alergy.",
            "Did you know that kiwi is a small fruit that packs a big nutritional punch? It's an excellent source of vitamin C, even outshining oranges in some measures, and it's also rich in dietary fiber, which aids in digestion. \nKiwis contain a unique enzyme called actinidin, which can help break down proteins and aid in digestion. \nFurthermore, kiwis are a good source of vitamin K and vitamin E, both of which are important for blood clotting and protecting your body from oxidative damage. Eating kiwi can boost your immune system, promote healthy skin, and even help you digest your meals more efficiently."
        );
        
    }

    public List<String> getCluesForFruit(String fruitName) {
        return fruitDictionary.getOrDefault(fruitName, new ArrayList<>());
    }

    public List<String> getFruitNames() {
        return new ArrayList<>(fruitDictionary.keySet());
    }

    // Method to add a fruit with its clues to the dictionary
    private void addFruitWithClues(String fruit, String... clues) {
        List<String> clueList = new ArrayList<>();
        for (String clue : clues) {
            clueList.add(clue);
        }
        fruitDictionary.put(fruit, clueList);
        fruitIndexMap.put(fruit, fruitIndexMap.size());
    }

    public int getIndexForFruit(String fruit) {
        return fruitIndexMap.getOrDefault(fruit, -1);
    }
    
    public void reset() {
        fruitDictionary.clear();
        fruitIndexMap.clear();
        initializeDictionary();
    }
    
}
