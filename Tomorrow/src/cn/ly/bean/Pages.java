package cn.ly.bean;

import java.util.List;

//��ҳ����
public class Pages<T> {
	
	private List<T> list;//����--����������,��ҳ����
	private int toPage;//��ҳ��
	private int count;//�ܼ�¼��
	private Integer prePage;//��һҳ
	private Integer nextPage;//��һҳ
	private int currentPage=1;//��ǰҳ(Ĭ��)
	private int pageSize=4;//ÿҳ��ʾ������
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
