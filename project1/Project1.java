/*
   - Project 1
   - Cameron Custer
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.text.NumberFormat;

class Project1 {

	public static void main(String[] args) {

		System.out.println("\t\t\t\tWelcome to my Personal Management Program");
		System.out.println();

		University university = new University();
		while (true) {

			// menu
			System.out.println("Choose one of the options:");
			System.out.println();
			System.out.println("1-Enter the information of the faculty member");
			System.out.println("2-Enter the information of the two students");
			System.out.println("3-Print tuition invoice");
			System.out.println("4-Print faculty information");
			System.out.println("5-Enter the information of the staff member");
			System.out.println("6-Print the information of the staff member");
			System.out.println("7-Exit Program");
			System.out.println();
			System.out.print("\tEnter your selection: ");

			// capture option
			int option;
			try {
				option = (new Scanner(System.in)).nextInt();
			}
			catch (Exception e) {
				System.out.println();
				System.out.println("Invalid entry-please try again");
				System.out.println();
				continue;
			}
			System.out.println();

			switch (option) {
				case 1:
					System.out.println("Enter faculty info:");

					System.out.print("\tName of the faculty: ");
					String name = (new Scanner(System.in)).nextLine().trim();
					System.out.println();
					System.out.println();

					System.out.print("\tID: ");
					String id = (new Scanner(System.in)).nextLine().trim();
					System.out.println();
					System.out.println();

					String rank;
					do {

						System.out.print("\tRank: ");
						rank = (new Scanner(System.in)).nextLine().toLowerCase().trim();
						rank = rank.substring(0, 1).toUpperCase() + rank.substring(1);

						if (!Faculty.isValidRank(rank))
							System.out.println("\t\t\"" + rank + "\" is invalid");

						System.out.println();

					} while (!Faculty.isValidRank(rank));
					System.out.println();

					String department;
					do {

						System.out.print("\tDepartment: ");
						department = (new Scanner(System.in)).nextLine().toLowerCase().trim();
						department = department.substring(0, 1).toUpperCase() + department.substring(1);

						if (!Faculty.isValidDepartment(department))
							System.out.println("\t\t\"" + department + "\" is invalid");

						System.out.println();

					} while (!Faculty.isValidDepartment(department));
					System.out.println();

					university.faculty.add(new Faculty(name, id, department, rank));

					System.out.println("Faculty successfully added!");

					break;

				case 2:
					if (university.students.size() == 2) {
						System.out.println("You already have two students filled in. Do you want to update their information?");
						System.out.print("Yes or No: ");
						String choice = (new Scanner(System.in)).nextLine().toLowerCase().trim();
						if (choice.equals("no"))
							break;
					}
					for (int student = 1; student <= 2; student++) {
						System.out.println("Enter student " + student + " info:");
						university.students.add(Student.readStudent());
						System.out.println("Thanks!");
						System.out.println();
					}
					break;

				case 3:
					if (university.students.size() == 0)
						System.out.println("No students registered.");
					else {
						assert university.students.size() == 2;
						System.out.print("Which student? Enter 1 " + university.students.get(0).getName() + " or Enter 2 " + university.students.get(1).getName() + "? ");
						int studentToPrint = (new Scanner(System.in)).nextInt();
						System.out.println(university.students.get(studentToPrint - 1).getTuitionInvoice());
					}
					break;

				case 4:
					if (university.faculty.isEmpty())
						System.out.println("Sorry! No Faculty member entered yet");
					System.out.println();
					for (Faculty faculty : university.faculty)
						System.out.println(faculty);
					break;

				case 5:
					System.out.println();
					university.staff.add(Staff.readStaff());
					System.out.println();
					System.out.println("Staff member added!");
					System.out.println();
					break;

				case 6:
					if (university.staff.isEmpty())
						System.out.println("Sorry! No Staff member entered yet");
					System.out.println();
					for (Staff staff : university.staff)
						System.out.println(staff);
					break;

				case 7:
					System.out.println("\tGoodbye!");
					return;

				default:
					System.out.println();
					System.out.println("Invalid entry-please try again");
					System.out.println();
			}

			System.out.println();

		}

	}

}

class University {

	ArrayList<Student> students;
	ArrayList<Faculty> faculty;
	ArrayList<Staff> staff;

	public University() {
		students = new ArrayList<Student>();
		faculty = new ArrayList<Faculty>();
		staff = new ArrayList<Staff>();
	}

}

class Personal {

	private String name;
	private String id;

	public Personal(String name, String id) {
		setName(name);
		setId(id);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}

class Student extends Personal {

	private double gpa;
	private int creditHours;

	public Student(String name, String id, double gpa, int creditHours) {
		super(name, id);
		setGpa(gpa);
		setCreditHours(creditHours);
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public double getGpa() {
		return gpa;
	}

	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	public int getCreditHours() {
		return creditHours;
	}

	public String getTuitionInvoice() {
		double totalPayment = 52.00 + 236.45 * creditHours;
		double discount = 0.0;
		if (gpa >= 3.85)
			discount = 15 * (totalPayment / 100.0);
		totalPayment -= discount;

		String tuitionInvoice = "";
		tuitionInvoice += "---------------------------------------------------------------------------\n";
		tuitionInvoice += getName() + "\t\t\t\t" + getId() + "\n";
		tuitionInvoice += "Credit Hours: " + creditHours + " ($236.45/credit hour)\n";
		tuitionInvoice += "Fees: $52\n";
		tuitionInvoice += "\n";
		tuitionInvoice +=
			"Total payment (after discount): $" +
			NumberFormat.getCurrencyInstance().format(totalPayment) +
			"\t\t($" +
			NumberFormat.getCurrencyInstance().format(discount) +
			" discount applied)\n";
		tuitionInvoice += "---------------------------------------------------------------------------\n";

		return tuitionInvoice;
	}

	public static Student readStudent() {
		System.out.print("\tName of Student: ");
		String name = (new Scanner(System.in)).nextLine().trim();
		System.out.println();

		System.out.print("\tID: ");
		String id = (new Scanner(System.in)).nextLine().trim();
		System.out.println();

		System.out.print("\tGpa: ");
		double Gpa = (new Scanner(System.in)).nextDouble();
		System.out.println();

		System.out.print("\tCredit hours: ");
		int creditHours = (new Scanner(System.in)).nextInt();
		System.out.println();

		return new Student(name, id, Gpa, creditHours);
	}

}

class Employee extends Personal {

	private String department;

	public Employee(String name, String id, String department) {
		super(name, id);
		setDepartment(department);
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public static boolean isValidDepartment(String department) {
		return department.equals("Mathematics") ||
			department.equals("Engineering") ||
			department.equals("English");
	}

}

class Faculty extends Employee {

	private String rank;

	public Faculty(String name, String id, String department, String rank) {
		super(name, id, department);
		setRank(rank);
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getRank() {
		return rank;
	}

	public String toString() {
		String facultyRepresentation = "";
		facultyRepresentation += "---------------------------------------------------------------------------\n";
		facultyRepresentation += getName() + "\t\t" + getId() + "\n";
		facultyRepresentation += getDepartment() + " Department, " + rank + "\n";
		facultyRepresentation += "---------------------------------------------------------------------------\n";
		return facultyRepresentation;
	}

	public static boolean isValidRank(String rank) {
		return rank.equals("Professor") || rank.equals("Adjunct");
	}

}

class Staff extends Employee {

	private String status;

	public Staff(String name, String id, String department, String status) {
		super(name, id, department);
		setStatus(status);
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public String toString() {
		String staffRepresentation = "";
		staffRepresentation += "---------------------------------------------------------------------------\n";
		staffRepresentation += getName() + "\t\t" + getId() + "\n";
		staffRepresentation += getDepartment() + " Department, " + status + "\n";
		staffRepresentation += "---------------------------------------------------------------------------\n";
		return staffRepresentation;
	}

	public static Staff readStaff() {
		System.out.print("\tName of staff member: ");
		String name = (new Scanner(System.in)).nextLine().trim();
		System.out.println();

		System.out.print("\tEnter the id: ");
		String id = (new Scanner(System.in)).nextLine().trim();
		System.out.println();

		String department;
		do {

			System.out.print("\tDepartment: ");
			department = (new Scanner(System.in)).nextLine().toLowerCase().trim();
			department = department.substring(0, 1).toUpperCase() + department.substring(1);

			if (!Staff.isValidDepartment(department))
				System.out.println("\t\t\"" + department + "\" is invalid");

			System.out.println();

		} while (!Staff.isValidDepartment(department));

		System.out.print("\tStatus, Enter P for Part Time or Enter F for Full Time: ");
		char status = (new Scanner(System.in)).nextLine().toLowerCase().charAt(0);
		String Status;
		if (status == 'f')
			Status = "Full Time";
		else
			Status = "Part Time";

		System.out.println();

		return new Staff(name, id, department, Status);
	}

}
