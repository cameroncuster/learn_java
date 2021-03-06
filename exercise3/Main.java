import java.util.Scanner;
import java.util.Arrays;

class Main {

	// print function
	public static void printNames(String[] arrayOfNames) {

		System.out.print("[");

		for (int i = 0; i < arrayOfNames.length - 1; i++)
			System.out.print(arrayOfNames[i] + ", ");

		System.out.println(arrayOfNames[arrayOfNames.length - 1] + "]");

	}

	// main method
	public static void main(String[] args) {

		// scanner
		Scanner sc = new Scanner(System.in);

		// count of names
		int nameCount;

		// assume nameCount is positive or -1
		while (true) {

			// capture initial size and list
			System.out.print("Enter how many name(s) you have: ");

			nameCount = sc.nextInt();

			System.out.println();

			if (nameCount <= 5)
				break;

			System.out.println("Sorry, you can’t have more than 5 names stored in our system! Enter -1 to exit this program.\n");

		}

		// exit accordingly
		if (nameCount == -1) {
			System.out.println("\nBye!");
			return;
		}

		// prompt for list of names seperated by '*'
		System.out.println("Enter your " + nameCount + " names separated by * then hit the Enter Key: ");

		// capture the list of names
		sc.nextLine();
		String nameString = sc.nextLine().toUpperCase();
		String[] arrayOfNames = nameString.split("[*]", -1);

		// assume the names are not white space
		for (int i = 0; i < arrayOfNames.length; i++)
			arrayOfNames[i] = arrayOfNames[i].trim();

		// sort and output
		Arrays.sort(arrayOfNames);
		System.out.println("Thank you!\nYou entered:");
		printNames(arrayOfNames);

		// allow updates to the name list
		while (true) {

			// prompt for option to update list of names
			System.out.print("\nDo you want to make a change to any of the entered names (enter y/Y for yes, n/N for no, e/E to Exit program): ");

			// choice input (readable)
			char choice = sc.next().toLowerCase().charAt(0);
			System.out.println();

			// exit accordingly
			if (choice == 'e' || choice == 'n') {
				System.out.println("Goodbye!");
				return;
			}

			// updates to the list
			System.out.print("Enter the name you would like to change: ");

			// capture name to update
			sc.nextLine();
			String nameToChange = sc.nextLine().toUpperCase().trim();

			// found names
			int foundNameIndex = -1;
			for (int index = 0; index < arrayOfNames.length; index++)
				if (nameToChange.equals(arrayOfNames[index]))
					foundNameIndex = index;

			// find name in list
			if (foundNameIndex > -1) {

				// prompt for name to replace with
				System.out.print("\nEnter the new name: ");
				arrayOfNames[foundNameIndex] = sc.nextLine().toUpperCase().trim();

				// maintain sorting
				Arrays.sort(arrayOfNames);

				// output new list of names
				System.out.println("\nGot it! Here is the new list:");
				printNames(arrayOfNames);

			}
			else {

				// prompt to add name not found to list
				System.out.print("Sorry no such name in your list. Would you like to add \"" + nameToChange + "\" to the list? enter y/Y for yes, n/N for no, e/E to Exit program): ");

				// choice (readable)
				choice = sc.next().toLowerCase().charAt(0);
				System.out.println();

				// exit accordingly
				if (choice == 'e' || choice == 'n') {
					System.out.println("Goodbye!");
					return;
				}

				// handle maximum list size
				if (arrayOfNames.length == 5)
					System.out.println("Sorry, you can’t add a new name as you already have 5 names!");

				else {

					// add the name to the list
					String[] newArrayOfNames = new String[arrayOfNames.length + 1];
					for (int i = 0; i < arrayOfNames.length; i++)
						newArrayOfNames[i] = arrayOfNames[i];
					newArrayOfNames[newArrayOfNames.length - 1] = nameToChange;
					arrayOfNames = newArrayOfNames;

					// maintain sorting
					Arrays.sort(arrayOfNames);

					// output added name confirmation
					System.out.println("The name " + nameToChange + " has been added");

				}

			}

			System.out.println();

		}

	}

}
