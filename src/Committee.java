import java.io.Serializable;
import java.util.ArrayList;

public class Committee<T extends Lecturer> implements Comparable<Committee<?>>, Serializable {
    // תכונות
    private String committeeName;
    private Lecturer chairman = null;
    private ArrayList<T> members = new ArrayList<>();
    private Class<T> memberType; // Enforces part 4 generic rules

    // פעולה בונה
    public Committee(String committeeName, Class<T> memberType) {
        this.committeeName = committeeName;
        this.memberType = memberType;
    }

    // פעולות
    public void addMember(T newMember) throws ChairCannotBeMemberException, AlreadyMemberException {
        if (chairman != null && chairman.getId().equals(newMember.getId())) {
            throw new ChairCannotBeMemberException("The chairman cannot be added as a regular member.");
        }
        if (getMemberIndex(newMember.getId()) != -1) {
            throw new AlreadyMemberException("This lecturer is already a member of the committee.");
        }
        members.add(newMember);
        newMember.addCommittee(this);
    }

    public void removeMember(T member) throws NotInCommitteeException {
        int index = getMemberIndex(member.getId());
        if (index == -1) {
            throw new NotInCommitteeException("This lecturer is not a member of the committee.");
        }
        members.remove(index);
        member.removeCommittee(this);
    }

    public int compareByTotalArticles(Committee<?> other) {
        return Integer.compare(this.getTotalArticles(), other.getTotalArticles());
    }

    public Committee<T> cloneCommittee() throws InvalidChairpersonException, AlreadyMemberException, ChairCannotBeMemberException {
        Committee<T> cloned = new Committee<>(this.committeeName + "-new", this.memberType);
        if (this.chairman != null && this.chairman instanceof Doctor) {
            cloned.setChairPerson((Doctor) this.chairman);
        }
        for (T member : members) {
            cloned.addMember(member);
        }
        return cloned;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Committee)) return false;
        Committee<?> other = (Committee<?>) obj;
        return this.committeeName.equalsIgnoreCase(other.getCommitteeName());
    }

    @Override
    public int compareTo(Committee<?> other) {
        return Integer.compare(this.members.size(), other.getMemberCount());
    }

    // פעולות עזר
    public int getMemberIndex(String lecturerId) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equals(lecturerId)) {
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
        for (T member : members) {
            if (member instanceof Doctor) {
                total += ((Doctor) member).getArticlesCount();
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

    public void setChairPerson(Doctor newChairPerson) throws InvalidChairpersonException, AlreadyMemberException {
        if (newChairPerson == null) {
            throw new InvalidChairpersonException("Chairperson cannot be null.");
        }
        this.chairman = newChairPerson;
        newChairPerson.addCommittee(this);
    }

    public ArrayList<T> getMembers() {
        return members;
    }

    public int getMemberCount() {
        return members.size();
    }

    public Class<T> getMemberType() {
        return memberType;
    }

    //הדפסה
    @Override
    public String toString() {
        StringBuilder membersPrint = new StringBuilder();
        for (T member : members) {
            membersPrint.append(member.getLecturerName()).append(", ");
        }
        String finalMembers = (membersPrint.length() > 0) ?
                membersPrint.substring(0, membersPrint.length() - 2) : "No members yet.";
        return "Committee Name: " + committeeName + " [Type: " + memberType.getSimpleName() + "]\n" +
                "Chairperson: " + (chairman != null ? chairman.getLecturerName() : "None") + "\n" +
                "Members: [" + finalMembers + "]\n";
    }
}