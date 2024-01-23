package controller;

import java.util.Scanner;
import view.Menu;

public class University extends Menu<String> {

    static Scanner sc = new Scanner(System.in);
    static String[] menu = {"Add student ","Print student (Name and GPA) ","Sort student by name ","Quantity students have same city ","Update ","Delete ","Report ","Exit "};
    static String fName = "\\university.txt";

    public University() {
        super("UNIVERSITY MANAGE", menu);

    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> {
                addNewStudent();
            }
            case 2 -> {

            }
            case 3 -> {

            }
            case 4 -> {

            }
            case 5 -> {

            }
            case 6 -> {

            }
            case 7 -> {

            }
            case 8 -> {
                System.out.println("Exiting...");
            }
            default ->
                System.out.println("Invalid choice. Please choose again.");
        }

    }

    public static void main(String[] args) {
        University uni = new University();
        uni.run();
    }

    private void addNewStudent() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
