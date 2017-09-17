package com.itheima.crm.Service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.itheima.crm.domain.Customer;

public interface CustomerService {
	//查询没有被定区管理的客户
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findNoAssociateCustomers")
	public List<Customer> findNoAssociateCustomers();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findHasAssociateCustomers")
	//查询被某个定区Id关联的客户
	public List<Customer> findHasAssociateCustomers(@QueryParam("fixedAreaId") Long fixedAreaId);
	
	@PUT
	@Path("/associateCustomersToFixedArea")
	public void associateCustomersToFixedArea(@QueryParam("fixedAreaId") Long fixedAreaId,@QueryParam("customerIds") String customerIds);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/checkPhoneIsExist")
	public List<Customer> checkPhoneIsExist(@QueryParam("telephone") String telephone);
	
	@POST
	@Path("save")
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveCustomer(Customer customer);
	
	@PUT
	@Path("activeCustomer")
	public void activeCustomer(@QueryParam("telephone") String telephone);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Customer login(@QueryParam("telephone")String telephone,@QueryParam("password")String password);

	//通过地址匹配客户
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findByAddress/{address}")
	public Customer findByAddress(@PathParam("address")String address);
	
	//通过
	//根据客户id查询客户
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findCustomerById")
	public Customer findCustomerById(@QueryParam("customerId") Long customerId);
}
