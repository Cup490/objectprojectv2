public class Lecturer {
private String name;
private int id;
private String TypeOfDegree;
private enum  LevelOfDegree {Bachelors, Masters, prof};
private int pay;
private Departments depart;

    public Lecturer(String name, int id, String typeOfDegree, int pay) {
        this.name = name;
        this.id = id;
        TypeOfDegree = typeOfDegree;
        this.pay = pay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfDegree() {
        return TypeOfDegree;
    }

    public void setTypeOfDegree(String typeOfDegree) {
        TypeOfDegree = typeOfDegree;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public Departments getDepart() {
        return depart;
    }

    public void setDepart(Departments depart) {
        this.depart = depart;
    }
}
