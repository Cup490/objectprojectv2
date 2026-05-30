import java.util.Scanner;
public class aliceandelad   {
public static void info(String[] array,int Count) {
    for (int i = 0; i < Count; i++) {
        System.out.println(array[i]);
    }
}

public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    String choices = ("Please choose your option\n " +
            "0 - Exit\n " +
            "1 - Add Lecturer to college\n" +
            "2 - Add a Committee to college\n" +
            "3 - Add a member to the committee\n" +
            "4 - update a committee's chairperson\n" +
            "5 - remove a member from the committee\n" +
            "6 - add a study department\n" +
            "7 - add lecturer to committee\n" +
            "8 - avg pay of all the lecturers in the college\n" +
            "9 - avg pay of the lecturers in a specific committee" +
            "10 -show the full information about every lecturer" +
            "11 -show the full information about every committee"
    );

    System.out.println(choices);
    int answer = s.nextInt();
    while (answer != 0) {
        switch (answer) {
            case 0:
                System.out.println("the program has ended");
                break;
            case 1:
                System.out.println("What College would you like to add a lecturer to? ");
                String College1 = s.nextLine();

                System.out.println("Enter lecturer name: ");
                String inputName = s.nextLine();

                System.out.print("Enter ID: ");
                String inputId = s.nextLine();

                System.out.print("Enter type of degree: ");
                String inputTypeOfDegree = s.nextLine();

                System.out.print("Enter pay: ");
                double inputPay = s.nextDouble();
                s.nextLine();

                System.out.print("Enter level of degree (e.g., Bachelor's, Masters, Doctor, prof): ");
                String inputLevelOfDegree = s.nextLine();
                Lecturer myLecturer = new Lecturer(inputName, inputId, inputTypeOfDegree, inputPay, inputLevelOfDegree);
                College myCollege = new College(College1);
                if (myCollege.addLecturer(myLecturer)) {
                  System.out.println("Lecturer added successfully");
                }
                else {
                    System.out.println("Lecturer was not saved");
                }
            case 2:
                System.out.println("What College would you like to add a committee to?");
                String College2 = s.nextLine();

                System.out.println("Please name the committee you want to add");
                String myCommitte = s.nextLine();

                System.out.println("name a chairperson");
                String person = s.nextLine();
                String myChairperson  = person.
                College myCollege = College2.addCommittee(myCommitte, myChairperson);

                break;
            case 3:

               break;
            case 4:

                break;
            case 5:

                break;

            case 6:

                break;

            case 7:

                break;
            case 8:

                break;
        }
        System.out.println(choices);
        answer = s.nextInt();
    }
}
}
