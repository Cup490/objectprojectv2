import java.util.Scanner;

public static void info(String[] array,int Count) {
    for (int i = 0; i < Count; i++) {
        System.out.println(array[i]);
    }
}

public static boolean isItemExists(String[] arr, int logicalSize, String searchName) {
    boolean bool = false;
    for (int i = 0; i < logicalSize; i++) {
        if (arr[i].equals(searchName)) {
            bool = true;
            break;
        }
    }
    return bool;
}

public static String[] doubleArraySize(String[] arr) {
    String[] newArr = new String[arr.length * 2];
    for (int i = 0; i < arr.length; i++) {
        newArr[i] = arr[i];
    }
    return newArr;
}

public static int addItem(String[] arr, int count, String newName) {
    if (!isItemExists(arr, count, newName)) {
        arr[count] = newName;
        return count + 1;
    }
    return count;
}


public static void main(String[] args) {
    //elad katz, alice
    String[] professorsArray = new String[1];
    String[] boardArray = new String[1];
    String[] departArray = new String[1];
    int boardCount = 0;
    int profCount = 0;
    int departcount = 0;
    Scanner s = new Scanner(System.in);
    String UniName = ("Please Enter the uni's name");
    System.out.println(UniName);
    String Uni = s.nextLine();
    String choices = ("Please choose your option\n " +
            "0 - Exit\n " +
            "1 - Adding a professor\n" +
            "2 - Add a board\n" +
            "3 - Add a department of study\n" +
            "4 - Add a prof to a board\n" +
            "5 - showcase the avg of paychecks of profs in  college\n" +
            "6 - showcase of avg paychecks in a specific department\n" +
            "7 - information about profs\n" +
            "8 - information about boards"
    );

    System.out.println(choices);
    int answer = s.nextInt();
    while (answer != 0) {
        switch (answer) {
            case 0:
                System.out.println("the program has ended :3");
                break;
            case 1:
                Scanner prof1 = new Scanner(System.in);
                String addAProf = ("Please name a professor");
                System.out.println(addAProf);
                String professorName = prof1.nextLine();
                boolean bool = isItemExists(professorsArray, profCount, professorName);
                while (bool == true) {
                    String message = ("Please choose a different name");
                    System.out.println(message);
                    professorName = prof1.nextLine();
                    bool = isItemExists(professorsArray, profCount, professorName);
                }

                if (profCount == professorsArray.length) {
                    professorsArray = doubleArraySize(professorsArray);
                }
                profCount = addItem(professorsArray, profCount, professorName);
                bool = true;
                break;
            case 2:
                Scanner board1 = new Scanner(System.in);
                String addAboard = ("Please name a board");
                System.out.println(addAboard);
                String boardName = board1.nextLine();
                boolean boolBoard = isItemExists(boardArray, boardCount, boardName);
                while(boolBoard == true) {
                    String message1 = ("Please choose a different name");
                    System.out.println(message1);
                    boardName = board1.nextLine();
                    boolBoard = isItemExists(boardArray,boardCount,boardName);
                }

                if (boardCount == boardArray.length) {
                    boardArray = doubleArraySize(boardArray);
                }
                boardCount = addItem(boardArray, boardCount, boardName);
                boolBoard = true;
                break;
            case 3:
                Scanner depart1 = new Scanner(System.in);
                String addAdepart = ("Please name a department");
                System.out.println(addAdepart);
                String DepartName = depart1.nextLine();
                boolean booldepart = isItemExists(departArray,departcount,DepartName);
                while (booldepart == true) {
                    String message2 = ("Please choose a different name");
                    System.out.println(message2);
                    DepartName = depart1.nextLine();
                    booldepart = isItemExists(departArray,departcount,DepartName);
                }
                if (departcount == departArray.length) {
                    departArray = doubleArraySize(departArray);
                }
                departcount = addItem(departArray, departcount, DepartName);
                booldepart = true;
                break;
            case 4:
                Scanner prof2 = new Scanner(System.in);
                Scanner board2 = new Scanner(System.in);
                System.out.println("Pick a professor");
                String professorName2 = prof2.nextLine();
                System.out.println("Pick a board");
                String boardName2 = prof2.nextLine();
                if (!isItemExists(professorsArray,profCount,professorName2)){
                    System.out.println("This professor does not exist in the system");
                }
                else{
                    if(!isItemExists(boardArray,boardCount,boardName2)){
                        System.out.println("This board does not exist");
                    }
                    else{

                    }
                }
                break;
            case 5:

                break;

            case 6:

                break;

            case 7:
                info(professorsArray,profCount);
                break;
            case 8:
                info(boardArray,boardCount);
                break;
        }
        System.out.println(choices);
        answer = s.nextInt();
    }
}
