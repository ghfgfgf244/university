package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentManage {

    private static ArrayList<StudentBiz> stuBiz = new ArrayList<>();
    private static ArrayList<StudentIT> stuIT = new ArrayList<>();
    private static ArrayList<Student> stu = new ArrayList<>();
    static StudentIT stIT = new StudentIT();
    static StudentBiz stBIZ = new StudentBiz();

    static File file = new File("src\\");
    static String path = file.getAbsolutePath();

    public StudentManage(String path, String fName) {
        loadData("\\university.txt");
    }

    public StudentManage() {
    }

    public static ArrayList<StudentBiz> getStuBiz() {
        return stuBiz;
    }

    public static void setStuBiz(ArrayList<StudentBiz> stuBiz) {
        StudentManage.stuBiz = stuBiz;
    }

    public static ArrayList<StudentIT> getStuIT() {
        return stuIT;
    }

    public static void setStuIT(ArrayList<StudentIT> stuIT) {
        StudentManage.stuIT = stuIT;
    }

    public static void loadData(String fName) {
        String fruitInfo;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + fName));
            while ((fruitInfo = br.readLine()) != null) {
                String[] b = fruitInfo.split(" \\| ");

                if (b.length == 9) {
                    String id = b[0].trim();
                    String name = b[1].trim();
                    Address address = new Address(b[2], b[3], b[4], b[5]);
                    Double point1 = Double.valueOf(b[6].trim());
                    Double point2 = Double.valueOf(b[7].trim());
                    Double avePoint = Double.valueOf(b[8].trim());

                    if (id.contains("IT")) {
                        stuIT.add(new StudentIT(id, name, avePoint, address, point1, point2));
                    } else if (id.contains("BIZ")) {
                        stuBiz.add(new StudentBiz(id, name, avePoint, address, point1, point2));
                    }
                }
            }
            br.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public static void saveData(String fName) {
        try (PrintWriter pr = new PrintWriter(path + fName)) {

            for (StudentIT it : stuIT) {
                String line = String.format("%-10s | %-15s | %-20s | %-10.2f | %-10.2f | %-10.2f ",
                        it.getID(), it.getFullName(), it.getAddress(), it.getJavaScore(), it.getCssScore(), it.getAveScore());
                pr.println(line);
            }

            for (StudentBiz biz : stuBiz) {
                String line = String.format("%-10s | %-15s | %-20s | %-10.2f | %-10.2f | %-10.2f ",
                        biz.getID(), biz.getFullName(), biz.getAddress(), biz.getAccountingScore(), biz.getMarketingScore(), biz.getAveScore());
                pr.println(line);
            }

            pr.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void addStu(int choose) {
        String id = Utils.enterID("ID: ");
        String name = Utils.getAndValidValue("Student name: ", "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", "Student name just contain letter").toUpperCase();
        Address address = addAddress();
        switch (choose) {
            case 1 -> {
                Double javaScore = Utils.getAndValuePoint("Java Score: ");
                Double cssScore = Utils.getAndValuePoint("CSS Score: ");
                Double aveIT = stIT.calScoreAve(javaScore, cssScore);

                stuIT.add(new StudentIT(id, name, aveIT, address, javaScore, cssScore));
            }
            case 2 -> {
                Double accountingScore = Utils.getAndValuePoint("Accounting Score: ");
                Double marketingScore = Utils.getAndValuePoint("Marketing Score: ");
                Double aveBIZ = stBIZ.calScoreAve(accountingScore, marketingScore);

                stuBiz.add(new StudentBiz(id, name, aveBIZ, address, accountingScore, marketingScore));
            }
            default ->
                System.out.println("Invalid choice. Please choose again.");
        }
    }

    public static Address addAddress() {
        String street = Utils.getAndValidValue("Street name: ", "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", "Street name just contain letter");
        String district = Utils.getAndValidValue("District name: ", "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", "District name just contain letter");
        String city = Utils.getAndValidValue("City name: ", "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", "City name just contain letter");
        String country = Utils.getAndValidValue("Country name: ", "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", "Country name just contain letter");
        Address address = new Address(street, district, city, country);
        return address;
    }

    public static void printList() {
        if (stuIT.isEmpty() && stuBiz.isEmpty()) {
            System.out.println("No student found.");
        } else {
            for (StudentIT st : stuIT) {
                System.out.println(st.toString());
            }
            for (StudentBiz st : stuBiz) {
                System.out.println(st.toString());
            }
        }
    }

    public static void printStudentByNameAndGPA() {
        if (stuIT.isEmpty() && stuBiz.isEmpty()) {
            System.out.println("No student found.");
        } else {
            for (StudentIT st : stuIT) {
                System.out.printf("%-15s | %-10.2f \n", st.getFullName(), st.getAveScore());
            }
            for (StudentBiz st : stuBiz) {
                System.out.printf("%-15s | %-10.2f \n", st.getFullName(), st.getAveScore());
            }
        }
    }

    public static void mergeList() {
        stu.addAll(stuIT);
        stu.addAll(stuBiz);
    }

    public static void sortAndPrintByName() {
        mergeList();
        Collections.sort(stu, (s1, s2) -> s1.fullName.compareTo(s2.fullName));
        System.out.println("Sorted List of Students by Name:");
        for (Student student : stu) {
            System.out.println(student);
        }
    }

    public static void countStuSameCity() {
        mergeList();
        Map<String, List<Student>> groupByCity = new HashMap<>();

        for (Student x : stu) {
            groupByCity.computeIfAbsent(x.getAddress().getCity(), k -> new ArrayList<>()).add(x);
        }

        System.out.println("Number of students in each city:");
        for (Map.Entry<String, List<Student>> entry : groupByCity.entrySet()) {
            String city = entry.getKey();
            List<Student> studentsInCity = entry.getValue();

            System.out.println(city + ": " + studentsInCity.size() + " students");

            for (Student student : studentsInCity) {
                System.out.println("  " + student);
            }

        }

        stu.clear();

    }

    public static void updateStu() {
        String id = Utils.getAndValidValue("Input id of student you want to update: ", "^(IT|BIZ)[0-9]{4}$", "Invalid id. ID's Format: ITxxxx or BIZxxxx");
        for (StudentIT it : stuIT) {
            if (it.getID().equals(id)) {
                Address ad = addAddress();
                Double javaScore = Utils.getAndValuePoint("Java Score: ");
                Double cssScore = Utils.getAndValuePoint("CSS Score: ");
                Double aveIT = stIT.calScoreAve(javaScore, cssScore);
                it.setAddress(ad);
                it.setJavaScore(javaScore);
                it.setCssScore(cssScore);
                it.setAveScore(aveIT);
                return;
            }
        }
        for (StudentBiz biz : stuBiz) {
            if (biz.getID().equals(id)) {
                Address ad = addAddress();
                Double accountingScore = Utils.getAndValuePoint("Accounting Score: ");
                Double marketingScore = Utils.getAndValuePoint("Marketing Score: ");
                Double aveBIZ = stIT.calScoreAve(accountingScore, marketingScore);
                biz.setAddress(ad);
                biz.setAccountingScore(accountingScore);
                biz.setMarketingScore(marketingScore);
                biz.setAveScore(aveBIZ);
                return;
            }
        }
        System.out.println("Updated successfully");
    }

    public static void deleteStu() {
        String id = Utils.getAndValidValue("Input id of student you want to delete: ", "^(IT|BIZ)[0-9]{4}$", "Invalid id. ID's Format: ITxxxx or BIZxxxx");
        ArrayList<StudentIT> removeITStu = new ArrayList<>();
        for (StudentIT it : stuIT) {
            if (it.getID().equals(id)) {
                removeITStu.add(it);
            }
        }

        ArrayList<StudentBiz> removeBIZStu = new ArrayList<>();
        for (StudentBiz biz : stuBiz) {
            if (biz.getID().equals(id)) {
                removeBIZStu.add(biz);
            }
        }

        stuIT.removeAll(removeITStu);
        stuBiz.removeAll(removeBIZStu);
        System.out.println("Deleted successfully");
    }

    public static void reportStuPassed() {
        for (StudentBiz biz : stuBiz) {
            int count = 0;
            System.out.println("Student name: " + biz.getFullName());
            if (biz.getAccountingScore() >= 5) {
                count++;
                System.out.println("Passed subject: Accounting, Grade:  " + biz.getAccountingScore());
            }
            if (biz.getMarketingScore() >= 5) {
                count++;
                System.out.println("Passed subject: Marketing, Grade:  " + biz.getMarketingScore());
            }
            System.out.println("Total passed subjects: " + count);
            System.out.println("\n");
        }
        for (StudentIT it : stuIT) {
            int count = 0;
            System.out.println("Student name: " + it.getFullName());
            if (it.getJavaScore() >= 5) {
                count++;
                System.out.println("Passed subject: Java, Grade:  " + it.getJavaScore());
            }
            if (it.getCssScore() >= 5) {
                count++;
                System.out.println("Passed subject: CSS, Grade:  " + it.getCssScore());
            }
            System.out.println("Total passed subjects: " + count);
            System.out.println("\n");
        }
    }

}
