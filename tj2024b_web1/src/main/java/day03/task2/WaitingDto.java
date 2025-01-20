package day03.task2;

public class WaitingDto {
	// 1. 멤버변수
	private String phone;
	private int count;
	
	// 2. 생성자 
	public WaitingDto() {}
	public WaitingDto(String phone, int count) {
		super();
		this.phone = phone;
		this.count = count;
	}
	
	// 3. 메소드 : setter/setter , toString
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "WaitingDto [phone=" + phone + ", count=" + count + "]";
	}
	

}
