public class Committee {
    private String committeeName;
    private Lecturer chairman = null;
    private Lecturer[] members = new Lecturer[1];
    private int memberCount = 0;

    public Committee(String name) {
        this.committeeName = name;
    }

    public boolean addMember(Lecturer newMember) {
        if (this.chairman != null && this.chairman.getId().equals(newMember.getId())){
            return false;
        }
        else {
            for (int i = 0; i < this.memberCount; i++) {
                if (newMember.getId().equals(this.members[i].getId())) {
                    return false;
                }
            }

            if (this.memberCount < this.members.length) {
                this.members[this.memberCount] = newMember;
                this.memberCount++;
            }
            else {
                Lecturer[] newMembers = new Lecturer[this.members.length * 2];
                for (int i = 0; i < this.memberCount; i++) {
                    newMembers[i] = this.members[i];
                }
                newMembers[this.memberCount] = newMember;
                this.members = newMembers;
                this.memberCount++;
            }

            newMember.addCommittee(this);
            return true;
        }
    }

    public boolean removeMember(Lecturer member) {
        for (int i = 0; i < this.memberCount; i++) {
            if (this.members[i].getId().equals(member.getId())) {
                int lastIndex = this.memberCount - 1;
                this.members[i] = this.members[lastIndex];
                this.members[lastIndex] = null;
                this.memberCount--;
                member.removeCommittee(this);
                return true;
            }
        }

        return false;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setName(String name) {
        this.committeeName = name;
    }

    public Lecturer getChairman() {
        return chairman;
    }

    public boolean setChairPerson(Lecturer newChairPerson) {
        if (!newChairPerson.isDoctor()) {
            return false;
        } else {
            removeMember(newChairPerson);
            newChairPerson.addCommittee(this);
            this.chairman = newChairPerson;
            return true;
        }
    }

    public Lecturer[] getMembers() {
        return members;
    }

    public void setMembers(Lecturer[] members) {
        this.members = members;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    @Override
    public String toString() {
        String membersPrint = "";

        for (int i = 0; i < this.memberCount; i++) {
            membersPrint = membersPrint + this.members[i].getLecturerName() + ", ";
        }

        if (!membersPrint.isEmpty()) {
            membersPrint = membersPrint.substring(0, membersPrint.length() - 2);
        } else {
            membersPrint = "No members yet.";
        }

        return "Committee Name: " + this.committeeName + "\n" +
                "Chairperson: " + (this.chairman != null ? this.chairman.getLecturerName() : "None") + "\n" +
                "Members: " + membersPrint + "\n";
    }
}