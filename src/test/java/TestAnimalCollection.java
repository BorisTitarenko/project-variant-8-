import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import static org.testng.Assert.*;

public class TestAnimalCollection {

    private boolean isFilesEqual(String fileOne, String fileTwo) throws FileNotFoundException, IOException{

        BufferedReader readerOne = new BufferedReader(new FileReader(fileOne));
        BufferedReader readerTwo = new BufferedReader(new FileReader(fileTwo));

        String contentOne = readerOne.lines().collect(Collectors.joining());
        String contentTwo = readerTwo.lines().collect(Collectors.joining());
        readerOne.close();
        readerTwo.close();

        return contentOne.equals(contentTwo);

    }


    @Test(priority = 1, dataProvider = "serializationDataProvider", dataProviderClass = AnimalsDataProvider.class)
    public void AnimalCollection_Fooled_ShouldSerialize(ArrayList<Animal> animals, String ACTUAL_SERIALIZED_ANIMALS, String EXPECTED_SERIALIZED_ANIMALS)
            throws FileNotFoundException, IOException
    {
        AnimalCollection<Animal> animalCollection = new AnimalCollection<>(animals);

        AnimalCollection.serializeAnimals(ACTUAL_SERIALIZED_ANIMALS, animalCollection);
        boolean isEqual = isFilesEqual(ACTUAL_SERIALIZED_ANIMALS, EXPECTED_SERIALIZED_ANIMALS);

        assertTrue(isEqual);

    }

    @Test(priority = 1, dataProvider = "deserializationDataProvider", dataProviderClass = AnimalsDataProvider.class)
    public void AnimalCollection_Fooled_ShouldDeserialize(ArrayList<Animal> expectedAnimals, String ANIMALS_TO_DESERIALIZE)
            throws FileNotFoundException, IOException
    {

        AnimalCollection<Animal> actualCollection = AnimalCollection.deserializeAnimals(ANIMALS_TO_DESERIALIZE);

        assertEquals(actualCollection.getAnimals(), expectedAnimals);

    }


    @Test(priority = 2, dataProvider = "expectedOutputDataProvider", dataProviderClass = AnimalsDataProvider.class)
    public void AnimalCollection_Fooled_ShouldWriteToFile(ArrayList<Animal> animals, String expectedOuput)
            throws FileNotFoundException, IOException {

        AnimalCollection<Animal> animalCollection = new AnimalCollection<>(animals);
        StringWriter stringWriter = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);

        animalCollection.writeAnimalsToFile(bufferedWriter);

        assertEquals(expectedOuput, stringWriter.toString());
    }


    @Test(priority = 3, dataProvider = "sortingDataProvider", dataProviderClass = AnimalsDataProvider.class)
    public void AnimalCollection_Fooled_ShouldSortFish(ArrayList<Animal> animals, ArrayList<Fish> expectedAnimals){
        AnimalCollection<Animal> animalCollection = new AnimalCollection<>(animals);

        ArrayList<Fish> actualAnimals = animalCollection.sortBySpecies();

        assertEquals(expectedAnimals, actualAnimals);
    }


    @Test(priority = 4, dataProvider = "oldFishDataProvider", dataProviderClass = AnimalsDataProvider.class)
    public  void AnimalCollection_Fooled_ShouldReturnOldFish(ArrayList<Animal> animals, ArrayList<Fish> oldFish){
        AnimalCollection<Animal> animalCollection = new AnimalCollection<>(animals);

        ArrayList<Fish> actualOldFish = animalCollection.getFishOlderThenThree();

        assertEqualsNoOrder(actualOldFish.toArray(), oldFish.toArray());
    }



}


