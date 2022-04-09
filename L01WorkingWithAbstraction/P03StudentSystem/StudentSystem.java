package L01WorkingWithAbstraction.P03StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> studentsData;

    public StudentSystem() {
        this.studentsData = new HashMap<>();
    }

    public Map<String, Student> getStudentsData() {
        return this.studentsData;
    }

    public void executeCommand(String[] commandParts) {
        String command = commandParts[0];

        switch (command) {
            case "Create":
                createStudent(commandParts);
                break;
            case "Show":
                String name = commandParts[1];
                showStudent(name);
                break;
        }
    }

    private void showStudent(String name) {
        if (studentsData.containsKey(name)) {
            Student student = studentsData.get(name);
            StringBuilder output = getOutputMessage(student);
            System.out.println(output);
        }
    }

    private StringBuilder getOutputMessage(Student student) {
        StringBuilder output = new StringBuilder(String.format("%s is %s years old.", student.getName(), student.getAge()));

        if (student.getGrade() >= 5.00) {
            output.append(" Excellent student.");
        } else if (student.getGrade() < 5.00 && student.getGrade() >= 3.50) {
            output.append(" Average student.");
        } else {
            output.append(" Very nice person.");
        }
        return output;
    }

    private void createStudent(String[] commandParts) {
        String name = commandParts[1];
        int age = Integer.parseInt(commandParts[2]);
        double grade = Double.parseDouble(commandParts[3]);
        if (!studentsData.containsKey(name)) {
            Student student = new Student(name, age, grade);
            studentsData.put(name, student);
        }
    }
}


