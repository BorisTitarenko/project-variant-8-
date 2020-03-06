import org.testng.annotations.Test;

import java.io.*;
import java.time.LocalDate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class TestAnimal {


    @Test(priority = 1, dataProvider = "animalInputDataProvider", dataProviderClass = AnimalsDataProvider.class)
    public void Animal_InputObjectFromFile_AnimalCreated(BufferedReader reader, Animal expectedAnimal) throws IOException {
        Animal actualInputAnimal = new Animal().input(reader);

        assertEquals(actualInputAnimal, expectedAnimal);
    }


    @Test(priority = 3, dataProvider = "animalConstructorDataProvider", dataProviderClass = AnimalsDataProvider.class)
    public void Animal_Created_GetCorrectAge(LocalDate localDate, String color, int expectedAge) throws IOException {
        Animal animal = new Animal(localDate, color);

        assertEquals(animal.getAge(), expectedAge);
    }


    @Test(priority = 4)
    public void Animal_Created_ToStringReturnMove() {
        Animal animal = new Animal();

        assertTrue(animal.toString().equals("I'm moving"));
    }


    @Test(priority = 2, dataProvider = "animalConstructorDataProvider", dataProviderClass = AnimalsDataProvider.class)
    public void Animal_Created_ShouldOutput(LocalDate localDate, String color, int age) throws IOException {
        Animal animal = new Animal(localDate, color);
        StringWriter stringWriter = new StringWriter();

        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);
        animal.output(bufferedWriter);
        boolean expected = stringWriter.toString().contains(animal.getColor()) &&
                stringWriter.toString().contains(animal.getBirthDate().toString());

        assertTrue(expected);
    }

}
