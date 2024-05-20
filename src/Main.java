import java.time.LocalDate;
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

        //choosing role
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
            System.out.print("Enter a number: ");
        }

        boolean isAdminActionChosen = false;
        while (!isAdminActionChosen) {
            String adminAction = scanner.nextLine();
            //----------------------------------------
            /*these are temp objects and are going to be
            replaced when backend is finished*/
            Student student = new Student("name", "family", "1");
            Teacher teacher = new Teacher("12", "name", "family");
            Course course = new Course("name", "12", teacher, 2,
                    LocalDate.of(2020, 12, 12));
            Assignment assignment = new Assignment(LocalDate.of(2020, 12, 12),
                    true);
            //----------------------------------------
            switch (adminAction) {
                case "1":
                    System.out.println("Enter Student ID: ");
                    System.out.println("Enter Course ID: ");
                    //find student and course with this ID
                    //temp actions-----------------
                    course.addStudent(student);
                    //-----------------------------
                    isAdminActionChosen = true;
                    break;
                case "2":
                    System.out.println();
                    isAdminActionChosen = true;
                    break;
                case "3":
                    System.out.println();
                    isAdminActionChosen = true;
                    break;
                case "4":
                    System.out.println();
                    isAdminActionChosen = true;
                    break;
                case "5":
                    System.out.println();
                    isAdminActionChosen = true;
                    break;
                case "6":
                    System.out.println();
                    isAdminActionChosen = true;
                    break;
                case "7":
                    System.out.println();
                    isAdminActionChosen = true;
                    break;
                case "8":
                    System.out.println();
                    isAdminActionChosen = true;
                    break;
                case "9":
                    System.out.println();
                    isAdminActionChosen = true;
                    break;
                case "10":
                    System.out.println();
                    isAdminActionChosen = true;
                    break;
                default:
                    System.out.println(RED + "Invalid action, please choose again." + RESET);
                    System.out.print("Enter a number: ");
                    break;
            }
        }


        //به نظرم بعد از انتخاب عملکرد ایدی استاد رو دریافت کنیم
        //می تونیم بعد از انتخاب role استاد و قبل از پیام
        //خوش آمد هم دریافت کنیم.
        //Teacher menu
//        if ("Teacher".equalsIgnoreCase(currentRole)) {
//            System.out.println("""
//                    1. Add student
//                    2. remove student
//                    3. Give mark to a student
//                    4. Change an assignment deadline
//                    5. Add assignment
//                    6. Remove assignment
//                    """);
//            System.out.print("Enter a number: ");
//        }
//        int teacherAction = scanner.nextInt();
//        switch (teacherAction) {
//            case 1:
//                System.out.println("adding student...");
//
//        }

    }
}