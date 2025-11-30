import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Zoo {
    private Map<Integer, Animal> animals;
    private Map<Integer, Supervisor> supervisors;
    private List<ZooObserver> observers;
    private TreeSet<Animal> animalsByHeight;
    
    public Zoo() {
        animals = new HashMap<>();
        supervisors = new HashMap<>();
        observers = new ArrayList<>();
        animalsByHeight = new TreeSet<>(
                Comparator.comparingDouble(Animal::getHeight).thenComparingInt(Animal::getId));
    }

    //Constructor with given set of animals
    public Zoo(Set<Animal> animalSet){
        this();
        for (Animal animal : animalSet) {
            addAnimal(animal);
        }
    }
    // Add animal to the zoo
    public void addAnimal(Animal animal){
        animals.put(animal.getId(), animal);
        animalsByHeight.add(animal);
    }
    // Find an animal by ID
    public void getAnimalById(int id){
        Animal animal = animals.get(id);
        if (animal != null) {
            System.out.println("Animal with ID" + id + "found");
            System.out.println("Type of animal is:" + animal.getType());
            System.out.println("Height of animal is:" + animal.getHeight());
        } else {
            System.out.println("Animal with ID" + id + " not found");
        }
    }

    // Remove an animal by ID
    public void removeAnimal(int id){
        Animal animal = animals.remove(id);
        if (animal != null) {
            animalsByHeight.remove(animal);
        }
    }

    // Add a supervisor
    public void addSupervisor(Supervisor supervisor){ supervisors.put(supervisor.getId(), supervisor); }

    // Assign a supervisor to an animal
    public void assignSupervisor(int animalId, int supervisorId){
        Animal animal = animals.get(animalId);
        Supervisor supervisor = supervisors.get(supervisorId);
        if (animal != null && supervisor != null) {
            Supervisor oldSupervisor = animal.getSupervisor();
            if  (oldSupervisor != null) {
                oldSupervisor.removeAnimal(animal);
            }
            supervisor.addAnimal(animal);
            notifyObservers(animal, oldSupervisor, supervisor);
        }
    }

    // Get all animals assigned to a specific supervisor by ID
    public List<Animal> getAnimalsBySupervisorId(int supervisorId){
        Supervisor supervisor = supervisors.get(supervisorId);
        if (supervisor != null) {
            return supervisor.getAnimals();
        }
        return new ArrayList<>();
    }

    // Get all animals supervised by supervisors with a specific name
    public List<Animal> getAnimalsBySupervisorName(String name){
        List<Animal> result = new ArrayList<>();
        for (Supervisor supervisor : supervisors.values()) {
            if (supervisor.getName().equals(name)) {
                result.addAll(supervisor.getAnimals());
            }
        }
        return result;
    }

    // Get all animals with a height higher than a specified value
    public List<Animal> getAnimalsWithHeightHigherThan(double height){
        return new ArrayList<>(animalsByHeight.tailSet(new Animal(0, height) {
            @Override
            public String getType() {
                return null;
            }
        }, false));
    };

    // Get all animals that can make sound
    public List<Animal> getAnimalsThatCanMakeSound(){
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animals.values()) {
            if (animal instanceof MakeSound){
                result.add(animal);
            }
        }
        return result;
    }

    // Get all animals of a certain type
    public List<Animal> getAnimalsByType(String type){
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animals.values()) {
            if (animal.getType().equalsIgnoreCase(type)) {
                result.add(animal);
            }
        }

        return result;

    }

    // Observer pattern methods
    public void addObserver(ZooObserver observer){observers.add(observer);}

    public void removeObserver(ZooObserver observer){observers.remove(observer);}

    private void notifyObservers(Animal animal, Supervisor oldSupervisor, Supervisor newSupervisor){
        for (ZooObserver observer : observers) {
            observer.onSupervisorChanged(animal, oldSupervisor, newSupervisor);
        }
    }
}

interface ZooObserver {
    void onSupervisorChanged(Animal animal, Supervisor oldSupervisor, Supervisor newSupervisor);
}

class SupervisorChangeObserver implements ZooObserver {

    @Override
    public void onSupervisorChanged(Animal animal, Supervisor oldSupervisor,
                                    Supervisor newSupervisor) {
        System.out.println(
                "Animal ID: " + animal.getId() + " changed from " + (oldSupervisor != null ? "Supervisor "
                        + oldSupervisor.getName() : "No Supervisor") + " to " + (newSupervisor != null ?
                        "Supervisor " + newSupervisor.getName() : "No Supervisor"));
    }
}







