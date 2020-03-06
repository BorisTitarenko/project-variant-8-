import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class Fish extends Animal {
    private FishKind kind;
    private String species;

    public Fish(){

    }
    public Fish(LocalDate birthDate, String color, FishKind kind, String species) {
        super(birthDate, color);
        this.kind = kind;
        this.species = species;
    }

    public FishKind getKind() {
        return kind;
    }

    public void setKind(FishKind kind) {
        this.kind = kind;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String move() {
        return "I'm swimming!";
    }


    public String fishRepresentation() {
        return  String.format("{kind: %s, species: %s}", this.kind, this.species);
    }

    @Override
    public void output(BufferedWriter writer) throws IOException{
        String outString = String.format("{%s %s} ", animalRepresentation(), fishRepresentation());
        writer.write(outString);
        writer.flush();
    }


    @Override
    public Animal input(BufferedReader reader) throws IOException {

        super.input(reader);

        String fishSpecies;
        String fishKind;

        System.out.println("Enter species: ");
        if((fishSpecies = reader.readLine()) == null){
            return null;
        };

        System.out.println("Enter kind(SEA or RIVER): ");
        if((fishKind = reader.readLine()) == null){
            return null;
        };

        FishKind kind = FishKind.valueOf(fishKind.toUpperCase());

        this.species = fishSpecies;
        this.kind = kind;

        return this;
    }

    public static Comparator<Fish> speciesComparator = new Comparator<Fish>() {
        @Override
        public int compare(Fish o1, Fish o2) {
            return o1.getSpecies().compareTo(o2.getSpecies());
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fish)) return false;
        if (!super.equals(o)) return false;
        Fish fish = (Fish) o;
        return kind == fish.kind &&
                Objects.equals(species, fish.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), kind, species);
    }
}

