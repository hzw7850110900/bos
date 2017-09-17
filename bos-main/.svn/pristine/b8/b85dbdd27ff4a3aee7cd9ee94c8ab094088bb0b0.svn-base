package com.itheima.bos.domain.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TakeDeliveryTime entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_TAKE_DELIVERY_TIME", schema = "ITHEIMABOS")
public class TakeDeliveryTime implements java.io.Serializable {

	// Fields

	private Long id;
	private Courier courier;
	private String timeName;
	private String peaWorkingTime;
	private String peaClosingTime;
	private String staWorkingTime;
	private String staClosingTime;
	private String sunWorkingTime;
	private String sunClosingTime;

	// Constructors

	/** default constructor */
	public TakeDeliveryTime() {
	}

	/** full constructor */
	public TakeDeliveryTime(Courier courier, String timeName,
			String peaWorkingTime, String peaClosingTime,
			String staWorkingTime, String staClosingTime,
			String sunWorkingTime, String sunClosingTime) {
		this.courier = courier;
		this.timeName = timeName;
		this.peaWorkingTime = peaWorkingTime;
		this.peaClosingTime = peaClosingTime;
		this.staWorkingTime = staWorkingTime;
		this.staClosingTime = staClosingTime;
		this.sunWorkingTime = sunWorkingTime;
		this.sunClosingTime = sunClosingTime;
	}

	// Property accessors
	@SequenceGenerator(name = "generator",sequenceName="T_TAKE_DELIVERY_TIME_SEQ",allocationSize=1)
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
	@JoinColumn(name = "COURIER")
	public Courier getCourier() {
		return this.courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	@Column(name = "TIME_NAME", length = 200)
	public String getTimeName() {
		return this.timeName;
	}

	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}

	@Column(name = "PEA_WORKING_TIME", length = 200)
	public String getPeaWorkingTime() {
		return this.peaWorkingTime;
	}

	public void setPeaWorkingTime(String peaWorkingTime) {
		this.peaWorkingTime = peaWorkingTime;
	}

	@Column(name = "PEA_CLOSING_TIME", length = 200)
	public String getPeaClosingTime() {
		return this.peaClosingTime;
	}

	public void setPeaClosingTime(String peaClosingTime) {
		this.peaClosingTime = peaClosingTime;
	}

	@Column(name = "STA_WORKING_TIME", length = 200)
	public String getStaWorkingTime() {
		return this.staWorkingTime;
	}

	public void setStaWorkingTime(String staWorkingTime) {
		this.staWorkingTime = staWorkingTime;
	}

	@Column(name = "STA_CLOSING_TIME", length = 200)
	public String getStaClosingTime() {
		return this.staClosingTime;
	}

	public void setStaClosingTime(String staClosingTime) {
		this.staClosingTime = staClosingTime;
	}

	@Column(name = "SUN_WORKING_TIME", length = 200)
	public String getSunWorkingTime() {
		return this.sunWorkingTime;
	}

	public void setSunWorkingTime(String sunWorkingTime) {
		this.sunWorkingTime = sunWorkingTime;
	}

	@Column(name = "SUN_CLOSING_TIME", length = 200)
	public String getSunClosingTime() {
		return this.sunClosingTime;
	}

	public void setSunClosingTime(String sunClosingTime) {
		this.sunClosingTime = sunClosingTime;
	}

}