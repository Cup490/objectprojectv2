import java.io.Serializable;
import java.util.ArrayList;

public class Lecturer implements Serializable {
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
    private ArrayList<Committee<?>> committees = new ArrayList<>();

    // פעולה בונה
    public Lecturer(String lecturerName, String id, String typeOfDegree, double pay, String levelOfDegree) throws InvalidLecturerNameException, InvalidLecturerIdException {
        this.setLecturerName(lecturerName);
        this.setId(id);
        this.typeOfDegree = typeOfDegree;
        this.levelOfDegree = LevelOfDegree.valueOf(levelOfDegree.toUpperCase());
        this.pay = pay;
    }

    // פעולות
    public void addCommittee(Committee<?> committee) throws AlreadyMemberException {
        if (getCommitteeIndex(committee.getCommitteeName()) != -1) {
            throw new AlreadyMemberException("Lecturer is already a member of this committee.");
        }
        committees.add(committee);
    }

    public void removeCommittee(Committee<?> committee) throws NotInCommitteeException {
        int index = getCommitteeIndex(committee.getCommitteeName());
        if (index == -1) {
            throw new NotInCommitteeException("Lecturer is not a member of this committee.");
        }
        committees.remove(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Lecturer other = (Lecturer) obj;
        return this.id.equals(other.getId());
    }

    // פעולות עזר
    private int getCommitteeIndex(String committeeName) {
        for (int i = 0; i < committees.size(); i++) {
            if (committees.get(i).getCommitteeName().equalsIgnoreCase(committeeName)) {
                return i;
            }
        }
        return -1;
    }

    // get & set
    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) throws InvalidLecturerNameException {
        if (lecturerName != null && !lecturerName.isEmpty() && lecturerName.matches("[a-zA-Z ]+")) {
            this.lecturerName = lecturerName;
        } else {
            throw new InvalidLecturerNameException("Name can only have letters.");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws InvalidLecturerIdException {
        if (id != null && id.matches("\\d{9}")) {
            this.id = id;
        } else {
            throw new InvalidLecturerIdException("ID must be exactly 9 digits.");
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

    public ArrayList<Committee<?>> getCommittees() {
        return committees;
    }

    public int getCommitteesCount() {
        return committees.size();
    }

    // הדפסה
    @Override
    public String toString() {
        StringBuilder committeesPrint = new StringBuilder();
        for (Committee<?> committee : committees) {
            committeesPrint.append(committee.getCommitteeName()).append(", ");
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