public class Committee {
    // תכונות
    private String committeeName;
    private Lecturer chairman = null;
    private Lecturer[] members = new Lecturer[1];
    private int memberCount = 0;

    // פעולה בונה
    public Committee(String committeeName) {
        this.committeeName = committeeName;
    }

    // פעולות
    public boolean addMember(Lecturer newMember) {
        if (chairman != null && chairman.getId().equals(newMember.getId())) {
            return false;
        }
        if (getMemberIndex(newMember.getId()) != -1) {
            return false;
        }
        if (memberCount == members.length) {
            doubleMembersArraySize();
        }
        members[memberCount++] = newMember;
        newMember.addCommittee(this);
        return true;
    }

    public boolean removeMember(Lecturer member) {
        int index = getMemberIndex(member.getId());
        if (index != -1) {
            members[index] = members[--memberCount];
            members[memberCount] = null;

            member.removeCommittee(this);

            return true;
        }
        return false;
    }

    // פעולות עזר
    private void doubleMembersArraySize() {
        Lecturer[] newMembers = new Lecturer[members.length * 2];
        for (int i = 0; i < memberCount; i++) {
            newMembers[i] = members[i];
        }
        members = newMembers;
    }

    public int getMemberIndex(String lecturerId) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getId().equals(lecturerId)) {
                return i;
            }
        }
        return -1;
    }

    // get & set
    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public Lecturer getChairman() {
        return chairman;
    }

    public boolean setChairPerson(Lecturer newChairPerson) {
        if (newChairPerson != null && newChairPerson.isDoctor()) {
            this.chairman = newChairPerson;
            newChairPerson.addCommittee(this);
            return true;
        }
        return false;
    }

    public Lecturer[] getMembers() {
        return members;
    }

    public int getMemberCount() {
        return memberCount;
    }

    // אין פעולות set עבור המערכים מכיוון שהם יכולים להתעדכן רק דרך פעולות ולא על ידי המשתמש

    //הדפסה
    @Override
    public String toString() {
        StringBuilder membersPrint = new StringBuilder();
        for (int i = 0; i < memberCount; i++) {
            membersPrint.append(members[i].getLecturerName()).append(", ");
        }

        String finalMembers = (membersPrint.length() > 0) ?
                membersPrint.substring(0, membersPrint.length() - 2) : "No members yet.";

        return "Committee Name: " + committeeName + "\n" +
                "Chairperson: " + (chairman != null ? chairman.getLecturerName() : "None") + "\n" +
                "Members: [" + finalMembers + "]\n";
    }
}