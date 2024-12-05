
class Employee {
    private int id;
    private String name;
    private double salary;
    private int yoe;

    public Employee(int id, String name,int yoe, double salary) {
        this.id = id;
        this.name = name;
        this.yoe = yoe;
        this.salary=salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getYoe() {
        return yoe;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}