package cn.ly.bean;

import java.util.List;

//分页对象
public class Pages<T> {
	
	private List<T> list;//泛型--参数化类型,分页数据
	private int toPage;//总页数
	private int count;//总记录数
	private Integer prePage;//上一页
	private Integer nextPage;//下一页
	private int currentPage=1;//当前页(默认)
	private int pageSize=4;//每页显示的条数
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getToPage() {
		return  (count%pageSize==0?count/pageSize:count/pageSize+1);
	}
	public void setToPage(int toPage) {
		this.toPage = toPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Integer getPrePage() {
		return (currentPage-1>0?currentPage-1:null);
	}
	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}
	public Integer getNextPage() {
		return (currentPage+1>getToPage()?null:currentPage+1);
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
