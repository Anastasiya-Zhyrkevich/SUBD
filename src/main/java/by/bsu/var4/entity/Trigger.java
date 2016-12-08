package by.bsu.var4.entity;

public class Trigger {
	private boolean en;
	private String name;
	private String state;
	private String desc;
	
	public boolean isEn() {
		return en;
	}
	public void setEn(boolean en) {
		this.en = en;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Trigger(String name, String desc) {
		super();
		this.en = false;
		this.state = "disabled";
		this.name = name;
		this.desc = desc;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	

}
