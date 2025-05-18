package Employee;

public class Employee {
    private String position;
    private String id;
    private  String name;
    private  String lastName;
    private String location;

    public Employee(String id, String name, String lastName, String position, String location) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.location = location;
    }

    public Employee(){
    }
    //взять должность
    public String getPosition() {
        return position;
    }
    //взять уникальный номер
    public String getId() {
        return id;
    }
    //взять имя
    public String getName() {
        return name;
    }
    //определить должность
    public void setPosition(String position) {
        this.position = position;
    }

    //взять фамилию
    public String getLastName() {
        return lastName;
    }
    //взять место работы
    public String getLocation() {
        return location;
    }
    //определить место работы
    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
