import java.util.Set;
import java.util.HashSet;
public class Main {

    public static void main(String[] args) {
        // 1.Create a zoo
        Zoo zoo = new Zoo();

        // Create some supervisors
        Supervisor supervisor1 = new Supervisor(1, "John");
        Supervisor supervisor2 = new Supervisor(2, "Alice");

        // Add supervisors to the zoo
        zoo.addSupervisor(supervisor1);
        zoo.addSupervisor(supervisor2);

        // Create some animals
        Animal cat = new Cat(101, 0.5);
        Animal dog = new Dog(102, 0.7);
        Animal horse = new Horse(103, 1.6);
        Animal hippo = new Hippo(104, 1.6);
        Animal fish = new Fish(105, 0.1);

        Animal cat2 = new Cat(106, 0.5);
        // 2. Constructor with set of animals
        Set<Animal> AnimalsSet = new HashSet<>();
        AnimalsSet.add(cat);
        AnimalsSet.add(dog);
        AnimalsSet.add(horse);

        Zoo zoo1 = new Zoo(AnimalsSet);

        // 3.Add animals to the zoo
        zoo1.addAnimal(cat);
        zoo1.addAnimal(dog);
        zoo1.addAnimal(horse);
        zoo1.addAnimal(hippo);
        zoo1.addAnimal(fish);
        zoo1.addAnimal(cat2);

        // 4.Remove an animal by id
        zoo1.removeAnimal(103);
        // 4.Searching an animal by id
        zoo1.getAnimalById(102);

        // Create an observer to track supervisor changes
        SupervisorChangeObserver observer = new SupervisorChangeObserver();
        zoo1.addObserver(observer);

        // 5.Assign supervisors to animals
        zoo1.assignSupervisor(101, 1);  // Assign John
        zoo1.assignSupervisor(102, 1);  // Assign John
        zoo1.assignSupervisor(103, 1);  // Assign Alice
        zoo1.assignSupervisor(104, 2);  // Assign Alice
        zoo1.assignSupervisor(105, 2);  // Assign Alice
        zoo1.assignSupervisor(106, 2);  // Assign Alice

        // 6.Display all animals assigned to supervisor id
        System.out.println("\nAnimals supervised by John:");
        for (Animal animal : zoo1.getAnimalsBySupervisorId(1)) {
            System.out.println(animal.getType() + " with ID " + animal.getId());
        }

        // 7.Display all animals supervised by supervisor name
        System.out.println("\nAnimals supervised by Alice:");
        for (Animal animal : zoo1.getAnimalsBySupervisorName("Alice")) {
            System.out.println(animal.getType() + " with ID " + animal.getId());
        }

        // 8.Display all animals with a height greater than 0.6 meters
        System.out.println("\nAnimals with height greater than 0.6 meters:");
        for (Animal animal : zoo1.getAnimalsWithHeightHigherThan(0.6)) {
            System.out.println(
                    animal.getType() + " with ID " + animal.getId() + ", Height: " + animal.getHeight());
        }

        // 9.Display all animals that can make sounds
        System.out.println("\nAnimals that can make sounds:");
        for (Animal animal : zoo1.getAnimalsThatCanMakeSound()) {
            MakeSound soundMaker = (MakeSound) animal;
            System.out.println(
                    animal.getType() + " with ID " + animal.getId() + " says " + "\"" + soundMaker.makeSound()
                            + "\"");
        }

        // Change the supervisor for Cat (id 101) from John to Alice
        System.out.println("\nReassign Cat (ID 101) to Alice");
        zoo1.assignSupervisor(101, 2);

        // Display the updated list of animals for Alice
        System.out.println("\nAnimals supervised by Alice after reassignment:");
        for (Animal animal : zoo1.getAnimalsBySupervisorId(2)) {
            System.out.println(animal.getType() + " with ID " + animal.getId());
        }

        // 10.Getting all the animals in a zoo of a certain type
        System.out.println("\nAnimals that are Cat:");
        for (Animal animal : zoo1.getAnimalsByType("Cat")) {
            System.out.println("Animal with ID " + animal.getId() + " is a cat");
        }
    }
}
