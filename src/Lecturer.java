public class Lecturer {
    private enum LevelOfDegree {
        BACHELORS, MASTERS, DOCTOR, PROF
    }
    private String lecturerName;
    private String id;
    private String typeOfDegree;
    private LevelOfDegree levelOfDegree;
    private double pay;
    private Department department;
    private int committeesCount = 0;
    private Committee[] committees = new Committee[1];

    public Lecturer(String lecturerName, String id, String typeOfDegree, double pay, String levelOfDegree) {
        this.setLecturerName(lecturerName);
        this.setId(id);
        this.typeOfDegree = typeOfDegree;
        this.levelOfDegree = LevelOfDegree.valueOf(levelOfDegree.toUpperCase());
        this.pay = pay;
    }

    public boolean isDoctor() {
        return this.levelOfDegree == LevelOfDegree.DOCTOR || this.levelOfDegree == LevelOfDegree.PROF;
    }

    private int getCommitteeIndex(String committeeName){
        for(int i = 0; i<this.committeesCount; i++){
            if(this.committees[i].getCommitteeName().equalsIgnoreCase(committeeName)){
                return i;
            }
        }
        return -1;
    }

    public boolean addCommittee(Committee committee) {
        if (getCommitteeIndex(committee.getCommitteeName())!=-1) {
            return false;
        }

        if (this.committeesCount == this.committees.length){
            Committee[] newCommittees = new Committee[this.committees.length * 2];
            for (int i = 0; i < this.committeesCount; i++) {
                newCommittees[i] = this.committees[i];
            }
            this.committees = newCommittees;
        }

        this.committees[this.committeesCount] = committee;
        this.committeesCount++;
        return true;
    }

    public boolean removeCommittee(Committee committee) {
        int index=getCommitteeIndex(committee.getCommitteeName());
        if(index!=-1){
            int lastIndex=this.committeesCount -1;
            this.committees[index]=this.committees[lastIndex];
            this.committees[lastIndex]=null;
            this.committeesCount--;
            return true;
        }
        return false;
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

    public void setCommittees(Committee[] committees) {
        this.committees = committees;
    }

    @Override
    public String toString() {
        String committeesPrint = "";

        for (int i = 0; i < this.committeesCount; i++) {
            committeesPrint = committeesPrint + this.committees[i].getCommitteeName() + ", ";
        }

        if (!committeesPrint.isEmpty()) {
            committeesPrint = committeesPrint.substring(0, committeesPrint.length() - 2);
        } else {
            committeesPrint = "No committees yet.";
        }

        return "Lecturer: " + this.lecturerName + " (ID: " + this.id + ")\n" +
                "Degree: " + this.levelOfDegree + " in " + this.typeOfDegree + "\n" +
                "Salary: " + this.pay + "\n" +
                "Department: " + ((this.department != null) ? this.department.getDepartmentName() : "No Department") + "\n" +
                "Committees: [" + committeesPrint + "]\n";
    }
}