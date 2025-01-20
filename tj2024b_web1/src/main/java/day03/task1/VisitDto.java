package day03.task1;

public class VisitDto {
	private String content;
	private int age;
	public VisitDto() {}
	public VisitDto(String content, int age) {
		super();
		this.content = content;
		this.age = age;
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
		return "VisitDto [content=" + content + ", age=" + age + "]";
	}
	
	
	

}
