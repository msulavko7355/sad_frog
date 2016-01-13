package objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;


public class Bookshelf {
	public static void main(String[] args) {
		Person author1 = new Person("Noah", "Webster");
		Person author2 = new Person("Anthony", "Burgess");
		Person author3 = new Person("Philip", "K. ", "Dick");
		Book book1 = new Book("Dictionary", 1001, author1);
		Book book2 = new Book("A ClockWork Orange", 176, author2);
		Book book3 = new Book("Do Androids Dream of Electric Sheep?", 500, author3);
		Book book4 = new Book("The Man in the High Castle", 600, author3);
		//using a setter to make jacketColor from default gray to orange	
		Book book5 = new Book("The Minority Report", 589, author3);
		
		book1.setJacketColor(Color.blue);
		book2.setJacketColor(Color.orange);
		book3.setJacketColor(Color.cyan);
		book4.setJacketColor(Color.red);
		book5.setJacketColor(new Color(102, 14, 14));
		
		//book3.setOnFire();
		
		ArrayList<Book> shelf = new ArrayList<Book>();
		shelf.add(book1);
		shelf.add(book2);
		shelf.add(book3);
		shelf.add(book4);
		shelf.add(0, book5);
		
			
		sortByAuthor(true, shelf);
		
		ArrayList<Person> libraryCardHolders = new ArrayList<Person>();
		libraryCardHolders.add(new Person("Anikan", "Skywalker"));
		libraryCardHolders.add(new Person("Rick", "Sanches"));
		libraryCardHolders.add(new Person("Jane", "Doe"));
		
		printAll(shelf);
		
		Library lib = new Library(shelf, libraryCardHolders);//use "books" or "shelf" whatever your ArrayList is 

		lib.setSize(new Dimension(500,500));

		lib.setVisible(true);

		lib.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sortByAuthor(true, shelf);
		printAll(shelf);
	}
	
	public static void sortByAuthor(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {//anonymous inner class.
		    public int compare(Book a, Book b) {
		    	String aLast = a.getAuthor().getLastName();
		    	String bLast = b.getAuthor().getLastName();
		    	if(ascending){
		    		return bLast.compareTo(aLast);
		    	}
		        return aLast.compareTo(bLast);
		    	
		    }

		});
	}
	public static void sortByPageNumber(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {//anonymous inner class.
		    public int compare(Book a, Book b) {
		    	if(ascending){
		    		return b.getNumberOfPages() - a.getNumberOfPages();
		    	}
		    	return a.getNumberOfPages() - b.getNumberOfPages();
		    	
		    }

		});
	}
	public static void sortByHeight(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {//anonymous inner class.
		    public int compare(Book a, Book b) {
		    	if(ascending){
		    		return b.getHeight() - a.getHeight();
		    	}
		    	return a.getHeight() - b.getHeight();
		    }

		});
	}
	public static void sortByTitle(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {//anonymous inner class.
		    public int compare(Book a, Book b) {
		    	String aTitle = a.getTitle();
		    	String bTitle = b.getTitle();
		    	if(ascending){
		    		return bTitle.compareTo(aTitle);
		    	}
		        return aTitle.compareTo(bTitle);
		    	
		    }

		});
	}
	private static void printAll(ArrayList list){
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	private void notes(){
		/**
		ArrayList<Book> shelf = new ArrayList<Book>();
		ArrayList<Book> creepyBooks = new ArrayList<Book>();
		for (Book b: shelf) {
			if (b.getAuthor().toString().equals("Philip K. Dick")) 
				creepyBooks.add(b);
		}
		printAll(creepyBooks);
	
		 * "<Book>" is a generic type. It tells the constructor what's 
		 * 		in the ArrayList. Saves trouble of type-casting.
		 * "new ArrayList<?>()" constructor does not need to specify length.
		 * Default length is 10 (doesn't matter) but adjusts as you add objects.
		 * Note that ArrayList is indexed (item 0 is always at index 0, and
		 * 		item 1 is always at index 1), can be iterated through.
		 * You cannot make an ArrayList of primitives
		 * 		(no int, boolean, ect) IF you want to make an ArrayList of int,
		 * 		you use the wrapper class
		 * 		int is Integer, double is Double, ect
		
		//add things to an ArrayList (remember, for arrays it was:
		//array[0] = book1; //specify index
		shelf.add(book1);
		//above does not specify index, automatically at index 0
		shelf.add(book2);
		shelf.add(book3);
		shelf.add(new Book("The Man in the High Castle", 600, author3));
		
		System.out.println("The first book on the shelf is " + shelf.get(0)); //.get() is instead of arr[]
		
		System.out.println("\nUsing a for-each loop:");
		for(Book b: shelf) System.out.println(b);
		
	
		//adding Objects at specified indices
		Book book5 = new Book("The Minority Report", 589, author3);
		shelf.add(0, book5);
		shelf.remove(book1);
		//task: remove all books with the word "The" in the title
		for (int b = 0; b < shelf.size(); b++)	
			while (b < shelf.size() && shelf.get(b).toString().toLowerCase().indexOf("the") > -1) 
				shelf.remove(b); 
		
		
		//to get the length of ArrayList
		//recall using arrays:
		//array.length
		System.out.println("The length (size) of the shelf is " + shelf.size() + " books");
	
		//iterate through an ArrayList, using standard for loop:
		System.out.println("\nUsing a standard for loop:");
		for (int i = 0; i < shelf.size(); i++) System.out.println(shelf.get(i));
		System.out.println("The length (size) of the shelf is " + shelf.size() + " books");
	
		//identifies whether or not a an Object is in the list
		//getting the index of an Object in an ArrayList
		if (shelf.contains(book2))
			System.out.println(book2.getTitle() + " is the #" 
				+ shelf.indexOf(book2) + " on the shelf.");
		
	*/
	}
	}


