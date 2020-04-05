package pojo;

public class Book {

	public String name;
	public String isbn;
	public String aisle;
	public String author;

	public Book() {
		super();
	}

	public Book(String name, String isbn, String aisle, String author) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.aisle = aisle;
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAisle() {
		return aisle;
	}

	public void setAisle(String aisle) {
		this.aisle = aisle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
