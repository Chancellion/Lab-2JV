package Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class University {
    private List<Department> departments = new ArrayList<>();

    public void addDepartment(String name, List<String> professors) {
        departments.add(new Department(name, professors));
    }

    public void displayDepartments() {
        for (Department department : departments) {
            System.out.println("Вiддiл: " + department.getName());
            System.out.println("Професори: " + department.getProfessors());
        }
    }

    public void manageEvents() {
        Event conference = new Event() {
            @Override
            public void organize() {
                System.out.println("Органiзацiя конференцiї...");
            }
        };
        conference.organize();
    }

    private class Department {
        private String name;
        private List<String> professors;

        public Department(String name, List<String> professors) {
            this.name = name;
            this.professors = professors;
        }

        public String getName() {
            return name;
        }

        public List<String> getProfessors() {
            return professors;
        }
    }

    public static class Helper {
        public static double calculateAverageGrade(List<Integer> grades) {
            int sum = 0;
            for (int grade : grades) {
                sum += grade;
            }
            return grades.isEmpty() ? 0 : (double) sum / grades.size();
        }
    }

    interface Event {
        void organize();
    }

    public static void main(String[] args) {
        System.out.println("Данiїл Iванченко, КIб-1-23-4.0д:");
        University university = new University();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("1. Додати вiддiл");
                System.out.println("2. Список вiддiлiв");
                System.out.println("3. Управлiння подiями");
                System.out.println("4. Вихiд");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Введiть назву вiддiлу:");
                        String name = scanner.nextLine();
                        System.out.println("Введiть професорів (через кому вiдокремлюються):");
                        String[] professorsArray = scanner.nextLine().split(",");
                        List<String> professors = new ArrayList<>();
                        for (String professor : professorsArray) {
                            professors.add(professor.trim());
                        }
                        university.addDepartment(name, professors);
                        break;
                    case 2:
                        university.displayDepartments();
                        break;
                    case 3:
                        university.manageEvents();
                        break;
                    case 4:
                        System.out.println("Вихiд...");
                        return;
                    default:
                        System.out.println("Невiрний вибiр. Спробуй ще раз.");
                }
            }
        }
    }
}
