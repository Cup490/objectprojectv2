public class Department {
    // תכונות
    private String departmentName;
    private int studentsCount;
    private Lecturer[] lecturers = new Lecturer[1];
    private int lecturersCount = 0;

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
        if (lecturersCount == lecturers.length) {
            doubleLecturersArraySize();
        }
        lecturers[lecturersCount++] = newLecturer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Department)) return false;
        Department other = (Department) obj;
        return this.departmentName.equalsIgnoreCase(other.getDepartmentName());
    }

    // פעולות עזר
    private void doubleLecturersArraySize() {
        Lecturer[] newLecturers = new Lecturer[lecturers.length * 2];
        for (int i = 0; i < lecturersCount; i++) {
            newLecturers[i] = lecturers[i];
        }
        lecturers = newLecturers;
    }

    public int getLecturerIndex(String lecturerId) {
        for (int i = 0; i < lecturersCount; i++) {
            if (lecturers[i].getId().equals(lecturerId)) {
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

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public int getLecturersCount() {
        return lecturersCount;
    }

    // אין פעולות set עבור המערכים מכיוון שהם יכולים להתעדכן רק דרך פעולות ולא על ידי המשתמש
    // אין פעולת set למשתנים הסופרים מכיוון שהם מתעדכנים אוטומטית ע"י הפעולות ושינוי ישיר שלו עלול ליצור חוסר התאמה בין הספירה לתוכן המערך בפועל

    //הדפסה
    @Override
    public String toString() {
        StringBuilder lecturersPrint = new StringBuilder();
        for (int i = 0; i < lecturersCount; i++) {
            lecturersPrint.append(lecturers[i].getLecturerName()).append(", ");
        }
        String finalLecturers = (lecturersPrint.length() > 0) ?
                lecturersPrint.substring(0, lecturersPrint.length() - 2) : "No lecturers yet.";
        return "Department Name: " + departmentName + "\n" +
                "Students Count: " + studentsCount + "\n" +
                "Lecturers: [" + finalLecturers + "]\n";
    }
}