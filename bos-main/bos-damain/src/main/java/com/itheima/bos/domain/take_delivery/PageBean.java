package com.itheima.bos.domain.take_delivery;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
@XmlRootElement(name="page")
@XmlSeeAlso(value={Promotion.class})
public class PageBean<T> {

	private List<T> list;
	
	private Long total;

	public List<T> getList() {
		return list;
	}

	public Long getTotal() {
		return total;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageBean(List<T> list, Long total) {
		super();
		this.list = list;
		this.total = total;
	}
	
	
}
