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

class Personal {

	String name;
	String id;

}

class Student extends Personal {

	double gpa;
	int creditHours;

}

class Faculty extends Personal {

	String department;
	char status;

}

class Staff extends Personal {

	String department;
	String rank;

}
