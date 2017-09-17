package com.itheima.testStandard;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import com.itheima.crm.domain.Customer;

public class WebActionTest {

	@Test
	public void test01(){
		List<com.itheima.crm.domain.Customer> list = (List<Customer>) WebClient
				.create("http://localhost:9081/crm-web/services/customerService/findNoAssociateCustomers")
				.accept(MediaType.APPLICATION_JSON)
				.getCollection(Customer.class);
		
		for (Customer customer : list) {
			System.out.println(customer);
		}
	}
}	
