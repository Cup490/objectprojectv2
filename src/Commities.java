public class Commities {
    private String Commimtiesname;
    private Lecturer chairperson;
    private Lecturer[] members;
    private int count;


    public Commities(String name) {
       this.Commimtiesname = name;
       this.members = new Lecturer[1];
       this.count = 0;
       this.chairperson = null;
    }

    public String getName() {
        return Commimtiesname;
    }

    public void setName(String name) {

        this.Commimtiesname = name;
    }

    public Lecturer getChairperson() {
        return chairperson;
    }

    public boolean setChairperson(Lecturer chairperson,College uni) {
    if (!chairperson.IsDoctor()) {
        System.out.println("the chairperson must be atleast a doctor");
        return false;
    }
    if (!uni.isvalid(chairperson)) {
        System.out.println("Error: The chairperson cannot be a regular member of the committee!");
        return false;
    }
        this.chairperson = chairperson;
        return true;
    }

    public Lecturer[] getMembers() {
        return members;
    }

    public void setMembers(Lecturer[] members) {
        this.members = members;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
