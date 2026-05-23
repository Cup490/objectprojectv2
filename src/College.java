public class College {
    private String uni;
    private int Lecturercount = 0;
    private int committeeCount = 0;
    private Lecturer[] arraylecturer = new Lecturer[1];
    private Commities[] arrayCommittees = new Commities[1];
    public College(String uni) {
        this.uni = uni;
    }

    public boolean isvalid(Lecturer searchLecturer) { // is the lecturer valid
        boolean lecturerbool = false;
        for (int i = 0; i < Lecturercount; i++) {
            if (arraylecturer[i].equals(searchLecturer)) {
                lecturerbool = true;
                break;
            }
        }
        return lecturerbool;
    }
    public boolean isvalid(String name) {
        for (int i = 0; i < committeeCount; i++) {
            if (arrayCommittees[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    public Lecturer findLecturerByName(String name) {
        for (int i = 0; i < Lecturercount; i++) {
            if (arraylecturer[i].getName().equalsIgnoreCase(name)) {
                return arraylecturer[i];
            }
        }
        return null;
    }
    public void addItem(Lecturer newLecturer) {
        if (!isvalid(newLecturer)) {
            if (Lecturercount == arraylecturer.length) {
                doubleArraySize();
            }
            arraylecturer[Lecturercount] = newLecturer;
            Lecturercount++;
        }
    }
    public boolean additem(String committeeName, String chairName) {

        // תנאי 1: בדיקה שאין ועדה קיימת עם אותו השם
        if (isvalid(committeeName)) {
            System.out.println("the committee" + committeeName + "already exists in the college");
            return false;
        }

        // תנאי 2: חיפוש המרצה במכללה לפי השם שנקלט
        Lecturer potentialChair = findLecturerByName(chairName);
        if (potentialChair == null) {
            System.out.println("שגיאה: לא נמצא מרצה במכללה בשם " + chairName);
            return false;
        }

        // תנאי 3: יצירת אובייקט הוועדה הזמני ובדיקת חוקי היו"ר (האם הוא ד"ר?)
        Commities newCommittee = new Commities(committeeName);

        // נשתמש במתודת setChairperson שכתבנו קודם שמחזירה true/false!
        if (!newCommittee.setChairperson(potentialChair, this)) {
            System.out.println("שגיאה: יצירת הוועדה נכשלה מכיוון שהיו\"ר אינו עומד בקריטריונים.");
            return false;
        }

        // אם עברנו את כל השלבים בהצלחה - נגדיל את המערך במידת הצורך ונשמור את הוועדה!
        if (committeeCount == arrayCommittees.length) {
            doubleCommitteeArraySize();
        }

        arrayCommittees[committeeCount] = newCommittee;
        committeeCount++;

        System.out.println("הוועדה '" + committeeName + "' נוצרה בהצלחה עם היו\"ר " + chairName);
        return true;
    }
    private void doubleArraySize() {
        Lecturer[] newArr = new Lecturer[arraylecturer.length * 2];
        for (int i = 0; i < arraylecturer.length; i++) {
            newArr[i] = arraylecturer[i];
        }
        arraylecturer = newArr;
    }
    private void doubleCommitteeArraySize() {
        Commities[] newArr = new Commities[arrayCommittees.length * 2];
        for (int i = 0; i < arrayCommittees.length; i++) {
            newArr[i] = arrayCommittees[i];
        }
        arrayCommittees = newArr;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public int getLecturercount() {
        return Lecturercount;
    }

    public void setLecturercount(int count) {
        this.Lecturercount = count;
    }

    public Lecturer[] getArraylecturer() {
        return arraylecturer;
    }

    public void setArraylecturer(Lecturer[] arraylecturer) {
        this.arraylecturer = arraylecturer;
    }
}