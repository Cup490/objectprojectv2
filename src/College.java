public class College {
    private String uni;
    private int lecturerCount = 0;
    private int committeeCount = 0;
    private int departmentCount = 0;
    private Lecturer[] lecturers = new Lecturer[1];
    private Committee[] committees = new Committee[1];
    private Department[] departments = new Department[1];

    public College(String uni) {
        this.uni = uni;
    }

    public boolean isValid(Lecturer searchLecturer) {
        boolean isLecturerValid = false;
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i].equals(searchLecturer)) {
                isLecturerValid = true;
                break;
            }
        }
        return isLecturerValid;
    }

    public boolean isValid(String name) {
        for (int i = 0; i < committeeCount; i++) {
            if (committees[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Lecturer findLecturerByName(String name) {
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i].getName().equalsIgnoreCase(name)) {
                return lecturers[i];
            }
        }
        return null;
    }

    public boolean addLecturer(Lecturer newLecturer) {
        if (!isValid(newLecturer)) {
            if (lecturerCount == lecturers.length) {
                doubleLecturerArraySize();
            }
            lecturers[lecturerCount] = newLecturer;
            lecturerCount++;
            return true;
        }
        return false;
    }

    public boolean addCommittee(String committeeName, String chairName) {
        if (isValid(committeeName)) {
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

        if (committeeCount == committees.length) {
            doubleCommitteeArraySize();
        }

        committees[committeeCount] = newCommittee;
        committeeCount++;

        return true;
    }

    private void doubleLecturerArraySize() {
        Lecturer[] newArr = new Lecturer[lecturers.length * 2];
        for (int i = 0; i < lecturers.length; i++) {
            newArr[i] = lecturers[i];
        }
        lecturers = newArr;
    }

    private void doubleCommitteeArraySize() {
        Committee[] newArr = new Committee[committees.length * 2];
        for (int i = 0; i < committees.length; i++) {
            newArr[i] = committees[i];
        }
        committees = newArr;
    }

    public boolean addDepartment(Department newDepartment){

    }

    public boolean addLecturerToCommittee(String lecturerName, String committeeName){
        
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public int getLecturerCount() {
        return lecturerCount;
    }

    public void setLecturerCount(int count) {
        this.lecturerCount = count;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }
}