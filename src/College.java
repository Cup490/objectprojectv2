import java.io.Serializable;
import java.util.ArrayList;

public class College implements Serializable {
    // תכונות
    private String uniName;
    private ArrayList<Lecturer> lecturers = new ArrayList<>();
    private ArrayList<Committee<?>> committees = new ArrayList<>();
    private ArrayList<Department> departments = new ArrayList<>();

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
        lecturers.add(newLecturer);
    }

    public void addDepartment(Department department) throws DepartmentAlreadyExistsException {
        if (findDepartmentByName(department.getDepartmentName()) != null) {
            throw new DepartmentAlreadyExistsException("A department with this name already exists.");
        }
        departments.add(department);
    }

    public void addCommittee(Committee<?> newCommittee) throws CommitteeAlreadyExistsException {
        if (findCommitteeByName(newCommittee.getCommitteeName()) != null) {
            throw new CommitteeAlreadyExistsException("A committee with this name already exists.");
        }
        committees.add(newCommittee);
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

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void addLecturerToCommittee(String lecturerId, String committeeName) throws LecturerNotFoundException, CommitteeNotFoundException, ChairCannotBeMemberException, AlreadyMemberException {
        Lecturer lecturer = findLecturerById(lecturerId);
        Committee committee = findCommitteeByName(committeeName);
        if (lecturer == null) {
            throw new LecturerNotFoundException("Lecturer ID does not exist.");
        }
        if (committee == null) {
            throw new CommitteeNotFoundException("Committee name does not exist.");
        }

        Class<?> committeeType = committee.getMemberType();
        if (committeeType == Professor.class && !(lecturer instanceof Professor)) {
            throw new IllegalArgumentException("Homogeneity Error: Only Professors can join this committee.");
        } else if (committeeType == Doctor.class && (!(lecturer instanceof Doctor) || lecturer instanceof Professor)) {
            throw new IllegalArgumentException("Homogeneity Error: Only Doctors can join this committee.");
        } else if (committeeType == Lecturer.class && (lecturer instanceof Doctor)) {
            throw new IllegalArgumentException("Homogeneity Error: Only Regular Lecturers (Bachelors/Masters) can join this committee.");
        }

        committee.addMember(lecturer);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
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

    @SuppressWarnings({"unchecked", "rawtypes"})
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
        if (committee.getMemberIndex(newChair.getId()) != -1) {
            committee.removeMember(newChair);
        }
        committee.setChairPerson((Doctor) newChair);
    }

    public double getAveragePay() {
        if (lecturers.isEmpty()) return 0.0;
        double sum = 0.0;
        for (Lecturer l : lecturers) {
            sum += l.getPay();
        }
        return sum / lecturers.size();
    }

    public double getAveragePayPerDepartment(String departmentName) {
        Department department = findDepartmentByName(departmentName);
        if (department == null || department.getLecturers().isEmpty()) return 0.0;
        double sum = 0.0;
        for (Lecturer l : department.getLecturers()) {
            sum += l.getPay();
        }
        return sum / department.getLecturers().size();
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
        for (Lecturer l : lecturers) {
            if (l.getId().equals(lecturerId)) return l;
        }
        return null;
    }

    public Lecturer findLecturerByName(String lecturerName) {
        for (Lecturer l : lecturers) {
            if (l.getLecturerName().equalsIgnoreCase(lecturerName)) return l;
        }
        return null;
    }

    public Committee<?> findCommitteeByName(String committeeName) {
        for (Committee<?> c : committees) {
            if (c.getCommitteeName().equalsIgnoreCase(committeeName)) return c;
        }
        return null;
    }

    public Department findDepartmentByName(String departmentName) {
        for (Department d : departments) {
            if (d.getDepartmentName().equalsIgnoreCase(departmentName)) return d;
        }
        return null;
    }

    // get & set
    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    // הדפסות
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("College Name: ").append(uniName).append("\n");
        sb.append("Number of Departments: ").append(departments.size()).append("\n");
        sb.append("=== Lecturers Breakdown ===\n").append(printLecturers()).append("\n");
        sb.append("=== Committees Breakdown ===\n").append(printCommittees());
        return sb.toString();
    }

    public String printLecturers() {
        if (lecturers.isEmpty()) return "No lecturers yet.";
        StringBuilder print = new StringBuilder();
        for (Lecturer l : lecturers) {
            print.append(l.toString()).append("\n");
        }
        return print.toString();
    }

    public String printCommittees() {
        if (committees.isEmpty()) return "No committees yet.";
        StringBuilder print = new StringBuilder();
        for (Committee<?> c : committees) {
            print.append(c.toString()).append("\n");
        }
        return print.toString();
    }
}