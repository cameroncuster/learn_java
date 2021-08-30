import java.util.Scanner;

class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the Employee’s full name: ");

		String name = sc.nextLine();

		System.out.print("Enter the Employee’s number: ");

		String ID = sc.nextLine();

		System.out.print("Enter the pay rate per hour: ");

		double hourly_rate = sc.nextDouble();

		System.out.print("Enter the regular hours worked: ");

		double hours_worked = sc.nextDouble();

		System.out.println("\nThank you!\n");


		System.out.println("Here is your paycheck!\n\n\n");


		double gross_pay = hourly_rate * hours_worked;

		double deductions_tax = 0.06 * gross_pay;

		double net_pay = gross_pay - deductions_tax;

		System.out.println("------------------------------------------\n");

		System.out.println("Employee’s name:\t" + name);
		System.out.println("Employee's number:\t" + ID);
		System.out.println("Hourly rate of pay:\t" +
				String.format("%.2f", hourly_rate));
		System.out.println("Hours worked:\t\t" +
				String.format("%.0f", hours_worked) + '\n');

		System.out.println("Total Gross Pay:\t$" +
				String.format("%.2f", gross_pay) + '\n');

		System.out.println("Deductions\nTax (6%):\t\t$" +
				String.format("%.2f", deductions_tax) + '\n');

		System.out.println("Net Pay:\t\t" + String.format("%.2f", net_pay) +
				" Dollars\n");

		System.out.println("------------------------------------------\n\n\n");


		System.out.println("Bye!");

	}

}
