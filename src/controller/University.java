package controller;

import java.util.Scanner;
import model.StudentManage;
import model.Utils;
import view.Menu;

public class University extends Menu<String> {

    static Scanner sc = new Scanner(System.in);
    static String[] menu = {"Add student ", "Print student (Name and GPA) ", "Sort student by name ", "Quantity students have same city ", "Update / Delete ", "Report ", "Exit "};
    static String fName = "\\university.txt";
    StudentManage sm = new StudentManage();

    public University() {
        super("UNIVERSITY MANAGE", menu);
        StudentManage.loadData(fName);
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> {
                addNewStudent();
            }
            case 2 -> {
                StudentManage.printStudentByNameAndGPA();
            }
            case 3 -> {
                StudentManage.sortAndPrintByName();
            }
            case 4 -> {
                StudentManage.countStuSameCity();
            }
            case 5 -> {
                updateOrDelete();
            }
            case 6 -> {
                StudentManage.reportStuPassed();
            }
            case 7 -> {
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

    public void addNewStudent() {
        System.out.print("Do you want to add IT student (1) or BIZ student (2) ? Input your choose: ");
        int choose = sc.nextInt();
        do {
            if (choose == 1 || choose == 2) {
                StudentManage.addStu(choose);
            }
        } while (false);

        StudentManage.saveData(fName);
        StudentManage.printList();
    }

    public static void updateOrDelete() {
        char choose = 0;
        do {
            choose = Utils.getAndValidValue("Do you want to update (U) or delete (D) student ? ", "^[U|D]$", "Only input chars: U or D. Please choose again.").charAt(0);
            switch (choose) {
                case 'U' -> {
                    StudentManage.updateStu();
                }
                case 'D' -> {
                    StudentManage.deleteStu();
                }
                default ->
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (false);
        StudentManage.saveData(fName);
        StudentManage.printList();
    }

}
