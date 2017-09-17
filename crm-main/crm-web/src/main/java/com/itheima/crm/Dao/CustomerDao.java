package com.itheima.crm.Dao;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.crm.domain.Customer;



public interface CustomerDao extends JpaRepository<Customer, Long> {

	public List<Customer> findByfixedAreaIdIsNull();
	
	public List<Customer> findByfixedAreaId(Long fixedAreaId);

	@Query("update Customer set fixedAreaId=null Where fixedAreaId=?")
	@Modifying
	public void clearCustomerByFixedArea(Long fixedAreaId);

	@Query("update Customer set fixedAreaId=? where id=?")
	@Modifying
	public void associateCustomerToFixedArea(Long fixedAreaId, Long customerId);

	public List<Customer> findByTelephone(String telePhone);

	@Query("update Customer set type='1' where  telephone=?")
	@Modifying
	public void activeCustomer(String telephone);
	
	@Query("from Customer where telephone=? and password=?")
	public Customer login(String telephone,String password);

	public Customer findByAddress(String address);

	public Customer findById(Long customerId);
}
