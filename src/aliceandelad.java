/*
 * מגישים:
 * אליס ריינס - [216747204]
 [216553420] - אלעד כץ  *
 */

import java.util.Scanner;

public class aliceandelad {
    public static void info(String[] array, int Count) {
        for (int i = 0; i < Count; i++) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("What College would you like to add a lecturer to? ");
        String College1 = s.nextLine();

        String choices = ("Please choose your option\n " +
                "0 - Exit\n " +
                "1 - Add Lecturer to college\n" +
                "2 - Add a Committee to college\n" +
                "3 - Add a member to the committee\n" +
                "4 - update a committee's chairperson\n" +
                "5 - remove a member from the committee\n" +
                "6 - add a study department\n" +
                "7 - add lecturer to department\n" + // תוקן מ-committee ל-department
                "8 - avg pay of all the lecturers in the college\n" +
                "9 - avg pay of the lecturers in a specific department\n" + // תוקן מ-committee ל-department
                "10 -show the full information about every lecturer\n" +
                "11 -show the full information about every committee"
        );
        College myCollege = new College(College1);
        System.out.println(choices);
        int answer = s.nextInt();
        s.nextLine();

        while (answer != 0) {
            switch (answer) {
                case 0:
                    System.out.println("the program has ended");
                    break;
                case 1:
                    System.out.println("Enter lecturer name: ");
                    String inputName = s.nextLine();

                    while (!inputName.matches("[a-zA-Z ]+") || myCollege.findLecturerByName(inputName) != null) {
                        if (!inputName.matches("[a-zA-Z ]+")) {
                            System.out.println("Name can only have letters. Please enter a valid name: ");
                        } else {
                            System.out.println("A lecturer with this name already exists. Please choose a different name: ");
                        }
                        inputName = s.nextLine();
                    }

                    System.out.print("Enter ID: ");
                    String inputId = s.nextLine();

                    while (!inputId.matches("\\d{9}") || myCollege.findLecturerById(inputId) != null) {
                        if (!inputId.matches("\\d{9}")) {
                            System.out.println("ID must be exactly 9 digits. Please enter a valid ID: ");
                        } else {
                            System.out.println("A lecturer with this ID already exists. Please enter a different ID: ");
                        }
                        inputId = s.nextLine();
                    }

                    System.out.print("Enter type of degree: ");
                    String inputTypeOfDegree = s.nextLine();

                    System.out.print("Enter pay: ");
                    double inputPay = s.nextDouble();
                    s.nextLine();

                    System.out.print("Enter level of degree (e.g., BACHELORS, MASTERS, DOCTOR, PROF): ");
                    String inputLevelOfDegree = s.nextLine();

                    Lecturer myLecturer = new Lecturer(inputName, inputId, inputTypeOfDegree, inputPay, inputLevelOfDegree);

                    if (myCollege.addLecturer(myLecturer)) {
                        System.out.println("Lecturer added successfully");
                    } else {
                        System.out.println("Error saving lecturer.");
                    }
                    break;
                case 2:
                    System.out.print("Enter the name of the committee you want to add: ");
                    String committeeName = s.nextLine();
                    while (myCollege.findCommitteeByName(committeeName) != null) {
                        System.out.println("A committee with this name already exists.");
                        System.out.print("Please enter a different committee name: ");
                        committeeName = s.nextLine();
                    }

                    System.out.print("Enter the Chairperson's ID (Must be a Doctor/Prof): ");
                    String chairId = s.nextLine();
                    if (myCollege.addCommittee(committeeName, chairId)) {
                        System.out.println("Committee added successfully with the designated chairperson!");
                    } else {
                        System.out.println("Failed to add committee.");
                        System.out.println("(Make sure the Lecturer ID exists and that their degree level is DOCTOR or PROF)");
                    }
                    break;
                case 3:
                    System.out.print("Enter the Committee Name: ");
                    String commName3 = s.nextLine();

                    System.out.print("Enter the Lecturer's ID to add as a member: ");
                    String memberIdToAdd = s.nextLine();

                    if (myCollege.addLecturerToCommittee(memberIdToAdd, commName3)) {
                        System.out.println("The lecturer was successfully added as a member of the committee!");
                    } else {
                        System.out.println("Failed to add member to the committee.");
                        System.out.println("(Make sure the committee exists, the lecturer ID exists, they aren't already a member, and they aren't the chairperson)");
                    }
                    break;
                case 4:
                    System.out.print("Enter the Committee Name: ");
                    String commName4 = s.nextLine();

                    System.out.print("Enter the new Chairperson's ID (Must be a Doctor/Prof): ");
                    String newChairId = s.nextLine();
                    if (myCollege.updateChairman(newChairId, commName4)) {
                        System.out.println("The committee's chairperson was successfully updated!");
                    } else {
                        System.out.println("Failed to update chairperson.");
                        System.out.println("(Make sure the committee exists, the lecturer ID exists, and that they are a DOCTOR or PROF)");
                    }
                    break;
                case 5:
                    System.out.print("Enter the Committee Name: ");
                    String commName5 = s.nextLine();

                    System.out.print("Enter the ID of the member you want to remove: ");
                    String memberIdToRemove = s.nextLine();

                    if (myCollege.removeMemberFromCommittee(memberIdToRemove, commName5)) {
                        System.out.println("The member was successfully removed from the committee.");
                    } else {
                        System.out.println("Failed to remove member.");
                        System.out.println("(Make sure the committee exists and that this lecturer is actually a member in it)");
                    }
                    break;

                case 6:
                    System.out.print("Enter the Department Name: ");
                    String deptNameInput = s.nextLine();
                    while (myCollege.findDepartmentByName(deptNameInput) != null) {
                        System.out.println("A department with this name already exists.");
                        System.out.print("Please enter a different department name: ");
                        deptNameInput = s.nextLine();
                    }

                    System.out.print("Enter the number of students in the department: ");
                    int studentsCount = s.nextInt();
                    s.nextLine();

                    Department newDepartment = new Department(deptNameInput, studentsCount);
                    if (myCollege.addDepartment(newDepartment)) {
                        System.out.println("Department added successfully to the college!");
                    } else {
                        System.out.println("Failed to save the department.");
                    }
                    break;

                case 7:
                    System.out.print("Enter Lecturer ID: ");
                    String lecIdDept = s.nextLine();

                    System.out.print("Enter Department Name: ");
                    String deptName = s.nextLine();
                    if (myCollege.addLecturerToDepartment(lecIdDept, deptName)) {
                        System.out.println("Lecturer was successfully added to the department.");
                    } else {
                        System.out.println("Failed to add lecturer to the department.");
                        System.out.println("(Check if the ID or Department name are correct, or if the lecturer is already in a department.)");
                    }
                    break;
                case 8:
                    double globalAverage = myCollege.getAveragePay();

                    System.out.println("--- College Salary Statistics ---");
                    System.out.printf("The average salary of all lecturers is: %.2f\n", globalAverage);
                    break;
                case 9:
                    System.out.print("Enter the Department Name: ");
                    String targetDept = s.nextLine();

                    if (myCollege.findDepartmentByName(targetDept) == null) {
                        System.out.println("Error: Department '" + targetDept + "' does not exist.");
                    } else {
                        double deptAverage = myCollege.getAveragePayPerDepartment(targetDept);

                        System.out.println("--- Department Salary Statistics ---");
                        System.out.printf("The average salary in '%s' department is: %.2f\n", targetDept, deptAverage);
                    }
                    break;
                case 10:
                    System.out.println("--- All Lecturers ---");
                    System.out.println(myCollege.printLecturers());
                    break;
                case 11:
                    System.out.println("--- All Committees ---");
                    System.out.println(myCollege.printCommittees());
                    break;

                default:
                    System.out.println("you chose an option that does not exist");
            }
            System.out.println(choices);
            answer = s.nextInt();
            s.nextLine();
        }
    }
}