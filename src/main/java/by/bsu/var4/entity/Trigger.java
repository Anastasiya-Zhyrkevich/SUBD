package by.bsu.var4.entity;

public class Trigger {
	private boolean en;
	private String name;
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
	
	public Trigger(String name) {
		super();
		this.en = false;
		this.name = name;
	}
	
	

}
