public class Committee implements Comparable<Committee> {
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
    public void addMember(Lecturer newMember) throws InvalidActionException {
        if (chairman != null && chairman.getId().equals(newMember.getId())) {
            throw new InvalidActionException(InvalidActionException.CHAIR_CANNOT_BE_MEMBER);
        }
        if (getMemberIndex(newMember.getId()) != -1) {
            throw new InvalidActionException(InvalidActionException.ALREADY_IN_COMMITTEE);
        }
        if (memberCount == members.length) {
            doubleMembersArraySize();
        }
        members[memberCount++] = newMember;
        newMember.addCommittee(this);
    }

    public void removeMember(Lecturer member) throws InvalidActionException {
        int index = getMemberIndex(member.getId());
        if (index == -1) {
            throw new InvalidActionException(InvalidActionException.NOT_IN_COMMITTEE);
        }
        members[index] = members[--memberCount];
        members[memberCount] = null;
        member.removeCommittee(this);
    }

    public int compareByMemberCount(Committee other) {
        return Integer.compare(this.memberCount, other.getMemberCount());
    }

    public int compareByTotalArticles(Committee other) {
        return Integer.compare(this.getTotalArticles(), other.getTotalArticles());
    }

    public Committee cloneCommittee() throws InvalidActionException {
        Committee cloned = new Committee("new-" + this.committeeName);
        if (this.chairman != null && this.chairman instanceof Doctor) {
            cloned.setChairPerson((Doctor) this.chairman);
        }
        for (int i = 0; i < this.memberCount; i++) {
            cloned.addMember(this.members[i]);
        }
        return cloned;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Committee)) return false;
        Committee other = (Committee) obj;
        return this.committeeName.equalsIgnoreCase(other.getCommitteeName());
    }

    @Override
    public int compareTo(Committee other) {
        return Integer.compare(this.memberCount, other.getMemberCount());
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

    public int getTotalArticles() {
        int total = 0;
        if (chairman != null && chairman instanceof Doctor) {
            total += ((Doctor) chairman).getArticlesCount();
        }
        for (int i = 0; i < memberCount; i++) {
            if (members[i] instanceof Doctor) {
                total += ((Doctor) members[i]).getArticlesCount();
            }
        }
        return total;
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

    public void setChairPerson(Doctor newChairPerson) throws InvalidActionException {
        if (newChairPerson == null) {
            throw new InvalidActionException("Chairperson cannot be null.");
        }
        this.chairman = newChairPerson;
        newChairPerson.addCommittee(this);
    }

    public Lecturer[] getMembers() {
        return members;
    }

    public int getMemberCount() {
        return memberCount;
    }

    // אין פעולות set עבור המערכים מכיוון שהם יכולים להתעדכן רק דרך פעולות ולא על ידי המשתמש
    // אין פעולת set למשתנים הסופרים מכיוון שהם מתעדכנים אוטומטית ע"י הפעולות ושינוי ישיר שלו עלול ליצור חוסר התאמה בין הספירה לתוכן המערך בפועל

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