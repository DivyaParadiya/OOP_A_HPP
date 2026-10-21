
public class PayrollDriver {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Amit", 101, 40000),
            new PartTime("Neha", 102, 80, 200),
            new Intern("Rahul", 103, 10000)
        };

        double total = 0;

        for (Employee e : employees) {
            double salary = e.monthlySalary();

            System.out.println("Name: " + e.name);
            System.out.println("ID: " + e.id);
            System.out.println("Monthly salary: " + salary);

            if (e instanceof Intern)
                System.out.println("Note: Intern employee");

            total += salary;
            System.out.println();
        }

        System.out.println("Total payroll: " + total);
    }
}
