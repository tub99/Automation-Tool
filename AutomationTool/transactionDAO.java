public class transactionDAO {

	public int insert(transaction obj, Connection con){
	String str = insert into transaction(obj.tid,obj.accno,obj.date,obj.type,obj.amount,obj.balance) values(obj.getTid(),obj.getAccno(),obj.getDate(),obj.getType(),obj.getAmount(),obj.getBalance());
	Statement st=con.createStatement();
	int x=st.executeUpdate(str);
	return x;
	}
	public int update(transaction obj, Connection con){
		String str = update transaction set obj.accno=obj.getAccno(), obj.date=obj.getDate(), obj.type=obj.getType(), obj.amount=obj.getAmount(), obj.balance=obj.getBalance() where obj.tid=obj.getTid();
		Statement st=con.createStatement();
		int x=st.executeUpdate(str);
		return x;
	}
	public int deleteDb(int tid, Connection con){
		String str = delete from transaction where this.tid=tid;
		Statement st=con.createStatement();
		int x=st.executeUpdate(str);
		return x;
	}
	public int searchDb(int tid, Connection con){
		String str = select * from transaction where this.tid=tid;
		Statement st=con.createStatement();
		int x=st.executeQuery(str);
		return x;
	}
}