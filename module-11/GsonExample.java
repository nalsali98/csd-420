import com.google.gson.Gson;

class Employee {

    String name;
    int id;
    String position;

    Employee(String name, int id, String position) {
        this.name = name;
        this.id = id;
        this.position = position;
    }
}

public class GsonExample {

    public static void main(String[] args) {

        Gson gson = new Gson();

        Employee emp = new Employee("Noor", 101, "Developer");

        String json = gson.toJson(emp);

        System.out.println("Java Object to JSON:");
        System.out.println(json);

        Employee converted = gson.fromJson(json, Employee.class);

        System.out.println("\nJSON to Java Object:");
        System.out.println(converted.name + " " + converted.id + " " + converted.position);
    }
}