package com.itheima.bos.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.base.Standard;


public interface StandardDao extends BaseDao<Standard> {
	//查询JPQL语句
	@Query("from Standard where id=?" )
	public List<Standard> findById(Long id);
	//查询SQL语句
	@Query(value="select*from T_STANDARD where name like ?",nativeQuery=true)
	public List<Standard> findByName(String name);
	@Query("from Standard where name=?" )
	public Standard findByNames( String stanName);
	//使用Query进行修改
	@Query("update Standard set name=? where id=?")
	@Modifying
	public void updateStanardById(String name,Long id);
	
	
}
