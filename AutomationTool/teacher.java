public class teacher {
	private int teacherId;
	private String teacherName;
	private String teacherDept;

 public teacher(int teacherId,String teacherName,String teacherDept){
	this.teacherId=teacherId;
	this.teacherName=teacherName;
	this.teacherDept=teacherDept;
	}
	public void  setTeacherId(int teacherId){
		this.teacherId=teacherId;
	}

	public int getTeacherId(){
		return teacherId;
	}

	public void  setTeacherName(String teacherName){
		this.teacherName=teacherName;
	}

	public String getTeacherName(){
		return teacherName;
	}

	public void  setTeacherDept(String teacherDept){
		this.teacherDept=teacherDept;
	}

	public String getTeacherDept(){
		return teacherDept;
	}
}