package L02Encapsulation.P04FirstAndReserveTeam;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
    public void setFirstName(String firstName) {
        if(firstName.length() >= 3) {
            this.firstName = firstName;
        }else {
            throw new IllegalArgumentException("First name cannot be less than 3 symbols");
        }
    }

    public void setLastName(String lastName) {
        if(lastName.length() >= 3) {
            this.lastName = lastName;
        }else {
            throw new IllegalArgumentException("Last name cannot be les than 3 symbols");
        }
    }

    public void setAge(int age) {
        if(age > 0){
            this.age = age;
        }else {
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }
    }

    public void setSalary(double salary) {
        if (salary >= 460) {
            this.salary = salary;
        }else {
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
    }

    @Override
    public String toString(){
        return String.format("%s %s gets %f leva", this.firstName, this.lastName, this.salary);
    }

}
