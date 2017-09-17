package com.itheima.bos.service.system.imp;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.itheima.bos.dao.system.ResourcesDao;
import com.itheima.bos.dao.system.RoleDao;
import com.itheima.bos.domain.system.Resources;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.service.imp.BaseServiceImp;
import com.itheima.bos.service.system.ResourcesService;

@Service("resourcesService")
public class ResourcesServiceImp extends BaseServiceImp<Resources> implements ResourcesService {
	
	ResourcesDao resourcesDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setResourcesDao(ResourcesDao resourcesDao) {
		this.resourcesDao = resourcesDao;
		super.setBaseDao(resourcesDao);
	}
	
	@Resource
	RoleDao roleDao;
	
	@Override
	public Page<Resources> findByPage(Specification<Resources> spec, Pageable pageable, Long roleId) {
		//查询当前角色并获取该角色绑定过的资源
		Role role = roleDao.findOne(roleId);
		//该角色绑定的资源
		Set<Resources> resSet = role.getResourceses();
		//获取所有的资源id 并存入集合中
		Set<Long>resIdSet=new HashSet<>();
		for (Resources resource : resSet) {
			resIdSet.add(resource.getId());
		}
		//获取分页对象
		Page<Resources> page = resourcesDao.findAll(spec, pageable);
		
		//循环遍历分页数据
		List<Resources> resList = page.getContent();
		for (Resources resource : resList) {
			//判断在绑定过的资源的id集合中是否包含当前资源 如果是 给当前资源设置一个checked属性 为true
			if(resIdSet.contains(resource.getId())){
				resource.setChecked(true);
			}
		}
		
		return page;
	}
	
	
	
	

	
	
}
