package day03.task3;

public class VisitDto {
	private int num;
	private String content;
	private int age;
	public VisitDto() {}
	public VisitDto(String content, int age, int num) {
		super();
		this.num = num;
		this.content = content;
		this.age = age;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "VisitDto [num=" + num + ", content=" + content + ", age=" + age + "]";
	}
	
	
	

}
