public class transaction {
	private int tid;
	private int accno;
	private Date date;
	private String type;
	private float amount;
	private float balance;

 public transaction(int tid,int accno,Date date,String type,float amount,float balance){
	this.tid=tid;
	this.accno=accno;
	this.date=date;
	this.type=type;
	this.amount=amount;
	this.balance=balance;
	}
	public void  setTid(int tid){
		this.tid=tid;
	}

	public int getTid(){
		return tid;
	}

	public void  setAccno(int accno){
		this.accno=accno;
	}

	public int getAccno(){
		return accno;
	}

	public void  setDate(Date date){
		this.date=date;
	}

	public Date getDate(){
		return date;
	}

	public void  setType(String type){
		this.type=type;
	}

	public String getType(){
		return type;
	}

	public void  setAmount(float amount){
		this.amount=amount;
	}

	public float getAmount(){
		return amount;
	}

	public void  setBalance(float balance){
		this.balance=balance;
	}

	public float getBalance(){
		return balance;
	}
}