package com.itheima.bos.domain.take_delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.itheima.bos.domain.base.Courier;

/**
 * WorkBill entity. @author MyEclipse Persistence Tools
 * 
 * 取件工单
 */
@Entity
@Table(name = "T_WORK_BILL", schema = "ITHEIMABOS")
@XmlRootElement(name="workBill")
public class WorkBill implements java.io.Serializable {

	// Fields

	private Long id;
	private Order order; //所属订单
	private String pickstate; //取件状态 1.未通知  2.已通知   3.已取件
	private Date buildtime;  //工单创建时间  
	private String remark;  // 备注
	
	private Courier courier;  //取件的快递员
	

	// Constructors
	@ManyToOne
	@JoinColumn(name="courier_id")
	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	/** default constructor */
	public WorkBill() {
	}

	/** full constructor */
	public WorkBill(Order order, String pickstate, Date buildtime,
			String remark) {
		this.order = order;
		this.pickstate = pickstate;
		this.buildtime = buildtime;
		this.remark = remark;
	}

	// Property accessors
	@SequenceGenerator(name = "generator",sequenceName="T_WORK_BILL_SEQ",allocationSize=1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Column(name = "PICKSTATE", length = 20)
	public String getPickstate() {
		return this.pickstate;
	}

	public void setPickstate(String pickstate) {
		this.pickstate = pickstate;
	}

	@Column(name = "BUILDTIME")
	public Date getBuildtime() {
		return this.buildtime;
	}

	public void setBuildtime(Date buildtime) {
		this.buildtime = buildtime;
	}

	@Column(name = "REMARK", length = 510)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}