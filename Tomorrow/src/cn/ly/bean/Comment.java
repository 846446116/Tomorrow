package cn.ly.bean;

public class Comment {
	private int id;
	private String name;
	private int price;
	private int num;
	private String site;
	private String exclusive;
	public String getExclusive() {
		return exclusive;
	}
	public void setExclusive(String exclusive) {
		this.exclusive = exclusive;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", price=" + price + ", num=" + num + ", site=" + site
				+ ", exclusive=" + exclusive + "]";
	}

	
}
