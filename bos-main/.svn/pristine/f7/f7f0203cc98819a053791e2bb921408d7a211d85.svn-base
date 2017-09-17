package com.itheima.bos.domain.system;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Resources entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_RESOURCE", schema = "ITHEIMABOS")
public class Resources implements java.io.Serializable {

	// Fields

	private Long id;
	private String name; //资源/权限名称
	private String grantKey;  //权限关键字
	private String pageUrl;  //权限的url  只有菜单才会有
	private Long seq;	//排序
	private String resourceType;   //资源的类型  0 是菜单  1是按钮
	private String icon;   //资源的图标  只有菜单才会有
	@JSONField(name="_parentId")
	private Long pid;   //父资源的id 
	private Boolean open;   //当前菜单展开的状态
	
	@JSONField(serialize=false)
	private Set<Role> roles = new HashSet<Role>(0);  //与角色表多对多的映射

	
	//加上临时字段checked ,用于在页面当中回显已经绑定的资源
	private Boolean checked;	
	@Transient
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/** default constructor */
	public Resources() {
	}

	/** full constructor */
	public Resources(String name, String grantKey, String pageUrl, Long seq,
			String resourceType, String icon, Long pid, Boolean open,
			Set<Role> roles) {
		this.name = name;
		this.grantKey = grantKey;
		this.pageUrl = pageUrl;
		this.seq = seq;
		this.resourceType = resourceType;
		this.icon = icon;
		this.pid = pid;
		this.open = open;
		this.roles = roles;
	}

	// Property accessors
	@SequenceGenerator(name = "generator",sequenceName="T_RESOURCE_SEQ",allocationSize=1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GRANT_KEY", length = 200)
	public String getGrantKey() {
		return this.grantKey;
	}

	public void setGrantKey(String grantKey) {
		this.grantKey = grantKey;
	}

	@Column(name = "PAGE_URL", length = 510)
	public String getPageUrl() {
		return this.pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	@Column(name = "SEQ", precision = 10, scale = 0)
	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	@Column(name = "RESOURCE_TYPE", length = 20)
	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "ICON", length = 200)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "PID", precision = 10, scale = 0)
	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Column(name = "OPEN", precision = 1, scale = 0)
	public Boolean getOpen() {
		return this.open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resourceses")
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Resources [id=" + id + ", name=" + name + ", grantKey=" + grantKey + ", pageUrl=" + pageUrl + ", seq="
				+ seq + ", resourceType=" + resourceType + ", icon=" + icon + ", pid=" + pid + ", open=" + open
				+ ", roles=" + roles + ", checked=" + checked + "]";
	}
	
	

}