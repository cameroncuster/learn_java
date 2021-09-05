import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.lang.Math;

class Main {

	public static void main(String[] args) {

		char option = ' ';
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Choose from the following options:\nA or a: To display the divisors of a natural number\nB or b: To display the multiplicationtable for a natural number\nQ or q to Exit\n\n\tEnter your choice: ");
			option = sc.next().charAt(0);
			option = Character.toLowerCase(option);
			switch (option) {
				case 'q':
					System.out.println("\tBye!");
					System.exit(0);
					break;

				case 'a':
				case 'b':
					System.out.print("\tEnter your number: ");
					int number = sc.nextInt();
					if (number < 1) {
						System.out.println("\t" + number + " is not a natural number.");
						break;
					}

					if (option == 'a') {
						Set<Integer> divisors = new TreeSet<Integer>();
						for (int divisor = 2; divisor < Math.sqrt(number) + 1; divisor++) {
							if (number % divisor == 0) {
								divisors.add(divisor);
								divisors.add(number / divisor);
							}
						}
						if (divisors.isEmpty()) {
							System.out.println("\t" + number + " is prime (the only divisors of " + number + " are 1 and " + number + ")");
						}
						else {
							System.out.print("\tThe divisors of " + number + " are: 1,");
							for (Integer divisor : divisors)
								System.out.print(divisor + ",");
							System.out.println(" and " + number);
						}
					}
					else {
						System.out.print("\tEnter the size of the multiplication table (1,2...etc): ");
						int n = sc.nextInt();
						int i = 1;
						while (i <= n) {
							System.out.println("\t" + i + " x " + number + " = " + i * number);
							i++;
						}
					}
					break;

				default:
					System.out.println("\tInvalid option!");
			}
			System.out.println();
		} while (true);

	}

}
