package day04;

public class ValueDto {
	   private String value1;
	   private int value2;
	   public ValueDto() {}
	   public ValueDto(String value1, int value2) {
	      super();
	      this.value1 = value1;
	      this.value2 = value2;
	   }
	   public String getValue1() {
	      return value1;
	   }
	   public void setValue1(String value1) {
	      this.value1 = value1;
	   }
	   public int getValue2() {
	      return value2;
	   }
	   public void setValue2(int value2) {
	      this.value2 = value2;
	   }
	   @Override
	   public String toString() {
	      return "ValueDto [value1=" + value1 + ", value2=" + value2 + "]";
	   }
	}
