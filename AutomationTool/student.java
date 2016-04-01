public class student {
	private int studentId;
	private String studentName;
	private String studentDept;

 public student(int studentId,String studentName,String studentDept){
	this.studentId=studentId;
	this.studentName=studentName;
	this.studentDept=studentDept;
	}
	public void  setStudentId(int studentId){
		this.studentId=studentId;
	}

	public int getStudentId(){
		return studentId;
	}

	public void  setStudentName(String studentName){
		this.studentName=studentName;
	}

	public String getStudentName(){
		return studentName;
	}

	public void  setStudentDept(String studentDept){
		this.studentDept=studentDept;
	}

	public String getStudentDept(){
		return studentDept;
	}
}