package objects;

import java.util.ArrayList;

public class Person {
	private String firstName;
	private String lastName;
	private String middleName;
	public static int MAX_ALLOWED_BOOKS = 3;
	private boolean male;
	private Balance balance;
	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	ArrayList<Book> checkedOutBooks;
	public ArrayList<Book> getCheckedOutBooks() {
		return checkedOutBooks;
	}

	public void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) {
		this.checkedOutBooks = checkedOutBooks;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	//constructor
	public Person(String firstName, String lastName){
		this.firstName = firstName;//assigns first name to parameter.
		this.lastName = lastName;//this is used to specify the field.
		//whereas lastName, by itself, is the local parameter.
		this.middleName = "";
		this.checkedOutBooks = new ArrayList<Book>();
		this.male = true;
		this.balance = new Balance();
	}
	
	//constructor for middle-named people
	public Person(String firstName, String middleName, String lastName){
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.checkedOutBooks = new ArrayList<Book>();
		this.male = true;
		this.balance = new Balance();
	}
	
	public String toString(){//this overrides Java's default method.
		return firstName + " " + middleName + " " + lastName;
	}
	public void checkOutBook(Book book){
		book.setCheckedOut(true);
		book.setCheckOutDate(System.currentTimeMillis());
		book.setDueDate(System.currentTimeMillis()+30000);
		checkedOutBooks.add(book);
	}
	public void returnBook(Book book){
		book.setCheckedOut(false);
		book.updateCondition(System.currentTimeMillis());
		balance.subtractLateFees(book.getDueDate()-System.currentTimeMillis());
		book.setCheckOutDate(0);
		book.setDueDate(0);
		checkedOutBooks.remove(book);
	}
	public void renewBook(Book book){
		book.setDueDate(System.currentTimeMillis()+30000);
	}
	public String getGenderPosessivePronoun(){
		if(this.isMale()){
			return "his";
		}
		return "her";
	}
	public String getLibraryDescription(){
		return this.firstName + " is peering at library collection. The balance is $" + this.balance.getAmount() + ".";
	}
}