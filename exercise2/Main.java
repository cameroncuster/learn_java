import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.lang.Math;

class Main {

	public static void main(String[] args) {

		// Scanner
		Scanner sc = new Scanner(System.in);

		do {

			// Menu
			System.out.println("Choose from the following options:");
			System.out.println("A or a: To display the divisors of a natural number");
			System.out.println("B or b: To display the multiplication table for a natural number");
			System.out.println("Q or q to Exit\n");
			System.out.print("\tEnter your choice: ");

			// option
			char option = sc.next().charAt(0);
			option = Character.toLowerCase(option);

			switch (option) {

				// quit
				case 'q':
					System.out.println("\tBye!");
					System.exit(0);
					break;

				// options
				case 'a':
				case 'b':

					// input
					System.out.print("\tEnter your number: ");

					int number = sc.nextInt();

					if (number < 1) {
						System.out.println("\t" + number +
								" is not a natural number.");
						break;
					}

					// divisors
					if (option == 'a') {

						TreeSet<Integer> divisors = new TreeSet<Integer>();

						for (int divisor = 2; divisor < Math.sqrt(number) + 1;
								divisor++) {
							if (number % divisor == 0) {
								divisors.add(divisor);
								divisors.add(number / divisor);
							}
						}

						if (divisors.isEmpty())
							System.out.println("\t" + number +
									" is prime (the only divisors of " +
									number + " are 1 and " + number + ")");
						else {
							System.out.print("\tThe divisors of " + number +
									" are: 1,");

							Integer last = divisors.last();
							divisors.remove(last);

							for (Integer divisor : divisors) {
								System.out.print(divisor + ",");
							} // end for

							System.out.println(last + " and " + number);
						}
					}
					// multiplication table
					else {
						System.out.print("\tEnter the size of the multiplication table (1,2...etc): ");

						int n = sc.nextInt();

						int i = 1;

						while (i <= n) {
							System.out.println("\t" + i + " x " + number + " = "
									+ i * number);
							i++;
						} // end while
					}
					break;

				// invalid
				default:
					System.out.println("\tInvalid option!");

			} // end switch

			System.out.println();

		} while (true); // end do while

	}

}
