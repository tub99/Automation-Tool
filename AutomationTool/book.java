public class book {
	private int bookId;
	private String bookNo;
	private String isbn;
	private String bookName;
	private String authorName;
	private String publication;
	private int bookQuantity;
	private int issuedStatus;
	private String issuedTo;
	private Date issueDate;
	private Date returnDate;
	private double bookPrice;

 public book(int bookId,String bookNo,String isbn,String bookName,String authorName,String publication,int bookQuantity,int issuedStatus,String issuedTo,Date issueDate,Date returnDate,double bookPrice){
	this.bookId=bookId;
	this.bookNo=bookNo;
	this.isbn=isbn;
	this.bookName=bookName;
	this.authorName=authorName;
	this.publication=publication;
	this.bookQuantity=bookQuantity;
	this.issuedStatus=issuedStatus;
	this.issuedTo=issuedTo;
	this.issueDate=issueDate;
	this.returnDate=returnDate;
	this.bookPrice=bookPrice;
	}
	public void  setBookId(int bookId){
		this.bookId=bookId;
	}

	public int getBookId(){
		return bookId;
	}

	public void  setBookNo(String bookNo){
		this.bookNo=bookNo;
	}

	public String getBookNo(){
		return bookNo;
	}

	public void  setIsbn(String isbn){
		this.isbn=isbn;
	}

	public String getIsbn(){
		return isbn;
	}

	public void  setBookName(String bookName){
		this.bookName=bookName;
	}

	public String getBookName(){
		return bookName;
	}

	public void  setAuthorName(String authorName){
		this.authorName=authorName;
	}

	public String getAuthorName(){
		return authorName;
	}

	public void  setPublication(String publication){
		this.publication=publication;
	}

	public String getPublication(){
		return publication;
	}

	public void  setBookQuantity(int bookQuantity){
		this.bookQuantity=bookQuantity;
	}

	public int getBookQuantity(){
		return bookQuantity;
	}

	public void  setIssuedStatus(int issuedStatus){
		this.issuedStatus=issuedStatus;
	}

	public int getIssuedStatus(){
		return issuedStatus;
	}

	public void  setIssuedTo(String issuedTo){
		this.issuedTo=issuedTo;
	}

	public String getIssuedTo(){
		return issuedTo;
	}

	public void  setIssueDate(Date issueDate){
		this.issueDate=issueDate;
	}

	public Date getIssueDate(){
		return issueDate;
	}

	public void  setReturnDate(Date returnDate){
		this.returnDate=returnDate;
	}

	public Date getReturnDate(){
		return returnDate;
	}

	public void  setBookPrice(double bookPrice){
		this.bookPrice=bookPrice;
	}

	public double getBookPrice(){
		return bookPrice;
	}
}