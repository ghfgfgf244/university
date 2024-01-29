package model;

import java.util.Scanner;

public class Utils {

    static Scanner sc = new Scanner(System.in);

    public static String getValue(String ms) {
        System.out.print(ms);
        return sc.nextLine().trim();
    }

    public static String getAndValidValue(String msg, String regex, String cause) {
        String value;
        while (true) {
            try {
                value = getValue(msg);
                if (value.isEmpty()) {
                    throw new Exception("Input can not empty");
                }
                if (!value.matches(regex)) {
                    throw new Exception(cause);
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return value;
    }

    public static double getAndValuePoint(String msg) {
        String point;
        while (true) {
            try {
                point = getAndValidValue(msg, "[\\d.]+", "Invalid point");

                if (Double.parseDouble(point) <= 0) {
                    throw new Exception("Point must a positive number");
                }

                return Double.parseDouble(point);
            } catch (NumberFormatException e) {
                System.out.println("Invalid point");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static String enterID(String st) {

        String id;
        do {
            id = getAndValidValue(st, "^(IT|BIZ)[0-9]{4}$", "Invalid id. ID's Format: ITxxxx or BIZxxxx");

            if (checkDuplicate(id)) {
                System.out.println("The ID is already use. Please enter another ID.");
            }
        } while (checkDuplicate(id));
        return id;
    }

    public static boolean checkDuplicate(String obj) {
        for (StudentIT it : StudentManage.getStuIT()) {
            if (it.getID().equals(obj)) {
                return true;
            }
        }
        for (StudentBiz biz : StudentManage.getStuBiz()) {
            if (biz.getID().equals(obj)) {
                return true;
            }
        }

        return false;
    }

}
