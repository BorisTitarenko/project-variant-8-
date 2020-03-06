import java.beans.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalCollection<T extends Animal>{
    private ArrayList<T> animals;
    public AnimalCollection(ArrayList<T> animals) {
        this.animals = animals;
    }

    public AnimalCollection(){
        animals = new ArrayList<>();
    }

    public ArrayList<T> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<T> animals) {
        this.animals = animals;
    }

    public void addAnimal(T animal){
        if(animal != null){
            this.animals.add(animal);
        }
    }

    public void addAll(AnimalCollection<T> animalCollection){
        if(animalCollection != null){
            this.animals.addAll(animalCollection.getAnimals());
        }
    }

    private ArrayList<Fish> segregateFish(){
        List<Fish> listFish = animals.stream().filter(animal -> animal instanceof Fish)
                .map(animal -> (Fish)animal)
                .collect(Collectors.toList());
        ArrayList<Fish> fish = new ArrayList<>(listFish);
        return fish;
    }

    public ArrayList<Fish> sortBySpecies(){
        ArrayList<Fish> sortedFish = segregateFish();
        sortedFish.sort(Fish.speciesComparator);
        return sortedFish;
    }

    public ArrayList<Fish> getFishOlderThenThree(){
        List<Fish> oldFish = segregateFish().stream()
                .filter(fish -> fish.getAge() > 3).collect(Collectors.toList());

        return (ArrayList<Fish>) oldFish;
    }



    public static <T extends Animal> void serializeAnimals(String SERIALIZED_FILE, AnimalCollection<T> animals)
        throws FileNotFoundException
    {
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE)));
        encoder.setPersistenceDelegate(LocalDate.class, new PersistenceDelegate() {
                @Override
                protected Expression instantiate(Object localDate, Encoder out) {
                    return new Expression(localDate, LocalDate.class,
                            "parse", new Object[]{localDate.toString()});
                }
            });

        encoder.writeObject(animals);
        encoder.close();
    }


    public static <T extends Animal> AnimalCollection<T> deserializeAnimals(String SERIALIZED_FILE )
            throws FileNotFoundException
    {
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE)));

            AnimalCollection<T> animals = (AnimalCollection<T>) decoder.readObject();
            decoder.close();
            return animals;
    }


    public void writeAnimalsToFile(BufferedWriter writer) throws IOException{

        writer.write(this.toString());
        writer.flush();
    }

    public void readAnimalsFromFile(BufferedReader reader) throws IOException{
        while (true) {
            Animal animal = new Animal();
            if (animal.input(reader) == null){
                break;}
            this.addAnimal((T)animal);
        }
    }

    public void readFishFromFile(BufferedReader reader) throws IOException{
        while (true) {
            Fish fish = new Fish();
            if (fish.input(reader) == null){
                break;}
            this.addAnimal((T)fish);
        }
    }

    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);
        for(T animal : animals){
            try {
                animal.output(bufferedWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringWriter.toString();
    }
}
