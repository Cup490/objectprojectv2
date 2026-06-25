public class Professor extends Doctor {
    private String grantingBody;

    public Professor(String lecturerName, String id, String typeOfDegree, double pay, String grantingBody) {
        super(lecturerName, id, typeOfDegree, pay, "PROF");
        this.grantingBody=grantingBody;
    }

    public String getGrantingBody() {
        return grantingBody;
    }

    public void setGrantingBody(String grantingBody) {
        this.grantingBody = grantingBody;
    }

    @Override
    public String toString() {
        return super.toString() + "Granting Body: " + grantingBody + "\n";
    }
}
