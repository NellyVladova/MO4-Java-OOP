package E03Inheritance.P06Animals;

public abstract class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public abstract String produceSound();

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(26);

        sb.append(this.getClass().getSimpleName());
        sb.append("\r");
        sb.append("\n");

        sb.append(name);
        sb.append(' ');
        sb.append(age);
        sb.append(' ');
        sb.append(gender);
        sb.append("\r");
        sb.append("\n");

        sb.append(this.produceSound());

        return sb.toString();
    }
}
