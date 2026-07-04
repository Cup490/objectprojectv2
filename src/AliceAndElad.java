/*
 * מגישים:
 * אליס ריינס - [216747204]
 [216553420] - אלעד כץ  *
 */

import java.util.Scanner;

public class AliceAndElad {
    public static void info(String[] array, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("What College would you like to add a lecturer to? ");
        String college1 = s.nextLine();

        String choices = ("Please choose your option\n " +
                "0 - Exit\n " +
                "1 - Add Lecturer to college\n" +
                "2 - Add a Committee to college\n" +
                "3 - Add a member to the committee\n" +
                "4 - update a committee's chairperson\n" +
                "5 - remove a member from the committee\n" +
                "6 - add a study department\n" +
                "7 - add lecturer to department\n" +
                "8 - avg pay of all the lecturers in the college\n" +
                "9 - avg pay of the lecturers in a specific department\n" +
                "10 -show the full information about every lecturer\n" +
                "11 -show the full information about every committee\n" +
                "12 - Compare Doctors/Professors by articles\n" +
                "13 - Compare two Committees\n" +
                "14 - Clone a Committee"
        );
        College myCollege = new College(college1);
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

                    System.out.print("Enter level of degree (BACHELORS, MASTERS, DOCTOR, PROF): ");
                    String inputLevelOfDegree = s.nextLine();
                    String levelUpper = inputLevelOfDegree.trim().toUpperCase();

                    while (!levelUpper.equals("BACHELORS") && !levelUpper.equals("MASTERS")
                            && !levelUpper.equals("DOCTOR") && !levelUpper.equals("PROF")) {
                        System.out.print("Invalid degree level. Please enter one of BACHELORS, MASTERS, DOCTOR, PROF: ");
                        inputLevelOfDegree = s.nextLine();
                        levelUpper = inputLevelOfDegree.trim().toUpperCase();
                    }

                    // Build the correct concrete type so that Doctor/Professor-only features
                    // (chairing a committee, article tracking, article-count comparisons)
                    // are actually reachable from the menu.
                    try {
                        Lecturer myLecturer;
                        if (levelUpper.equals("PROF")) {
                            System.out.print("Enter the granting body: ");
                            String grantingBody = s.nextLine();
                            myLecturer = new Professor(inputName, inputId, inputTypeOfDegree, inputPay, grantingBody);
                        } else if (levelUpper.equals("DOCTOR")) {
                            myLecturer = new Doctor(inputName, inputId, inputTypeOfDegree, inputPay);
                        } else {
                            myLecturer = new Lecturer(inputName, inputId, inputTypeOfDegree, inputPay, inputLevelOfDegree);
                        }

                        myCollege.addLecturer(myLecturer);
                        System.out.println("Lecturer added successfully");

                        if (myLecturer instanceof Doctor) {
                            System.out.print("How many articles would you like to add? ");
                            int articleCount = s.nextInt();
                            s.nextLine();
                            for (int i = 0; i < articleCount; i++) {
                                System.out.print("Enter article title #" + (i + 1) + ": ");
                                String articleTitle = s.nextLine();
                                try {
                                    ((Doctor) myLecturer).addArticle(articleTitle);
                                } catch (ArticleAlreadyExistsException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                    } catch (LecturerAlreadyExistsException | InvalidLecturerNameException | InvalidLecturerIdException e) {
                        System.out.println(e.getMessage());
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

                    try {
                        myCollege.addCommittee(committeeName, chairId);
                        System.out.println("Committee added successfully with the designated chairperson!");
                    } catch (CommitteeAlreadyExistsException | InvalidChairpersonException | LecturerNotFoundException | AlreadyMemberException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter the Committee Name: ");
                    String commName3 = s.nextLine();

                    System.out.print("Enter the Lecturer's ID to add as a member: ");
                    String memberIdToAdd = s.nextLine();

                    try {
                        myCollege.addLecturerToCommittee(memberIdToAdd, commName3);
                        System.out.println("The lecturer was successfully added as a member of the committee!");
                    } catch (LecturerNotFoundException | CommitteeNotFoundException | ChairCannotBeMemberException | AlreadyMemberException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter the Committee Name: ");
                    String commName4 = s.nextLine();

                    System.out.print("Enter the new Chairperson's ID (Must be a Doctor/Prof): ");
                    String newChairId = s.nextLine();

                    try {
                        myCollege.updateChairman(newChairId, commName4);
                        System.out.println("The committee's chairperson was successfully updated!");
                    } catch (LecturerNotFoundException | CommitteeNotFoundException | InvalidChairpersonException | NotInCommitteeException | AlreadyMemberException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Enter the Committee Name: ");
                    String commName5 = s.nextLine();

                    System.out.print("Enter the ID of the member you want to remove: ");
                    String memberIdToRemove = s.nextLine();

                    try {
                        myCollege.removeMemberFromCommittee(memberIdToRemove, commName5);
                        System.out.println("The member was successfully removed from the committee.");
                    } catch (LecturerNotFoundException | CommitteeNotFoundException | NotInCommitteeException e) {
                        System.out.println(e.getMessage());
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

                    try {
                        myCollege.addDepartment(newDepartment);
                        System.out.println("Department added successfully to the college!");
                    } catch (DepartmentAlreadyExistsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    System.out.print("Enter Lecturer ID: ");
                    String lecIdDept = s.nextLine();

                    System.out.print("Enter Department Name: ");
                    String deptName = s.nextLine();

                    try {
                        myCollege.addLecturerToDepartment(lecIdDept, deptName);
                        System.out.println("Lecturer was successfully added to the department.");
                    } catch (LecturerNotFoundException | DepartmentNotFoundException | AlreadyInDepartmentException e) {
                        System.out.println(e.getMessage());
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

                case 12:
                    System.out.print("Enter the ID of the first Doctor/Professor: ");
                    String docId1 = s.nextLine();
                    System.out.print("Enter the ID of the second Doctor/Professor: ");
                    String docId2 = s.nextLine();

                    Lecturer l1 = myCollege.findLecturerById(docId1);
                    Lecturer l2 = myCollege.findLecturerById(docId2);

                    if (l1 instanceof Doctor && l2 instanceof Doctor) {
                        Doctor d1 = (Doctor) l1;
                        Doctor d2 = (Doctor) l2;
                        int cmp = d1.compareTo(d2);
                        if (cmp > 0) {
                            System.out.println(d1.getLecturerName() + " has more articles.");
                        } else if (cmp < 0) {
                            System.out.println(d2.getLecturerName() + " has more articles.");
                        } else {
                            System.out.println("Both have the exact same number of articles.");
                        }
                    } else {
                        System.out.println("Error: One or both of the IDs do not belong to a Doctor/Professor, or they do not exist.");
                    }
                    break;

                case 13:
                    System.out.print("Enter the first Committee Name: ");
                    String cName1 = s.nextLine();
                    System.out.print("Enter the second Committee Name: ");
                    String cName2 = s.nextLine();

                    Committee c1 = myCollege.findCommitteeByName(cName1);
                    Committee c2 = myCollege.findCommitteeByName(cName2);

                    if (c1 != null && c2 != null) {
                        System.out.println("Compare by:\n1 - Number of members\n2 - Total articles written by members");
                        int criteria = s.nextInt();
                        s.nextLine();

                        if (criteria == 1) {
                            int res = c1.compareTo(c2);
                            if (res > 0) System.out.println(c1.getCommitteeName() + " has more members.");
                            else if (res < 0) System.out.println(c2.getCommitteeName() + " has more members.");
                            else System.out.println("Both committees have the same number of members.");
                        } else if (criteria == 2) {
                            int res = c1.compareByTotalArticles(c2);
                            if (res > 0) System.out.println(c1.getCommitteeName() + " members have more total articles.");
                            else if (res < 0) System.out.println(c2.getCommitteeName() + " members have more total articles.");
                            else System.out.println("Both committees have the same number of total articles.");
                        } else {
                            System.out.println("Invalid comparison choice.");
                        }
                    } else {
                        System.out.println("Error: One or both committees were not found.");
                    }
                    break;

                case 14:
                    System.out.print("Enter the name of the Committee you want to clone: ");
                    String targetClone = s.nextLine();
                    Committee original = myCollege.findCommitteeByName(targetClone);

                    if (original != null) {
                        try {
                            Committee clonedCommittee = original.cloneCommittee();
                            myCollege.addCommittee(clonedCommittee);
                            System.out.println("Committee successfully cloned! New name: " + clonedCommittee.getCommitteeName());
                        } catch (CommitteeAlreadyExistsException | InvalidChairpersonException | AlreadyMemberException | ChairCannotBeMemberException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Error: Committee not found.");
                    }
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