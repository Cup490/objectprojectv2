public class Lecturer {
    private final LevelOfDegree levelOfDegree;
    private String name;
    private String id;
    private String typeOfDegree;
    private static enum LevelOfDegree {Bachelors, Masters, doctor, prof}
    private double pay;
    private Departments depart;

    public Lecturer(String name, String id, String typeOfDegree, double pay,String levelOfDegree) {
        this.setName(name);
        this.setId(id);
        this.typeOfDegree = typeOfDegree;
        this.levelOfDegree = LevelOfDegree.valueOf(levelOfDegree);
        this.pay = pay;
    }
    public boolean IsDoctor() {
    if (this.levelOfDegree == LevelOfDegree.doctor || this.levelOfDegree == LevelOfDegree.prof ) {
        return true;
    }
    return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id != null && id.matches("//d{9}")) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID cannot exceed 9 characters.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != "" && name.matches("[a-zA-Z]+")) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can only have letters.");
        }
    }


    public String getTypeOfDegree() {
        return typeOfDegree;
    }

    public void setTypeOfDegree(String typeOfDegree) {
        this.typeOfDegree = typeOfDegree;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public Departments getDepart() {
        return depart;
    }

    public void setDepart(Departments depart) {
        this.depart = depart;
    }

}
