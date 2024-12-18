/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

/**
 *
 * @author PC
 */
public class Mavenproject1 {

static class Student {
        String id;
        String name;
        double marks;
        String rank;

        public Student(String id, String name, double marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
            this.rank = assignRank(marks);
        }

        private String assignRank(double marks) {
            if (marks < 5.0) {
                return "Fail";
            } else if (marks < 6.5) {
                return "Medium";
            } else if (marks < 7.5) {
                return "Good";
            } else if (marks < 9.0) {
                return "Very Good";
            } else {
                return "Excellent";
            }
        }

        @Override
        public String toString() {
            return "Student ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + rank;
        }
    }

    public static void insertionSort(List<Student> students) {
        int n = students.size();
        for (int i = 1; i < n; i++) {
            Student key = students.get(i);
            int j = i - 1;

            while (j >= 0 && students.get(j).marks > key.marks) {
                students.set(j + 1, students.get(j));
                j--;
            }
            students.set(j + 1, key);
        }
    }

    public static void quickSort(List<Student> students, int low, int high) {
        if (low < high) {
            int pi = partition(students, low, high);

            quickSort(students, low, pi - 1);
            quickSort(students, pi + 1, high);
        }
    }

    private static int partition(List<Student> students, int low, int high) {
        Student pivot = students.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (students.get(j).marks <= pivot.marks) {
                i++;

                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }

        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display Students");
            System.out.println("6. Sort Students by Marks (Insertion Sort)");
            System.out.println("7. Sort Students by Marks (Quick Sort)");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Marks: ");
                    double marks = scanner.nextDouble();
                    scanner.nextLine();
                    students.add(new Student(id, name, marks));
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Student ID to edit: ");
                    id = scanner.nextLine();
                    boolean found = false;
                    for (Student student : students) {
                        if (student.id.equals(id)) {
                            System.out.print("Enter new name: ");
                            student.name = scanner.nextLine();
                            System.out.print("Enter new marks: ");
                            student.marks = scanner.nextDouble();
                            scanner.nextLine();
                            student.rank = student.assignRank(student.marks);
                            System.out.println("Student updated successfully!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID to delete: ");
                    id = scanner.nextLine();
                    Iterator<Student> iterator = students.iterator();
                    found = false;
                    while (iterator.hasNext()) {
                        Student student = iterator.next();
                        if (student.id.equals(id)) {
                            iterator.remove();
                            System.out.println("Student deleted successfully!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter Student ID to search: ");
                    id = scanner.nextLine();
                    found = false;
                    for (Student student : students) {
                        if (student.id.equals(id)) {
                            System.out.println("Student found: " + student);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student not found!");
                    }
                    break;
                case 5:
                    if (students.isEmpty()) {
                        System.out.println("No students to display.");
                    } else {
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    }
                    break;
                case 6:
                    if (students.isEmpty()) {
                        System.out.println("No students to sort.");
                    } else {
                        insertionSort(students);
                        System.out.println("Students sorted by marks successfully using Insertion Sort!");
                        System.out.println("Sorted List:");
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    }
                    break;
                case 7:
                    if (students.isEmpty()) {
                        System.out.println("No students to sort.");
                    } else {
                        quickSort(students, 0, students.size() - 1);
                        System.out.println("Students sorted by marks successfully using Quick Sort!");
                        System.out.println("Sorted List:");
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    }
                    break;
                case 8:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
