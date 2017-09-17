package com.itheima.bos.domain.base;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * Area entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_AREA"
    ,schema="ITHEIMABOS"
)
@XmlRootElement(name="area")
public class Area  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String areacode;
     private String province;
     private String city;
     private String district;
     private String postcode;
     private String citycode;
     private String shortcode;
     @JSONField(serialize=false)
     private Set<SubArea> subAreas = new HashSet<SubArea>(0);
     
     //临时字段 存储省市区的
     private String areaName;
     
   

    // Constructors
     @Transient
    public String getAreaName() {
		return province+"-"+city+"-"+district;
	}  


	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}




	/** default constructor */
    public Area() {
    }

    
    /** full constructor */
    public Area(String areacode, String province, String city, String district, String postcode, String citycode, String shortcode, Set<SubArea> subAreas) {
        this.areacode = areacode;
        this.province = province;
        this.city = city;
        this.district = district;
        this.postcode = postcode;
        this.citycode = citycode;
        this.shortcode = shortcode;
        this.subAreas = subAreas;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    @SequenceGenerator(name="generator",sequenceName="T_AREA_SEQ",allocationSize=1)
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="AREACODE", length=200)

    public String getAreacode() {
        return this.areacode;
    }
    
    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }
    
    @Column(name="PROVINCE", length=510)

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="CITY", length=510)

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="DISTRICT", length=510)

    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    @Column(name="POSTCODE", length=200)

    public String getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    @Column(name="CITYCODE", length=510)

    public String getCitycode() {
        return this.citycode;
    }
    
    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }
    
    @Column(name="SHORTCODE", length=510)

    public String getShortcode() {
        return this.shortcode;
    }
    
    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="area")

    public Set<SubArea> getSubAreas() {
        return this.subAreas;
    }
    
    public void setSubAreas(Set<SubArea> subAreas) {
        this.subAreas = subAreas;
    }
   








}