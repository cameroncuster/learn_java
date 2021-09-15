import java.util.Scanner;
import java.util.Arrays;

class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int nameCount;

		// assume nameCount is positive or -1
		while (true) {

			System.out.print("Enter how many name(s) you have: ");

			nameCount = sc.nextInt();

			System.out.println();

			if (nameCount <= 5)
				break;

			System.out.println("Sorry, you can’t have more than 5 names stored in our system!Enter -1 to exit this program.\n");

		}

		if (nameCount == -1) {
			System.out.println("Bye!");
			return;
		}

		System.out.println("Enter your " + nameCount + " names separated by * then hit the Enter Key: ");

		sc.nextLine();
		String nameString = sc.nextLine().toUpperCase();
		String[] arrayOfNames = nameString.split("[*]", -1);

		// assume the names are not white space
		for (int i = 0; i < arrayOfNames.length; i++)
			arrayOfNames[i] = arrayOfNames[i].trim();

		Arrays.sort(arrayOfNames);

		System.out.println("Thank you!\nYou entered:");

		for (int i = 0; i < arrayOfNames.length - 1; i++)
			System.out.print(arrayOfNames[i] + ", ");
		System.out.println("and " + arrayOfNames[arrayOfNames.length - 1]);
		System.out.println();

		while (true) {

			System.out.print("Do you want to make a change to any of the entered names (enter y/Y for yes, n/N for no, e/E to Exit program): ");

			char choice = sc.next().toLowerCase().charAt(0);

			System.out.println();

			if (choice == 'e' || choice == 'n') {
				System.out.println("Goodbye!");
				return;
			}

			System.out.print("Enter the name you would like to change: ");

			sc.nextLine();
			String nameToChange = sc.nextLine().toUpperCase().trim();

			int foundNameIndex = -1;
			for (int index = 0; index < arrayOfNames.length; index++)
				if (nameToChange.equals(arrayOfNames[index]))
					foundNameIndex = index;

			if (foundNameIndex > -1) {

				System.out.print("\nEnter the new name: ");
				arrayOfNames[foundNameIndex] = sc.nextLine().toUpperCase().trim();

				Arrays.sort(arrayOfNames);

				System.out.println("\nGot it! Here is the new list:");

				System.out.print("[");
				for (int i = 0; i < arrayOfNames.length - 1; i++)
					System.out.print(arrayOfNames[i] + ", ");
				System.out.println(arrayOfNames[arrayOfNames.length - 1] + "]");

			}
			else {

				System.out.println("Sorry no such name in your list. Would you like to add \"" + nameToChange + "\" to the list? enter y/Y for yes, n/N for no, e/E to Exit program): ");

				choice = sc.next().toLowerCase().charAt(0);

				if (choice == 'e' || choice == 'n') {
					System.out.println("Goodbye!");
					return;
				}

				if (arrayOfNames.length == 5)
					System.out.println("Sorry, you can’t add a new name as you already have 5 names!\n");
				else {

					String[] newArrayOfNames = new String[arrayOfNames.length + 1];

					for (int i = 0; i < arrayOfNames.length; i++)
						newArrayOfNames[i] = arrayOfNames[i];
					newArrayOfNames[newArrayOfNames.length - 1] = nameToChange;

					arrayOfNames = newArrayOfNames;

					Arrays.sort(arrayOfNames);

					System.out.println("The name " + nameToChange + " has been added");

				}

			}

			System.out.println();

		}

	}

}
