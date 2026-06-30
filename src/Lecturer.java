public class Lecturer {
    // תכונות
    public enum LevelOfDegree {
        BACHELORS, MASTERS, DOCTOR, PROF
    }
    private String lecturerName;
    private String id;
    private String typeOfDegree;
    private LevelOfDegree levelOfDegree;
    private double pay;
    private Department department;
    private Committee[] committees = new Committee[1];
    private int committeesCount = 0;

    // פעולה בונה
    public Lecturer(String lecturerName, String id, String typeOfDegree, double pay, String levelOfDegree) {
        this.setLecturerName(lecturerName);
        this.setId(id);
        this.typeOfDegree = typeOfDegree;
        this.levelOfDegree = LevelOfDegree.valueOf(levelOfDegree.toUpperCase());
        this.pay = pay;
    }

    // פעולות
    public void addCommittee(Committee committee) throws InvalidActionException {
        if (getCommitteeIndex(committee.getCommitteeName()) != -1) {
            throw new InvalidActionException("Lecturer is already a member of this committee.");
        }
        if (committeesCount == committees.length) {
            doubleCommitteesArraySize();
        }
        committees[committeesCount++] = committee;
    }

    public void removeCommittee(Committee committee) throws InvalidActionException {
        int index = getCommitteeIndex(committee.getCommitteeName());
        if (index == -1) {
            throw new InvalidActionException("Lecturer is not a member of this committee.");
        }
        committees[index] = committees[--committeesCount];
        committees[committeesCount] = null;
    }

    // פעולות עזר
    private void doubleCommitteesArraySize() {
        Committee[] newCommittees = new Committee[committees.length * 2];
        for (int i = 0; i < committeesCount; i++) {
            newCommittees[i] = committees[i];
        }
        committees = newCommittees;
    }

    private int getCommitteeIndex(String committeeName) {
        for (int i = 0; i < committeesCount; i++) {
            if (committees[i].getCommitteeName().equalsIgnoreCase(committeeName)) {
                return i;
            }
        }
        return -1;
    }

    // get & set
    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        if (lecturerName != null && !lecturerName.isEmpty() && lecturerName.matches("[a-zA-Z ]+")) {
            this.lecturerName = lecturerName;
        } else {
            throw new IllegalArgumentException("Name can only have letters.");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id != null && id.matches("\\d{9}")) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID cannot exceed 9 characters.");
        }
    }

    public String getTypeOfDegree() {
        return typeOfDegree;
    }

    public void setTypeOfDegree(String typeOfDegree) {
        this.typeOfDegree = typeOfDegree;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Committee[] getCommittees() {
        return committees;
    }

    public int getCommitteesCount() {
        return committeesCount;
    }

    // אין פעולות set עבור המערכים מכיוון שהם יכולים להתעדכן רק דרך פעולות ולא על ידי המשתמש

    // הדפסה
    @Override
    public String toString() {
        StringBuilder committeesPrint = new StringBuilder();
        for (int i = 0; i < committeesCount; i++) {
            committeesPrint.append(committees[i].getCommitteeName()).append(", ");
        }
        String finalCommittees = (committeesPrint.length() > 0) ?
                committeesPrint.substring(0, committeesPrint.length() - 2) : "No committees yet.";
        return "Lecturer: " + lecturerName + " (ID: " + id + ")\n" +
                "Degree: " + levelOfDegree + " in " + typeOfDegree + "\n" +
                "Salary: " + pay + "\n" +
                "Department: " + ((department != null) ? department.getDepartmentName() : "No Department") + "\n" +
                "Committees: [" + finalCommittees + "]\n";
    }
}