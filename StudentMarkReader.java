/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    String lastName;
    String firstName;
    String studentID;
    double[] assessmentMarks;
    double total;

    public Student(String lastName, String firstName, String studentID, double[] assessmentMarks) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = studentID;
        this.assessmentMarks = assessmentMarks;
    }

    public double getTotalMark() {
        total = 0;
        for (double mark : assessmentMarks) {
            total += mark;
        }
        return total;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName +
                ", Student ID: " + studentID +
                ", Assessment Marks: A1=" + assessmentMarks[0] + ", A2=" + assessmentMarks[1] + ", A3=" + assessmentMarks[2] +
                ", Total Mark: " + getTotalMark();
    }
}

public class StudentMarkReader {
    public static void main(String[] args) {
        
        
        String fileName = "C:\\Users\\User\\Documents\\NetBeansProjects\\assignement\\src\\main\\java\\com\\mycompany\\assignement\\prog5001_students_grade_2022.txt"; // Replace with your file path
        Student[] students = new Student[100];
        int count = 0;
        //Ask user number from 1 to 4 

        Scanner sca = new Scanner(System.in);
        String choice;
        do {
            System.out.print("Enter the number 1 to 4: ");
            int input = sca.nextInt();

            switch (input) {
                case 1:
                    firstFunction(fileName, students);
                    break;
                case 2:
                    count = second(fileName, count, students);
                    break;
                case 3:
                    threshold(count, students);
                    break;
                case 4:
                    //Sorting total first
                    quickSort(students, 0, count - 1);

                    //Print top 5 student with most and least total marks
                    //Top 5 lesat marks 
                    System.out.println("Least marks");
                    for (int i = 0; i < 5; i++) {
                        System.out.println(students[i]);
                    }
                    //Top 5 higestmarks
                    System.out.println("HIgh marks");
                    for (int i = count - 1; i > count - 6; i--) {
                        System.out.println(students[i]);
                    }
                    break;

                default:
                    System.out.println("Enter the number between 1 to 4");
            }
            System.out.println("Do you want to contine?(Yes/No):");
            choice = sca.next().toLowerCase();
        } while (choice.equals("yes"));

    }

    public static void firstFunction(String fileName, Student[] students) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                // Add each line to the list
                if (line.trim().isEmpty() || line.trim().startsWith("//")) {
                    continue;
                }
                if (!line.trim().isEmpty() && !line.trim().startsWith("Unit:") && !line.contains("Last Name")) {

                    String[] parts = line.split("\\s{2,}");
                    String lastName = parts[0];
                    String firstName = parts[1];
                    String studentID = parts[2];
                    double[] assessmentMarks = new double[3];

                    for (int i = 0; i < 3; i++) {
                        assessmentMarks[i] = (i + 3 < parts.length) ? Double.parseDouble(parts[i + 3]) : 0.0;
                    }
                    System.out.println("Name: " + firstName + " " + lastName
                            + ", Student ID: " + studentID
                            + ", Assessment Marks: A1=" + assessmentMarks[0] + ", A2=" + assessmentMarks[1] + ", A3=" + assessmentMarks[2]);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int second(String fileName, int count, Student[] students) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.trim().startsWith("Unit:") && !line.contains("Last Name")) {

                    // Process student information
                    String[] parts = line.split("\\s{2,}");
                    String lastName = parts[0];
                    String firstName = parts[1];
                    String studentID = parts[2];
                    double[] assessmentMarks = new double[3];

                    for (int i = 0; i < 3; i++) {
                        assessmentMarks[i] = (i + 3 < parts.length) ? Double.parseDouble(parts[i + 3]) : 0.0;
                    }

                    Student student = new Student(lastName, firstName, studentID, assessmentMarks);
                    System.out.println(student);
                    students[count++] = student;
                }
            }
            //Checking count value
            //System.out.println(count);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return count;
    }
    
        public static void threshold(int count, Student[] students) {
        //F3 input the threshold and print thr info of student whose total is less than threshold
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the threshold for total marks: ");
        double threshold = scanner.nextDouble();

        // Print students below the threshold
        for (int i = 0; i < count; i++) {
            if (students[i].total < threshold) {
                System.out.println(students[i]);
            }
        }
    }
        
    }


