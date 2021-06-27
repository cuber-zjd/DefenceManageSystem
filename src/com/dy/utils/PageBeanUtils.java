package com.dy.utils;

import java.util.List;



public class PageBeanUtils {
	private int prepage;
	private int nextpage;
	private int firstpage;
	private int lastpage;
	private int currentpage;
	private int totalpage;
	private int pagesize;
	private int totalData;
	private List pageData;
	public int getPrepage() {
		return prepage;
	}
	public List getPageData() {
		return pageData;
	}
	public void setPageData(List pageData) {
		this.pageData = pageData;
	}
	public void setPrepage(int prepage) {
		this.prepage = prepage;
	}
	public int getNextpage() {
		return nextpage;
	}
	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}
	public int getFirstpage() {
		return firstpage;
	}
	public void setFirstpage(int firstpage) {
		this.firstpage = firstpage;
	}
	public int getLastpage() {
		return lastpage;
	}
	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotalData() {
		return totalData;
	}
	public void setTotalData(int totalData) {
		this.totalData = totalData;
	}
	public PageBeanUtils(int currentpage,int pagesize,int totalData) {
		this.currentpage=currentpage;
		this.pagesize=pagesize;
		this.totalData=totalData;
		
		
		this.totalpage=this.lastpage=(int)Math.ceil((double)totalData/pagesize);
		
		this.currentpage=Math.max(this.currentpage, 1);
		this.currentpage=Math.min(this.totalpage, this.currentpage);
		this.prepage=Math.max(this.currentpage-1, 1);
		this.nextpage=Math.min(this.currentpage+1,this.totalpage);
	}
	
}
