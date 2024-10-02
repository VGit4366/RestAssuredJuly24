package testdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class addUser {
	private String departmentno;
	private String salary;
	private String pincode;
	private String accountno;
	
	public addUser(String departmentno, String salary, String pincode, String accountno){
		this.departmentno = departmentno;
		this.accountno = accountno;
		this.pincode = pincode;
		this.salary = salary;
	}
	
	@JsonProperty
	public String getAcctNo() {
		return this.accountno;
	}
	
	@JsonProperty
	public String getDeptNo() {
		return this.departmentno;
	}
	
	@JsonProperty
	public String getSalary() {
		return this.salary;
	}
	
	@JsonProperty
	public String getPincode() {
		return this.pincode;
	}
}
