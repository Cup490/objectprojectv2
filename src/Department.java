import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Serializable {
    // תכונות
    private String departmentName;
    private int studentsCount;
    private ArrayList<Lecturer> lecturers = new ArrayList<>();

    // פעולה בונה
    public Department(String departmentName, int studentsCount) {
        this.departmentName = departmentName;
        this.studentsCount = studentsCount;
    }

    // פעולות
    public void addLecturer(Lecturer newLecturer) throws AlreadyInDepartmentException {
        if (getLecturerIndex(newLecturer.getId()) != -1) {
            throw new AlreadyInDepartmentException("This lecturer is already assigned to a department.");
        }
        lecturers.add(newLecturer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Department)) return false;
        Department other = (Department) obj;
        return this.departmentName.equalsIgnoreCase(other.getDepartmentName());
    }

    // פעולות עזר
    public int getLecturerIndex(String lecturerId) {
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i).getId().equals(lecturerId)) {
                return i;
            }
        }
        return -1;
    }

    // get & set
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public int getLecturersCount() {
        return lecturers.size();
    }

    //הדפסה
    @Override
    public String toString() {
        StringBuilder lecturersPrint = new StringBuilder();
        for (Lecturer lecturer : lecturers) {
            lecturersPrint.append(lecturer.getLecturerName()).append(", ");
        }
        String finalLecturers = (lecturersPrint.length() > 0) ?
                lecturersPrint.substring(0, lecturersPrint.length() - 2) : "No lecturers yet.";
        return "Department Name: " + departmentName + "\n" +
                "Students Count: " + studentsCount + "\n" +
                "Lecturers: [" + finalLecturers + "]\n";
    }
}