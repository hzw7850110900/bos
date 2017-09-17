package com.itheima.crm.Service.imp;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.Dao.CustomerDao;
import com.itheima.crm.Service.CustomerService;
import com.itheima.crm.domain.Customer;
@Service("customerService")
@Transactional
public class CustomerServiceImp implements CustomerService {
	@Resource
	CustomerDao customerDao;
	
	@Override
	public List<Customer> findNoAssociateCustomers() {
		return customerDao.findByfixedAreaIdIsNull();
	}

	@Override
	public List<Customer> findHasAssociateCustomers(Long fixedAreaId) {
		return customerDao.findByfixedAreaId(fixedAreaId);
	}

	@Override
	public void associateCustomersToFixedArea(Long fixedAreaId, String customerIds) {
		//先清空当前定区关联的用户
		customerDao.clearCustomerByFixedArea(fixedAreaId);
		
		//获取所有的客户id 
		String[] customerIdArr = customerIds.split(",");
		for (String customerId : customerIdArr) {
			//调用dao层的方法关联客户
			customerDao.associateCustomerToFixedArea(fixedAreaId,Long.parseLong(customerId));
		}
		
	}

	//保存客户
	@Override
	public List<Customer> checkPhoneIsExist(String telephone) {
		
		return customerDao.findByTelephone(telephone);
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		customerDao.save(customer);
		
	}

	@Override
	public void activeCustomer(String telephone) {
		
		customerDao.activeCustomer(telephone);
	}

	@Override
	public Customer login(String telephone, String password) {
		
		Customer customer = customerDao.login(telephone, password);
						
		return customer;
	}

	@Override
	public Customer findByAddress(String address) {
		
		return customerDao.findByAddress(address);
	}
	
	//通过id查询客户
	@Override
	public Customer findCustomerById(Long customerId) {
		return customerDao.findById(customerId);
	}
}
