class Main {

	public static void main(String[] args) {

		LibraryBook b1 = new LibraryBook("Emily Jones", "The OrigiN of SPECiEs",
				"23654585", 1965, "bio");
		BookstoreBook b2 = new BookstoreBook("Emily Jones",
				"The OrigiN of SPECiEs", "23654585", 1965, 89.99);

		System.out.println(b1.createCode());
		System.out.println(b2.createCode());

		System.out.println(b1);
		System.out.println(b2);

	}

}

interface Codable {

	public String createCode();

}

abstract class Book implements Codable {

	private String author;
	private String title;
	private String isbn;
	private int year;

	public Book(String author, String title, String isbn, int year) {
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.year = year;
	}

	String getAuthor() {
		return author;
	}

	String getTitle() {
		return title;
	}

	String getIsbn() {
		return isbn;
	}

	int getYear() {
		return year;
	}

	public String toString() {
		return title.toLowerCase() + " by " + author.toLowerCase() +
			" published in " + year;
	}

}

class LibraryBook extends Book {

	private String subject;

	public LibraryBook(String author, String title, String isbn, int year,
			String subject) {
		super(author, title, isbn, year);
		this.subject = subject;
	}

	public String createCode() {
		String[] names = getAuthor().toLowerCase().split(" ");
		return "[" + subject + "-" + names[0] + " " + names[1] + "-" +
			getTitle().toLowerCase() + "-" + getYear() + "]";
	}

	public String toString() {
		String formattedSubject;

		if (subject.equals("bio"))
			formattedSubject = "biology";
		else if (subject.equals("acc"))
			formattedSubject = "accounting";
		else if (subject.equals("psy"))
			formattedSubject = "psychology";
		else
			formattedSubject = "religion";

		return super.toString() + ", " + formattedSubject;
	}

}

class BookstoreBook extends Book {

	private double price;

	public BookstoreBook(String author, String title, String isbn, int year,
			double price)
	{
		super(author, title, isbn, year);
		this.price = price;
	}

	public String createCode() {
		String[] names = getAuthor().toLowerCase().split(" ");
		return "[" + getIsbn() + "-" + names[0] + " " + names[1] + "-" + price
			+ "]";
	}

	public String toString() {
		return getIsbn() + ": " + super.toString() + ", $" + price;
	}

}
