public class Committee {
    private String committeeName;
    private Lecturer chairPerson = null;
    private Lecturer[] members = new Lecturer[1];
    private int logicalSize = 0;

    public Committee(String name) {
        this.committeeName = name;
    }

    public String getName() {
        return committeeName;
    }

    public void setName(String name) {
        this.committeeName = name;
    }

    public Lecturer getChairPerson() {
        return chairPerson;
    }

    public boolean setChairPerson(Lecturer newChairPerson) {
        if (!newChairPerson.isDoctor()) {
            return false;
        } else {
            removeMember(newChairPerson);
            this.chairPerson = newChairPerson;
            return true;
        }
    }

    public Lecturer[] getMembers() {
        return members;
    }

    public void setMembers(Lecturer[] members) {
        this.members = members;
    }

    public int getLogicalSize() {
        return logicalSize;
    }

    public void setLogicalSize(int logicalSize) {
        this.logicalSize = logicalSize;
    }

    public boolean addMember(Lecturer newMember) {
        for (int i = 0; i < this.logicalSize; i++) {
            if (newMember.equals(this.members[i])) {
                return false;
            }
        }

        if (this.logicalSize < this.members.length) {
            this.members[this.logicalSize] = newMember;
            this.logicalSize++;
        } else {
            Lecturer[] newMembers = new Lecturer[this.members.length * 2];
            for (int i = 0; i < this.logicalSize; i++) {
                newMembers[i] = this.members[i];
            }
            newMembers[this.logicalSize] = newMember;
            this.members = newMembers;
            this.logicalSize++;
        }

        newMember.addCommittee(this);
        return true;
    }

    public boolean removeMember(Lecturer member) {
        for (int i = 0; i < this.logicalSize; i++) {
            if (this.members[i].equals(member)) {
                int lastIndex = this.logicalSize - 1;
                this.members[i] = this.members[lastIndex];
                this.members[lastIndex] = null;
                this.logicalSize--;
                member.removeCommittee(this);
                return true;
            }
        }

        return false;
    }

    public String toString() {
        String membersPrint = "";

        for (int i = 0; i < this.logicalSize; i++) {
            membersPrint = membersPrint + this.members[i].getName() + ", ";
        }

        if (!membersPrint.isEmpty()) {
            membersPrint = membersPrint.substring(0, membersPrint.length() - 2);
        } else {
            membersPrint = "No members yet.";
        }

        return "Committee Name: " + this.committeeName + "\n" +
                "Chairperson: " + (this.chairPerson != null ? this.chairPerson.getName() : "None") + "\n" +
                "Members: " + membersPrint + "\n";
    }
}