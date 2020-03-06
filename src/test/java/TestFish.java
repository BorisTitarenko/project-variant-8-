import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.*;
import java.time.LocalDate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestFish {


    @Test(priority = 1, dataProvider = "fishInputDataProvider", dataProviderClass = AnimalsDataProvider.class)
    public void Fish_Empty_ShouldInput(BufferedReader reader, Fish expectedFish) throws IOException {
            Fish actualInputFish = (Fish) new Fish().input(reader);

            assertEquals(actualInputFish, expectedFish);
    }
    

    @Test(priority = 2, dataProvider = "fishConstructorDataProvider", dataProviderClass = AnimalsDataProvider.class)
    public void Animal_Created_ShouldOutput(LocalDate localDate, String color, FishKind fishKind, String species)
            throws IOException {

        Fish fish = new Fish(localDate, color, fishKind, species);
        StringWriter writer = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(writer);


        fish.output(bufferedWriter);
        String outputString = writer.toString();
        boolean expected = outputString.contains(fish.getColor()) &&
                outputString.contains(fish.getBirthDate().toString()) &&
                outputString.contains(fish.getSpecies()) &&
                outputString.contains(fish.getKind().toString().toUpperCase());

        assertTrue(expected);
    }
}
