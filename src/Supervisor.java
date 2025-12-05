import java.util.ArrayList;
import java.util.List;

class Supervisor {
    private final int id;
    private final String name;
    private List<Animal> animals;

    public Supervisor(int id, String name) {
        this.id = id;
        this.name = name;
        this.animals = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setSupervisor(this);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
        animal.setSupervisor(null);
    }


}
