public class College {
    private String uni;
    private int lecturersCount = 0;
    private int committeesCount = 0;
    private int departmentsCount = 0;
    private Lecturer[] lecturers = new Lecturer[1];
    private Committee[] committees = new Committee[1];
    private Department[] departments = new Department[1];
    public College(String uni) {
        this.uni = uni;
    }

    private boolean isValidLecturer(String lecturerId) {
        for (int i = 0; i < this.lecturersCount; i++) {
            if (this.lecturers[i].getId().equalsIgnoreCase(lecturerId)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidCommittee(String committeeName) {
        for (int i = 0; i < this.committeesCount; i++) {
            if (this.committees[i].getCommitteeName().equalsIgnoreCase(committeeName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidDepartment(String departmentName) {
        for (int i = 0; i < this.departmentsCount; i++) {
            if (this.departments[i].getDepartmentName().equalsIgnoreCase(departmentName)) {
                return true;
            }
        }
        return false;
    }

    public Lecturer findLecturerByName(String lecturerName) {
        for (int i = 0; i < this.lecturersCount; i++) {
            if (this.lecturers[i].getLecturerName().equalsIgnoreCase(lecturerName)) {
                return this.lecturers[i];
            }
        }
        return null;
    }

    public Committee findCommitteeByName(String committeeName) {
        for (int i = 0; i < this.committeesCount; i++) {
            if (this.committees[i].getCommitteeName().equalsIgnoreCase(committeeName)) {
                return this.committees[i];
            }
        }
        return null;
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

    public boolean updateChairman(Lecturer lecturer, Committee committee) {
        Committee realCommittee = findCommitteeByName(committee.getCommitteeName());
        if(realCommittee!=null){
            return realCommittee.setChairPerson(lecturer);
        }
        return false;
    }

    public boolean removeMember(Lecturer lecturer, Committee committee) {
        Committee realCommittee = findCommitteeByName(committee.getCommitteeName());
        if(realCommittee!=null){
            return realCommittee.removeMember(lecturer);
        }
        return false;
    }

    public double averagePay(){
        double sum=0.0;
        for(int i = 0; i<this.lecturersCount; i++){
            sum+=this.lecturers[i].getPay();
        }
        return sum/this.lecturersCount;
    }

    public double averagePayPerDepartment(Department department){
        double sum=0.0;
        for(int i = 0; i<department.getLecturersCount(); i++){
            sum+=department.getLecturers()[i].getPay();
        }
        return sum/department.getLecturersCount();
    }

    public boolean addLecturerToDepartment(Lecturer lecturer,Department department){
        if(this.isValidDepartment(department.getDepartmentName()) && lecturer.getDepartment()==null){
             boolean wasAdded = department.addLecturer(lecturer);
             if(wasAdded){
                 lecturer.setDepartment(department);
                 return true;
             }
        }
        return false;
    }

    public boolean addCommittee(String committeeName, String chairName) {
        if (isValidCommittee(committeeName)) {
            return false;
        }

        Lecturer potentialChair = findLecturerByName(chairName);
        if (potentialChair == null) {
            return false;
        }

        Committee newCommittee = new Committee(committeeName);

        if (!newCommittee.setChairPerson(potentialChair)) {
            return false;
        }

        if (this.committeesCount == this.committees.length) {
            doubleCommitteeArraySize();
        }

        this.committees[this.committeesCount] = newCommittee;
        this.committeesCount++;

        return true;
    }


    private void doubleLecturerArraySize() {
        Lecturer[] newLecturers = new Lecturer[this.lecturers.length * 2];
        for (int i = 0; i < this.lecturers.length; i++) {
            newLecturers[i] = this.lecturers[i];
        }
        this.lecturers = newLecturers;
    }

    private void doubleCommitteeArraySize() {
        Committee[] newArr = new Committee[this.committees.length * 2];
        for (int i = 0; i < this.committees.length; i++) {
            newArr[i] = this.committees[i];
        }
        this.committees = newArr;
    }

    private void doubleDepartmentArraySize() {
        Department[] newArr = new Department[this.departments.length * 2];
        for (int i = 0; i < this.departments.length; i++) {
            newArr[i] = this.departments[i];
        }
        this.departments = newArr;
    }

    public boolean addDepartment(Department department) {
        if(isValidDepartment(department.getDepartmentName())){
           return false;
        }

        if (this.departmentsCount == this.departments.length) {
            this.doubleDepartmentArraySize();
        }

        this.departments[this.departmentsCount] = department;
        this.departmentsCount++;
        return true;
    }

    public boolean addLecturerToCommittee(String lecturerName, String committeeName){
        if(findLecturerByName(lecturerName) != null && isValidCommittee(committeeName)){
            Lecturer lecturer = findLecturerByName(lecturerName);
            Committee committee = findCommitteeByName(committeeName);
            return committee.addMember(lecturer);
        }
        return false;
    }

    public String getUni() {
        return this.uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public int getLecturersCount() {
        return this.lecturersCount;
    }

    public void setLecturersCount(int count) {
        this.lecturersCount = count;
    }

    public Lecturer[] getLecturers() {
        return this.lecturers;
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    public String printLecturers(){
        String lecturersPrint = "";
        for(int i = 0; i<this.lecturersCount; i++){
            lecturersPrint = lecturersPrint + this.lecturers[i].toString() + "\n";
        }
        if (!lecturersPrint.isEmpty()) {
            return lecturersPrint;
        } else {
            return "No lecturers yet.";
        }
    }

    public String printCommittees(){
        String committeesPrint = "";
        for(int i = 0; i<this.committeesCount; i++){
            committeesPrint = committeesPrint + this.committees[i].toString() + "\n";
        }
        if (!committeesPrint.isEmpty()) {
            return committeesPrint;
        } else {
            return "No committees yet.";
        }
    }
}