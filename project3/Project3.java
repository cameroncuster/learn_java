/*
   - Project 3
   - Cameron Custer
   */

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Project3 {

	public static University university;

	public static boolean invalid(String id) {
		if (id.length() != 6)
			return true;

		for (int i = 0; i < 6; i++)
			if (!(i < 2 && Character.isLetter(id.charAt(i))) ||
					!(i >= 2 && Character.isDigit(id.charAt(i))))
				return false;

		return true;
	}

	public static String createID() {
		String id;
		do {
			try {
				System.out.print("\tID: ");
				id = (new Scanner(System.in)).nextLine().trim();
				if (invalid(id))
					throw new IdException();
				if (university.find(id) == null)
					break;
			}
			catch (IdException e) {
				System.out.print(e);
			}
			System.out.println();
		} while (true);
		System.out.println();
		return id;
	}

	public static void main(String[] args) {

		System.out.println("\t\t\t\tWelcome to my Personal Management Program");
		System.out.println();

		university = new University(100);

		while (true) {

			// menu
			System.out.println("Choose one of the options:");
			System.out.println();
			System.out.println("1-Enter the information of a faculty");
			System.out.println("2-Enter the information of a student");
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

			String id;
			switch (option) {
				case 1:
					university.add(Faculty.readFaculty());
					System.out.println("Faculty added!");
					break;

				case 2:
					university.add(Student.readStudent());
					System.out.println("Student added!");
					break;

				case 3:
					while (true) {
						System.out.print("Enter the student’s ID: ");
						try {
							id = (new Scanner(System.in)).nextLine().trim();
							if (invalid(id))
								throw new IdException();
							break;
						}
						catch (IdException e) {
							System.out.print(e);
						}
						System.out.println();
					}
					System.out.println();
					Person student = university.find(id);
					if (student != null && student instanceof Student)
						student.print();
					else
						System.out.println("No student matched!");
					break;

				case 4:
					while (true) {
						System.out.print("Enter the Faculty’s ID: ");
						try {
							id = (new Scanner(System.in)).nextLine().trim();
							if (invalid(id))
								throw new IdException();
							break;
						}
						catch (IdException e) {
							System.out.print(e);
						}
						System.out.println();
					}
					System.out.println();
					Person faculty = university.find(id);
					if (faculty != null && faculty instanceof Faculty)
						faculty.print();
					else
						System.out.println("No faculty member matched!");
					break;

				case 5:
					university.add(Staff.readStaff());
					System.out.println("Staff member added!");
					break;

				case 6:
					while (true) {
						System.out.print("Enter the Staff’s ID: ");
						try {
							id = (new Scanner(System.in)).nextLine().trim();
							if (invalid(id))
								throw new IdException();
							break;
						}
						catch (IdException e) {
							System.out.print(e);
						}
						System.out.println();
					}
					System.out.println();
					Person staff = university.find(id);
					if (staff != null && staff instanceof Staff)
						staff.print();
					else
						System.out.println("No staff member matched!");
					break;

				case 7:
					System.out.print("Would you like to create the report? (Y/N): ");
					char choice = (new Scanner(System.in)).nextLine().toLowerCase().charAt(0);
					if (choice == 'y') {
						university.report();
						System.out.print("Report created and saved on your hard drive!");
					}
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

class IdException extends Exception {

	public String toString() {
		return "Invalid ID format. Must be LetterLetterDigitDigitDigitDigit.";
	}

}

class University {

	Person people[];
	int personIdx;

	public University() {
		people = null;
		personIdx = 0;
	}

	public University(int size) {
		people = new Person[size];
		personIdx = 0;
	}

	public void report() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(new File("report.txt"));
		}
		catch (Exception e) {
			return;
		}
		writer.println("\t\tReport created on " +
				DateTimeFormatter.ofPattern("yyyy/mm/dd").format(
					LocalDateTime.now()));
		writer.println("\t\t***********************************");

		writer.println("Faculty Members");
		writer.println("---------------");
		for (int i = 0, cnt = 1; i < personIdx; i++)
			if (people[i] instanceof Faculty)
				writer.println("\t" + cnt++ + ". " + people[i]);

		writer.println();
		writer.println();

		writer.println("Staff Members");
		writer.println("---------------");
		for (int i = 0, cnt = 1; i < personIdx; i++)
			if (people[i] instanceof Staff)
				writer.println("\t" + cnt++ + ". " + people[i]);

		writer.println();
		writer.println();

		writer.println("Students");
		writer.println("---------------");
		for (int i = 0, cnt = 1; i < personIdx; i++)
			if (people[i] instanceof Student)
				writer.println("\t" + cnt++ + ". " + people[i]);

		writer.println();
		writer.println();

		writer.close();
	}

	public boolean add(Person person) {
		if (personIdx < people.length) {
			people[personIdx++] = person;
			return true;
		}
		return false;
	}

	// ID's must be unique across entire university
	public Person find(String id) {
		for (int i = 0; i < personIdx; i++)
			if (people[i].getId().equals(id))
				return people[i];
		return null;
	}

	public Person[] getPeople() {
		return people;
	}

	public void setPeople(Person people[]) {
		this.people = people;
	}

	public int getPersonIdx() {
		return personIdx;
	}

	public void setPersonIdx(int personIdx) {
		this.personIdx = personIdx;
	}

}

abstract class Person {

	private String name;
	private String id;

	public Person() {
		name = "";
		id = "";
	}

	public Person(String name, String id) {
		setName(name);
		setId(id);
	}

	public abstract void print();

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

class Student extends Person {

	private double gpa;
	private int creditHours;

	public Student() {
		super();
		gpa = 0;
		creditHours = 0;
	}

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

	public String toString() {
		return getName() + "\n\tID: " + getId() + "\n\tGpa: " + gpa +
			"\n\tCredit hours: " + creditHours;
	}

	public void print() {
		double totalPayment = 52.00 + 236.45 * creditHours;
		double discount = 0.0;
		if (gpa >= 3.85)
			discount = 15 * (totalPayment / 100.0);
		totalPayment -= discount;

		String tuitionInvoice = "Here is the tuition invoice for " + getName() + " :\n\n";
		tuitionInvoice += "-------------------------------------------------------------------------------\n";
		tuitionInvoice += getName() + "\t\t\t\t" + getId() + "\n";
		tuitionInvoice += "Credit Hours: " + creditHours + " ($236.45/credit hour)\n";
		tuitionInvoice += "Fees: $52\n";
		tuitionInvoice += "\n";
		tuitionInvoice +=
			"Total payment (after discount): " +
			NumberFormat.getCurrencyInstance().format(totalPayment) +
			"\t\t(" +
			NumberFormat.getCurrencyInstance().format(discount) +
			" discount applied)\n";
		tuitionInvoice += "-------------------------------------------------------------------------------\n";

		System.out.println(tuitionInvoice);
	}

	public static Student readStudent() {
		System.out.println("Enter the student info:");
		System.out.println();

		System.out.print("\tName of Student: ");
		String name = (new Scanner(System.in)).nextLine().trim();
		System.out.println();

		String id = Project3.createID();

		System.out.println();
		double Gpa;
		while (true) {
			System.out.print("\tGpa: ");
			try {
				Gpa = (new Scanner(System.in)).nextDouble();
				break;
			}
			catch (Exception e) {
				System.out.println("GPA must be a double.");
			}
			System.out.println();
		}
		System.out.println();

		System.out.print("\tCredit hours: ");
		int creditHours;
		while (true) {
			try {
				creditHours = (new Scanner(System.in)).nextInt();
				break;
			}
			catch (Exception e) {
				System.out.println("Credit Hours must be an integer.");
			}
			System.out.println();
		}
		System.out.println();

		return new Student(name, id, Gpa, creditHours);
	}

}

abstract class Employee extends Person {

	private String department;

	public Employee() {
		super();
		department = "";
	}

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
			department.equals("Sciences") ||
			department.equals("English");
	}

}

class Faculty extends Employee {

	private String rank;

	public Faculty() {
		super();
		rank = "";
	}

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
		return getName() + "\n\tID: " + getId() + "\n\t" + rank + ", " +
			getDepartment();
	}

	public void print() {
		String facultyRepresentation = "";
		facultyRepresentation += "-------------------------------------------------------------------------------\n";
		facultyRepresentation += getName() + "\t\t" + getId() + "\n";
		facultyRepresentation += getDepartment() + " Department, " + rank + "\n";
		facultyRepresentation += "-------------------------------------------------------------------------------\n";
		System.out.println(facultyRepresentation);
	}

	public static boolean isValidRank(String rank) {
		return rank.equals("Professor") || rank.equals("Adjunct");
	}

	public static Faculty readFaculty() {
		System.out.println("Enter faculty info:");
		System.out.println();

		System.out.print("\tName of the faculty: ");
		String name = (new Scanner(System.in)).nextLine().trim();
		System.out.println();
		System.out.println();

		String id = Project3.createID();
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

		return new Faculty(name, id, department, rank);
	}

}

class Staff extends Employee {

	private String status;

	public Staff() {
		super();
		status = "";
	}

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
		return getName() + "\n\tID: " + getId() + "\n\t" + getDepartment() +
			", " + status;
	}

	public void print() {
		String staffRepresentation = "";
		staffRepresentation += "-------------------------------------------------------------------------------\n";
		staffRepresentation += getName() + "\t\t" + getId() + "\n";
		staffRepresentation += getDepartment() + " Department, " + status + "\n";
		staffRepresentation += "-------------------------------------------------------------------------------\n";
		System.out.println(staffRepresentation);
	}

	public static Staff readStaff() {
		System.out.print("\tName of staff member: ");
		String name = (new Scanner(System.in)).nextLine().trim();
		System.out.println();

		String id = Project3.createID();
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
