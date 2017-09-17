package com.itheima.testStandard;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.StandardDao;
import com.itheima.bos.domain.base.Standard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo1 {
	@Resource
	StandardDao standardDao;

	@Test
	@Transactional
	@Rollback(false)
	public void test01() {

		Standard standard = new Standard();
		standard.setName("20-30斤的标准");
		standard.setMaxLength(30L);
		standard.setMinWeight(25L);
		standard.setMaxWeight(30L);
		standard.setMinLength(25L);
		standardDao.save(standard);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void test02() {

		Standard standard = new Standard();
		standard.setId(8L);
		standard.setName("25-30斤的标准");
		standard.setMaxLength(30L);
		standard.setMinWeight(25L);
		standard.setMaxWeight(30L);
		standard.setMinLength(25L);
		standardDao.saveAndFlush(standard);
		
	}
	@Test
	@Transactional
	@Rollback(false)
	public void test03() {

		
	}
}
