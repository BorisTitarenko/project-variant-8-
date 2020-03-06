
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Animal implements Serializable {
    protected LocalDate birthDate;
    protected String color;
    public static final int FIELDS = 2;

    public Animal(){

    }

    public Animal(LocalDate birthDate, String color) {
        this.birthDate = birthDate;
        this.color = color;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String move(){
        return "I'm moving";
    }

    public int getAge(){
        int diffInYear = LocalDate.now().getYear() - birthDate.getYear();
        int diffInMonth = LocalDate.now().getMonthValue() - birthDate.getMonthValue();
        int diffInDays = LocalDate.now().getDayOfMonth() - birthDate.getDayOfMonth();
        byte dateAccuracy = 0;
        if(diffInMonth < 0) {
            dateAccuracy = 1;
        } else if(diffInMonth == 0 && diffInDays < 0) {
            dateAccuracy = 1;
        }
        return diffInYear - dateAccuracy;
    }

    @Override
    public String toString() {
        return move();
    }

    public String animalRepresentation(){
        return String.format("Some animal {color: %s, birth date: %s}", this.color, this.birthDate);
    }

    public void output(BufferedWriter writer) throws IOException{
        String outString = String.format("{%s} ", this.animalRepresentation());
        writer.write(outString);
        writer.flush();
    }

    public Animal input(BufferedReader reader) throws IOException, NumberFormatException{

        String animalColor;
        String animalBirthDate;

        System.out.println("Enter color: ");
        if((animalColor = reader.readLine()) == null){
            return null;
        }
        System.out.println("Enter birth date in the folowing format \'year month day\'");
        if((animalBirthDate = reader.readLine()) == null){
            return null;
        }

        String[] parsedDate = animalBirthDate.split(" ");
        int year = Integer.parseInt(parsedDate[0]);
        int month = Integer.parseInt(parsedDate[1]);
        int day = Integer.parseInt(parsedDate[2]);

        LocalDate date = LocalDate.of(year, month, day);

        this.color = animalColor;
        this.birthDate = date;

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(birthDate, animal.birthDate) &&
                Objects.equals(color, animal.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate, color);
    }
}
