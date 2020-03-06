import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        AnimalCollection<Fish> animalCollection = new AnimalCollection<>();

        String animalsFile = "E:\\course_java\\studying\\4\\v8\\src\\test\\data\\fish.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(animalsFile)));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


        try {
            animalCollection.readFishFromFile(reader);
            animalCollection.writeAnimalsToFile(writer);
        }catch (IOException ex){
            ex.printStackTrace();
        }



        Animal animal = new Animal(LocalDate.of(1999, 2, 3), "green");
        System.out.println(animal.getAge());
        System.out.println(animalCollection.getFishOlderThenThree());


    }
}
