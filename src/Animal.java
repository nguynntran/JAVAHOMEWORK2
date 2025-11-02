
abstract class Animal {
    private final double height;
    private final int id;
    private Supervisor supervisor;

    public Animal(int id, double height) {
        this.height = height;
        this.id = id;
        this.supervisor = null;
    }


    public Double getHeight(){
        return this.height;
    }

    public int getId(){
        return this.id;
    }

    public Supervisor getSupervisor(){
        return this.supervisor;
    }

    public void setSupervisor(Supervisor supervisor){
        this.supervisor = supervisor;
    }

    public abstract String getType(); // get the kind of animal
    // should use Generics method to get the type of animal
}

interface MakeSound {
    String makeSound();
}

class Cat extends Animal implements MakeSound {

    public Cat(int id, double height) {
        super(id, height);
    }

    @Override
    public String makeSound() {
        return "Meow";
    }
    @Override
    public String getType() {
        return "Cat";
    }
}
class Dog extends Animal implements MakeSound {

    public Dog(int id, double height) {
        super(id, height);
    }

    @Override
    public String makeSound() {
        return "Woof";
    }
    @Override
    public String getType() {
        return "Dog";
    }

}

class Hippo extends Animal implements MakeSound {
    public Hippo(int id, double height) {
        super(id, height);
    }
    @Override
    public String makeSound() {
        return "Grunt";
    }
    @Override
    public String getType() {
        return "Hippo";
    }
}

class Horse extends Animal implements MakeSound {
    public Horse(int id, double height) {
        super(id, height);
    }
    @Override
    public String makeSound() {
        return "Neigh";
    }
    @Override
    public String getType() {
        return "Horse";
    }
}

class Fish extends Animal {
    public Fish(int id, double height) {
        super(id, height);
    }

    @Override
    public String getType() {
        return "Fish";
    }
}


