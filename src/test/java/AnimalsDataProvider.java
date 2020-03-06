import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AnimalsDataProvider {
    private final String ACTUAL_SERIALIZED_ANIMALS = "./src/test/data/actualSerializedAnimals.xml";
    private final String EXPECTED_SERIALIZED_ANIMALS = "./src/test/data/expectedSerializedAnimals.xml";
    private final String ANIMALS_TO_DESERIALIZE = "./src/test/data/animalsToDeserialize.xml";


    @DataProvider(name = "animalConstructorDataProvider")
    public Object[][] getAnimalConstructorData(){
        Object[][] objects = {{LocalDate.of(2015, 5, 5), "red", 4},
                {LocalDate.of(2019, 2, 24), "deep blue", 1}};
        return objects;
    }

    @DataProvider(name = "fishConstructorDataProvider")
    public Object[][] getFishConstructorData(){
        Object[][] objects = {{LocalDate.of(2017, 5, 5), "red", FishKind.RIVER, "Silurus glanis"},
                {LocalDate.of(1019, 2, 24), "deep blue", FishKind.SEA, "Shark" }};
        return objects;
    }

    @DataProvider(name = "fishInputDataProvider")
    public Object[][] getFishInput() throws IOException {
        String linesToRead = "blue\n" +
                "1947 08 08\n" +
                "Silurus glanis\n" +
                "RIVER";

        BufferedReader reader = new BufferedReader(new StringReader(linesToRead));

        Fish fish = new Fish(LocalDate.of(1947, 8, 8),
                "blue",
                FishKind.RIVER,
                "Silurus glanis");

        Object[][] objects = {{reader, fish}};
        return objects;
    }


    @DataProvider(name = "animalInputDataProvider")
    public Object[][] getAnimalInput() throws IOException {
        String linesToRead = "dark brown\n" +
                "2014 11 27";

        BufferedReader reader = new BufferedReader(new StringReader(linesToRead));

        Animal animal = new Animal(LocalDate.of(2014, 11, 27), "dark brown");

        Object[][] objects = {{reader, animal}};
        return objects;
    }


    @DataProvider(name = "serializationDataProvider")
    public Object[][] getSerializationData(){
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal(LocalDate.of(2015, 5, 5), "orange"));
        animals.add(new Animal(LocalDate.of(2018, 7, 2), "blue"));
        animals.add(new Fish(LocalDate.of(2008, 6, 16), "Grey", FishKind.SEA, "Shark"));
        animals.add(new Fish(LocalDate.of(1969, 3, 2), "Black", FishKind.SEA, "Som"));
        animals.add(new Fish(LocalDate.of(2018, 7, 2), "orange", FishKind.SEA, "Nemo"));
        Object[][] objects = {{animals, ACTUAL_SERIALIZED_ANIMALS, EXPECTED_SERIALIZED_ANIMALS}};
        return objects;
    }


    @DataProvider(name = "deserializationDataProvider")
    public Object[][] getWritenData(){
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal(LocalDate.of(2015, 5, 5), "orange"));
        animals.add(new Animal(LocalDate.of(2018, 7, 2), "blue"));
        animals.add(new Fish(LocalDate.of(2008, 6, 16), "Grey", FishKind.SEA, "Shark"));
        animals.add(new Fish(LocalDate.of(1969, 3, 2), "Black", FishKind.SEA, "Som"));
        animals.add(new Fish(LocalDate.of(2018, 7, 2), "orange", FishKind.SEA, "Nemo"));
        Object[][] objects = {{animals, ANIMALS_TO_DESERIALIZE}};
        return objects;
    }

    @DataProvider(name = "expectedOutputDataProvider")
    public Object[][] getDataOutput(){
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal(LocalDate.of(2015, 5, 5), "orange"));
        animals.add(new Animal(LocalDate.of(2018, 7, 2), "blue"));
        animals.add(new Fish(LocalDate.of(2008, 6, 16), "Grey", FishKind.SEA, "Shark"));
        animals.add(new Fish(LocalDate.of(1969, 3, 2), "Black", FishKind.SEA, "Som"));
        animals.add(new Fish(LocalDate.of(2018, 7, 2), "orange", FishKind.SEA, "Nemo"));

        String expectedString = new AnimalCollection<>(animals).toString();

        Object[][] objects = {{animals, expectedString}};
        return objects;
    }

    @DataProvider(name = "sortingDataProvider")
    public Object[][] getSortingData(){
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal(LocalDate.of(2015, 5, 5), "orange"));
        animals.add(new Fish(LocalDate.of(2007, 6, 16), "Grey", FishKind.SEA, "Shark"));
        animals.add(new Animal(LocalDate.of(2018, 7, 2), "blue"));
        animals.add(new Fish(LocalDate.of(2008, 6, 16), "Grey", FishKind.SEA, "Shark"));
        animals.add(new Fish(LocalDate.of(1969, 3, 2), "Black", FishKind.SEA, "Som"));
        animals.add(new Fish(LocalDate.of(2018, 7, 2), "orange", FishKind.SEA, "Nemo"));

        ArrayList<Fish> sortedAnimals = new ArrayList<>();
        sortedAnimals.add(new Fish(LocalDate.of(2018, 7, 2), "orange", FishKind.SEA, "Nemo"));
        sortedAnimals.add(new Fish(LocalDate.of(2007, 6, 16), "Grey", FishKind.SEA, "Shark"));
        sortedAnimals.add(new Fish(LocalDate.of(2008, 6, 16), "Grey", FishKind.SEA, "Shark"));
        sortedAnimals.add(new Fish(LocalDate.of(1969, 3, 2), "Black", FishKind.SEA, "Som"));

        Object[][] objects = {{animals, sortedAnimals}};
        return objects;

    }

    @DataProvider(name = "oldFishDataProvider")
    public Object[][] getOldFish(){
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal(LocalDate.of(2015, 1, 5), "orange"));
        animals.add(new Fish(LocalDate.of(2007, 6, 16), "Grey", FishKind.SEA, "Shark"));
        animals.add(new Animal(LocalDate.of(2010, 7, 2), "blue"));
        animals.add(new Fish(LocalDate.of(2017, 6, 16), "Grey", FishKind.SEA, "Shark"));
        animals.add(new Fish(LocalDate.of(2018, 3, 2), "Black", FishKind.SEA, "Som"));
        animals.add(new Fish(LocalDate.of(2004, 2, 2), "orange", FishKind.SEA, "Nemo"));

        ArrayList<Fish> oldFish = new ArrayList<>();
        oldFish.add(new Fish(LocalDate.of(2007, 6, 16), "Grey", FishKind.SEA, "Shark"));
        oldFish.add(new Fish(LocalDate.of(2004, 2, 2), "orange", FishKind.SEA, "Nemo"));

        Object[][] objects = {{animals, oldFish}};
        return objects;
    }



}
