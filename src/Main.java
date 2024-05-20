import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //color codes
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        //first message
        System.out.println("""
                -----------------Greetings!-----------------
                To access the system, please choose your role.
                If you are an Admin, type "Admin".
                If you are an Teacher, type "Teacher".""");
        System.out.print("Enter your role: ");

        Scanner scanner = new Scanner(System.in);

        String currentRole;
        while (true) {
            String role = scanner.nextLine();
            if ("Admin".equalsIgnoreCase(role)) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(GREEN + "Welcome Admin!" + "\n" + RESET
                        + "Choose from the list below:" + "\n" +
                        "---------------------------------");
                currentRole = role;
                break;
            } else if ("Teacher".equalsIgnoreCase(role)) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(GREEN + "Welcome Teacher!" + "\n" + RESET
                        + "Choose from the list below:" + "\n" +
                        "---------------------------------");
                currentRole = role;
                break;
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(RED + "Invalid Role, Please Try Again." + RESET);
                System.out.print("Enter your role: ");
            }
        }

        //Admin menu
        if ("Admin".equalsIgnoreCase(currentRole)) {
            System.out.println("""
                    1. Add student
                    2. Remove student
                    3. Give mark to a student
                    4. Change an assignment deadline
                    5. Add assignment
                    6. Remove assignment
                    7. Add teacher
                    8. Remove teacher
                    9. Add course
                    10. Remove course
                    """);
        }


        //Teacher menu
        //به نظرم بعد از انتخاب عملکرد ایدی استاد رو دریافت کنیم
        //می تونیم بعد از انتخاب role استاد و قبل از پیام
        //خوش آمد هم دریافت کنیم.
        if ("Teacher".equalsIgnoreCase(currentRole)) {
            System.out.println("""
                    1. Add student
                    2. remove student
                    3. Give mark to a student
                    4. Change an assignment deadline
                    5. Add assignment
                    6. Remove assignment
                    """);
        }
        int teacherAction = scanner.nextInt();
        switch (teacherAction) {
            case 1:
                System.out.println("adding student...");

        }

    }
}