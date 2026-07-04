public class College {
    // תכונות
    private String uniName;
    private Lecturer[] lecturers = new Lecturer[1];
    private Committee[] committees = new Committee[1];
    private Department[] departments = new Department[1];
    private int lecturersCount = 0;
    private int committeesCount = 0;
    private int departmentsCount = 0;

    // פעולה בונה
    public College(String uniName) {
        this.uniName = uniName;
    }

    // פעולות
    public void addLecturer(Lecturer newLecturer) throws LecturerAlreadyExistsException {
        if (findLecturerByName(newLecturer.getLecturerName()) != null ||
                findLecturerById(newLecturer.getId()) != null) {
            throw new LecturerAlreadyExistsException("A lecturer with this name or ID already exists.");
        }
        if (lecturersCount == lecturers.length) {
            doubleLecturerArraySize();
        }
        lecturers[lecturersCount++] = newLecturer;
    }

    public void addDepartment(Department department) throws DepartmentAlreadyExistsException {
        if (findDepartmentByName(department.getDepartmentName()) != null) {
            throw new DepartmentAlreadyExistsException("A department with this name already exists.");
        }
        if (departmentsCount == departments.length) {
            doubleDepartmentArraySize();
        }
        departments[departmentsCount++] = department;
    }

    public void addCommittee(String committeeName, String chairId) throws CommitteeAlreadyExistsException, InvalidChairpersonException, LecturerNotFoundException, AlreadyMemberException {
        if (findCommitteeByName(committeeName) != null) {
            throw new CommitteeAlreadyExistsException("A committee with this name already exists.");
        }
        Lecturer potentialChair = findLecturerById(chairId);
        if (potentialChair == null) {
            throw new LecturerNotFoundException("Lecturer ID not found.");
        }
        if (!(potentialChair instanceof Doctor)) {
            throw new InvalidChairpersonException("The chairperson must be at least a Doctor or Professor.");
        }
        Committee newCommittee = new Committee(committeeName);
        newCommittee.setChairPerson((Doctor) potentialChair);
        if (committeesCount == committees.length) {
            doubleCommitteeArraySize();
        }
        committees[committeesCount++] = newCommittee;
    }

    public void addCommittee(Committee newCommittee) throws CommitteeAlreadyExistsException {
        if (findCommitteeByName(newCommittee.getCommitteeName()) != null) {
            throw new CommitteeAlreadyExistsException("A committee with this name already exists.");
        }
        if (committeesCount == committees.length) {
            doubleCommitteeArraySize();
        }
        committees[committeesCount++] = newCommittee;
    }

    public void addLecturerToDepartment(String lecturerId, String departmentName) throws LecturerNotFoundException, DepartmentNotFoundException, AlreadyInDepartmentException {
        Lecturer lecturer = findLecturerById(lecturerId);
        Department department = findDepartmentByName(departmentName);
        if (lecturer == null) {
            throw new LecturerNotFoundException("Lecturer ID does not exist.");
        }
        if (department == null) {
            throw new DepartmentNotFoundException("Department name does not exist.");
        }
        if (lecturer.getDepartment() != null) {
            throw new AlreadyInDepartmentException("Lecturer is already in a department.");
        }
        department.addLecturer(lecturer);
        lecturer.setDepartment(department);
    }

    public void addLecturerToCommittee(String lecturerId, String committeeName) throws LecturerNotFoundException, CommitteeNotFoundException, ChairCannotBeMemberException, AlreadyMemberException {
        Lecturer lecturer = findLecturerById(lecturerId);
        Committee committee = findCommitteeByName(committeeName);
        if (lecturer == null) {
            throw new LecturerNotFoundException("Lecturer ID does not exist.");
        }
        if (committee == null) {
            throw new CommitteeNotFoundException("Committee name does not exist.");
        }
        committee.addMember(lecturer);
    }

    public void removeMemberFromCommittee(String lecturerId, String committeeName) throws LecturerNotFoundException, CommitteeNotFoundException, NotInCommitteeException {
        Lecturer lecturer = findLecturerById(lecturerId);
        Committee committee = findCommitteeByName(committeeName);
        if (lecturer == null) {
            throw new LecturerNotFoundException("Lecturer ID does not exist.");
        }
        if (committee == null) {
            throw new CommitteeNotFoundException("Committee name does not exist.");
        }
        committee.removeMember(lecturer);
    }

    public void updateChairman(String lecturerId, String committeeName) throws LecturerNotFoundException, CommitteeNotFoundException, InvalidChairpersonException, NotInCommitteeException, AlreadyMemberException {
        Lecturer newChair = findLecturerById(lecturerId);
        Committee committee = findCommitteeByName(committeeName);
        if (newChair == null) {
            throw new LecturerNotFoundException("Lecturer ID does not exist.");
        }
        if (committee == null) {
            throw new CommitteeNotFoundException("Committee name does not exist.");
        }
        if (!(newChair instanceof Doctor)) {
            throw new InvalidChairpersonException("The lecturer is not a Doctor.");
        }
        if (committee.getChairman() != null) {
            committee.getChairman().removeCommittee(committee);
        }
        committee.setChairPerson((Doctor) newChair);
    }

    public double getAveragePay() {
        if (lecturersCount == 0) return 0.0;
        double sum = 0.0;
        for (int i = 0; i < lecturersCount; i++) {
            sum += lecturers[i].getPay();
        }
        return sum / lecturersCount;
    }

    public double getAveragePayPerDepartment(String departmentName) {
        Department department = findDepartmentByName(departmentName);
        if (department == null || department.getLecturersCount() == 0) return 0.0;
        double sum = 0.0;
        for (int i = 0; i < department.getLecturersCount(); i++) {
            sum += department.getLecturers()[i].getPay();
        }
        return sum / department.getLecturersCount();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof College)) return false;
        College other = (College) obj;
        return this.uniName.equalsIgnoreCase(other.getUniName());
    }

    // פעולות עזר
    public Lecturer findLecturerById(String lecturerId) {
        for (int i = 0; i < lecturersCount; i++) {
            if (lecturers[i].getId().equals(lecturerId)) {
                return lecturers[i];
            }
        }
        return null;
    }

    public Lecturer findLecturerByName(String lecturerName) {
        for (int i = 0; i < lecturersCount; i++) {
            if (lecturers[i].getLecturerName().equalsIgnoreCase(lecturerName)) {
                return lecturers[i];
            }
        }
        return null;
    }

    public Committee findCommitteeByName(String committeeName) {
        for (int i = 0; i < committeesCount; i++) {
            if (committees[i].getCommitteeName().equalsIgnoreCase(committeeName)) {
                return committees[i];
            }
        }
        return null;
    }

    public Department findDepartmentByName(String departmentName) {
        for (int i = 0; i < departmentsCount; i++) {
            if (departments[i].getDepartmentName().equalsIgnoreCase(departmentName)) {
                return departments[i];
            }
        }
        return null;
    }

    private void doubleLecturerArraySize() {
        Lecturer[] newArr = new Lecturer[lecturers.length * 2];
        for (int i = 0; i < lecturersCount; i++) newArr[i] = lecturers[i];
        lecturers = newArr;
    }

    private void doubleCommitteeArraySize() {
        Committee[] newArr = new Committee[committees.length * 2];
        for (int i = 0; i < committeesCount; i++) newArr[i] = committees[i];
        committees = newArr;
    }

    private void doubleDepartmentArraySize() {
        Department[] newArr = new Department[departments.length * 2];
        for (int i = 0; i < departmentsCount; i++) newArr[i] = departments[i];
        departments = newArr;
    }

    // get & set
    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    // אין פעולות set עבור המערכים מכיוון שהם יכולים להתעדכן רק דרך פעולות ולא על ידי המשתמש
    // אין פעולת set למשתנים הסופרים מכיוון שהם מתעדכנים אוטומטית ע"י הפעולות ושינוי ישיר שלו עלול ליצור חוסר התאמה בין הספירה לתוכן המערך בפועל

    // הדפסות
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("College Name: ").append(uniName).append("\n");
        sb.append("Number of Departments: ").append(departmentsCount).append("\n");
        sb.append("=== Lecturers Breakdown ===\n").append(printLecturers()).append("\n");
        sb.append("=== Committees Breakdown ===\n").append(printCommittees());
        return sb.toString();
    }

    public String printLecturers() {
        if (lecturersCount == 0) return "No lecturers yet.";
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < lecturersCount; i++) {
            print.append(lecturers[i].toString()).append("\n");
        }
        return print.toString();
    }

    public String printCommittees() {
        if (committeesCount == 0) return "No committees yet.";
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < committeesCount; i++) {
            print.append(committees[i].toString()).append("\n");
        }
        return print.toString();
    }
}