package com.itheima.testStandard;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.StandardDao;
import com.itheima.bos.domain.base.Standard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo2 {
	@Resource
	StandardDao standardDao;

	//测试查询JPQL语句
	@Test
	@Transactional
	@Rollback(false)
	public void test01() {

		List<Standard> list = standardDao.findById(31L);
		
		for (Standard standard : list) {
			System.out.println(standard);
		}
	}

	//使用@Query查询SQL语句
	@Test
	@Transactional
	@Rollback(false)
	public void test02(){
		List<Standard> list = standardDao.findByName("%20%");
		
		for (Standard standard : list) {
			System.out.println(standard);
		}
	}
	
	//使用query进行修改数据
	@Test
	@Transactional
	@Rollback(false)
	public void test03(){
		
		standardDao.updateStanardById("100-100斤的标准", 31L);
				
	}
	
	//测试PagingAndSortingRepository接口的排序
	@Test
	@Transactional
	@Rollback(false)
	public void test04(){
		//单个条件排序：通过id倒序
	//	List<Standard> list = standardDao.findAll(new Sort(Direction.DESC, "id"));
		//多个条件排序：按照id倒序，按照minLength升序
		List<Standard> list = standardDao.findAll(new Sort(new Order(Direction.DESC,"id"),new Order(Direction.ASC,"minLength")));
		for (Standard standard : list) {
			System.err.println(standard);
		}
	}
	
}
