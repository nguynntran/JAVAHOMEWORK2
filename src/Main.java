import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Zoo Management System ===\n");
        
        // Setup initial zoo and data
        Zoo zoo1 = setupZooWithData();
        
        // Show all zoo operations
        showZooOperations(zoo1);
    }
    
    // Two methods for setup and showing the task of requirements
    private static Zoo setupZooWithData() {
        // 1. Create a zoo (default constructor)
        Zoo zoo = new Zoo();
        System.out.println("1. Created zoo with default constructor");

        // Create some supervisors and add to zoo
        Supervisor supervisor1 = new Supervisor(1, "John");
        Supervisor supervisor2 = new Supervisor(2, "Alice");

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
        Set<Animal> animalsSet = new HashSet<>();
        animalsSet.add(cat);
        animalsSet.add(dog);
        animalsSet.add(horse);
        Zoo zoo1 = new Zoo(animalsSet);
        System.out.println("2. Created zoo with initial animals set");

        // 3. Add animals to the zoo
        zoo1.addAnimal(hippo);
        zoo1.addAnimal(fish);
        zoo1.addAnimal(cat2);
        System.out.println("3. Added more animals to zoo");

        // Add supervisors to zoo1 and setup observer
        zoo1.addSupervisor(supervisor1);
        zoo1.addSupervisor(supervisor2);
        
        return zoo1;
    }

    
    
    private static void showZooOperations(Zoo zoo1) {
        // 4. Remove an animal by id and search
        System.out.println("\n4. Remove animal ID 103 ");
        zoo1.removeAnimal(103);
        System.out.println("4. Search for animal ID 102");
        zoo1.getAnimalById(102);

        // 5. Assign supervisors to animals 
        System.out.println("\n5. Assigning supervisors to animals:");
        zoo1.assignSupervisor(101, 1);  // John
        zoo1.assignSupervisor(102, 1);  // John
        zoo1.assignSupervisor(104, 2);  // Alice
        zoo1.assignSupervisor(105, 2);  // Alice
        zoo1.assignSupervisor(106, 2);  // Alice


        // 6. Display all animals assigned to supervisor by ID
        System.out.println("\n6. Animals supervised by John (ID 1):");
        for (Animal animal : zoo1.getAnimalsBySupervisorId(1)) {
            System.out.println("   " + animal.getType() + " with ID " + animal.getId());
        }

        // 7. Display all animals supervised by supervisor name
        System.out.println("\n7. Animals supervised by Alice:");
        for (Animal animal : zoo1.getAnimalsBySupervisorName("Alice")) {
            System.out.println("   " + animal.getType() + " with ID " + animal.getId());
        }

        // 8. Display all animals with height greater than 0.6 meters
        System.out.println("\n8. Animals with height greater than 0.6 meters:");
        for (Animal animal : zoo1.getAnimalsWithHeightHigherThan(0.6)) {
            System.out.println("   " + animal.getType() + " (ID: " + animal.getId() + 
                             ", Height: " + animal.getHeight() + "m)");
        }

        // 9. Display all animals that can make sounds
        System.out.println("\n9. Animals that can make sounds:");
        for (Animal animal : zoo1.getAnimalsThatCanMakeSound()) {
            MakeSound soundMaker = (MakeSound) animal;
            System.out.println("   " + animal.getType() + " (ID: " + animal.getId() + 
                             ") says \"" + soundMaker.makeSound() + "\"");
        }

        // 10. Getting all animals of a certain type
        System.out.println("\n10. All animals that are Cat:");
        for (Animal animal : zoo1.getAnimalsByType("Cat")) {
            System.out.println("Animal with ID " + animal.getId() + " is a cat");
        }

        // Change the supervisor for Cat (id 101) from John to Alice
        System.out.println("\nReassign Cat (ID 101) to Alice");
        zoo1.assignSupervisor(101, 2);

        // Display the updated list of animals for Alice
        System.out.println("\nAnimals supervised by Alice after reassignment:");
        for (Animal animal : zoo1.getAnimalsBySupervisorId(2)) {
            System.out.println(animal.getType() + " with ID " + animal.getId());
        }
    }
}