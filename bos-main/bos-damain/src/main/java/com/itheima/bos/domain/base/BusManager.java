package com.itheima.bos.domain.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * BusManager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BUS_MANAGER", schema = "ITHEIMABOS")
public class BusManager implements java.io.Serializable {

	// Fields

	private Long id;
	private String lineType;
	private String plateNumber;
	private String carrier;
	private String vehicleType;
	private String driver;
	private Long telephone;
	private Long tonControl;
	private String remark;

	// Constructors

	/** default constructor */
	public BusManager() {
	}

	/** full constructor */
	public BusManager(String lineType, String plateNumber, String carrier,
			String vehicleType, String driver, Long telephone, Long tonControl,
			String remark) {
		this.lineType = lineType;
		this.plateNumber = plateNumber;
		this.carrier = carrier;
		this.vehicleType = vehicleType;
		this.driver = driver;
		this.telephone = telephone;
		this.tonControl = tonControl;
		this.remark = remark;
	}

	// Property accessors
	@SequenceGenerator(name = "generator",sequenceName="T_BUS_MANAGER_SEQ",allocationSize=1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "LINE_TYPE", length = 510)
	public String getLineType() {
		return this.lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	@Column(name = "PLATE_NUMBER", length = 510)
	public String getPlateNumber() {
		return this.plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	@Column(name = "CARRIER", length = 510)
	public String getCarrier() {
		return this.carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	@Column(name = "VEHICLE_TYPE", length = 510)
	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Column(name = "DRIVER", length = 510)
	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	@Column(name = "TELEPHONE", precision = 10, scale = 0)
	public Long getTelephone() {
		return this.telephone;
	}

	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}

	@Column(name = "TON_CONTROL", precision = 10, scale = 0)
	public Long getTonControl() {
		return this.tonControl;
	}

	public void setTonControl(Long tonControl) {
		this.tonControl = tonControl;
	}

	@Column(name = "REMARK", length = 510)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}