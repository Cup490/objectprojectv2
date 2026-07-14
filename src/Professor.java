import java.io.Serializable;

public class Professor extends Doctor implements Serializable {
    // תכונות
    private String grantingBody;

    // פעולה בונה
    public Professor(String lecturerName, String id, String typeOfDegree, double pay, String grantingBody) throws InvalidLecturerNameException, InvalidLecturerIdException {
        super(lecturerName, id, typeOfDegree, pay, "PROF");
        this.grantingBody = grantingBody;
    }

    // פעולות
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Professor)) return false;
        Professor other = (Professor) obj;
        return this.grantingBody.equalsIgnoreCase(other.getGrantingBody());
    }

    // get & set
    public String getGrantingBody() {
        return grantingBody;
    }

    public void setGrantingBody(String grantingBody) {
        this.grantingBody = grantingBody;
    }

    // הדפסה
    @Override
    public String toString() {
        return super.toString() + "Granting Body: " + grantingBody + "\n";
    }
}