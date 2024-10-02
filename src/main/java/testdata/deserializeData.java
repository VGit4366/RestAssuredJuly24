package testdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class deserializeData {
	private String departmentno;
	private String salary;
	private String pincode;
	private String accountno;
	private String userid;
	private String id;
	
	public deserializeData(String departmentno, String salary, String pincode, String accountno, String userid, String id){
		this.departmentno = departmentno;
		this.accountno = accountno;
		this.pincode = pincode;
		this.salary = salary;
		this.userid = userid;
		this.id = id;
	}
	
	@JsonProperty("")
	public String getAcctNo() {
		return this.accountno;
	}
	
	@JsonProperty("")
	public String getDeptNo() {
		return this.departmentno;
	}
	
	@JsonProperty("")
	public String getSalary() {
		return this.salary;
	}
	
	@JsonProperty("")
	public String getPincode() {
		return this.pincode;
	}
	
	@JsonProperty("")
	public String getUserid() {
		return this.userid;
	}
	
	@JsonProperty("")
	public String getId() {
		return this.id;
	}
}
