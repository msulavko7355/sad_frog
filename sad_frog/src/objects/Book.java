package objects;

import java.awt.Color;

public class Book {
	private String title;
	private int numberOfPages;
	private Person author;
	private Color jacketColor;
	private boolean wasLitOnFire;
	private int height;
	private int thickness;
	private boolean checkedOut;
	private long checkOutDate;
	private long dueDate;
	static public String[] conditions = {"Brand new.",
										 "Good enough.",
										 "Did someone eat on this book?"};
	private String description;
	private int accumulatedUse;
	public boolean isCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public long getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(long checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public long getDueDate() {
		return dueDate;
	}

	public void setDueDate(long dueDate) {
		this.dueDate = dueDate;
	}
	public long getSecondsRemaining(){
		return (dueDate-System.currentTimeMillis())/1000;
	}
	public Book(String title, int numOfPages, Person author){
		this.title = title;
		this.numberOfPages = numOfPages;
		this.author = author;
		jacketColor = Color.gray;
		wasLitOnFire=false;
		this.height = (int)(Math.random()*100+150);
		this.thickness = this.numberOfPages/10;
		this.checkedOut = false;
		this.checkOutDate = 0;
		this.dueDate = 0;
		this.description = conditions[0];
		this.accumulatedUse = 0;
	}
	public void updateCondition(long timeOfReturn){
		this.accumulatedUse += ((timeOfReturn) - this.checkOutDate)/1000;
		if (accumulatedUse > 30){
			this.description = conditions[1];
		}
		if (accumulatedUse > 60){
			this.description = conditions[2];
		}
		if (accumulatedUse > 90){
			this.description = conditions[3];
		}
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAccumulatedUse() {
		return accumulatedUse;
	}

	public void setAccumulatedUse(int accumulatedUse) {
		this.accumulatedUse = accumulatedUse;
	}

	public int getHeight() {
		return height;
	}

	public int getThickness() {
		return thickness;
	}

	public String toString(){
		if(wasLitOnFire){
			return "\"" + title + "\", by " + "You cannot make out the author's name" + ". " + numberOfPages + " pages.";
		}
		else{
			return "\"" + title + "\", by " + author + ". " + numberOfPages + " pages.";
		}
	}
	
	//getter
	public String getTitle(){
		return title;
	}
	public int getNumberOfPages(){
		return numberOfPages;
	}
	public Person getAuthor() {
		
		return author;
	}
	public Color getColor(){
		return jacketColor;
	}
	public void setJacketColor(Color jacketColor) {
		this.jacketColor = jacketColor;
	}
	public void setOnFire(){
		jacketColor = Color.black;
		title = "The title of this book is too charred to make out";
		wasLitOnFire = true;
	}
}