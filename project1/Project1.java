/*
   - Project 1
   - Cameron Custer
 */

import java.util.*;
import java.io.*;

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
				System.out.println("\tInvalid entry-please try again");
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

						if (!Faculty.isValidRank(rank))
							System.out.println("\t\t\"" + rank + "\" is invalid");

						System.out.println();

					} while (!Faculty.isValidRank(rank));
					System.out.println();

					String department;
					do {

						System.out.print("\tDepartment: ");
						department = (new Scanner(System.in)).nextLine().toLowerCase().trim();

						if (!Faculty.isValidDepartment(department))
							System.out.println("\t\t\"" + department + "\" is invalid");

						System.out.println();

					} while (!Faculty.isValidDepartment(department));
					System.out.println();

					university.addFaculty(new Faculty(name, id, department, rank));

					System.out.println("Faculty successfully added!");

					break;

				case 2:
					break;

				case 3:
					break;

				case 4:
					break;

				case 5:
					break;

				case 6:
					break;

				case 7:
					System.out.println("\tGoodbye!");
					return;

				default:
					System.out.println("\tInvalid entry-please try again");
			}

			System.out.println();

		}

	}

}

class University {

	private ArrayList<Student> students;
	private ArrayList<Faculty> faculty;
	private ArrayList<Staff> staff;

	public University() {
		students = new ArrayList<Student>();
		faculty = new ArrayList<Faculty>();
		staff = new ArrayList<Staff>();
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void addFaculty(Faculty faculty) {
		this.faculty.add(faculty);
	}

	public void addStaff(Staff staff) {
		this.staff.add(staff);
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

}

class Faculty extends Personal {

	private String department;
	private String rank;

	public Faculty(String name, String id, String department, String rank) {
		super(name, id);
		setDepartment(department);
		setRank(rank);
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public static boolean isValidDepartment(String department) {
		return department.equals("mathematics") ||
			department.equals("engineering") ||
			department.equals("english");
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getRank() {
		return rank;
	}

	public static boolean isValidRank(String rank) {
		return rank.equals("professor") || rank.equals("adjunct");
	}

}

class Staff extends Personal {

	private String department;
	private char status;

	public Staff(String name, String id, String department, char status) {
		super(name, id);
		setDepartment(department);
		setStatus(status);
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getStatus() {
		return status;
	}

}
