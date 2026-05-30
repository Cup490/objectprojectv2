public class Department {
    private String departmentName;
    private int studentsCount;
    private int lecturersCount = 0;
    private Lecturer[] lecturers = new Lecturer[1];

    public Department(String departmentName, int studentsCount){
        this.departmentName = departmentName;
        this.studentsCount = studentsCount;
    }

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

    public int getLecturersCount() {
        return lecturersCount;
    }

    public void setLecturersCount(int lecturersCount) {
        this.lecturersCount = lecturersCount;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    private void doubleLecturerArraySize() {
        Lecturer[] newLecturers = new Lecturer[this.lecturers.length * 2];
        for (int i = 0; i < this.lecturers.length; i++) {
            newLecturers[i] = this.lecturers[i];
        }
        this.lecturers = newLecturers;
    }

    public boolean isValidLecturer(String lecturerId) {
        for (int i = 0; i < this.lecturersCount; i++) {
            if (this.lecturers[i].getId().equalsIgnoreCase(lecturerId)) {
                return true;
            }
        }
        return false;
    }

    public boolean addLecturer(Lecturer newLecturer) {
        if (!isValidLecturer(newLecturer.getId())) {
            if (this.lecturersCount == this.lecturers.length) {
                doubleLecturerArraySize();
            }
            this.lecturers[this.lecturersCount] = newLecturer;
            this.lecturersCount++;
            return true;
        }
        return false;
    }
}
