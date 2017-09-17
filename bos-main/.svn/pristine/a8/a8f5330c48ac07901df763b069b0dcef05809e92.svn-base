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
import com.itheima.bos.dao.system.UserDao;
import com.itheima.bos.domain.system.Resources;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.imp.BaseServiceImp;
import com.itheima.bos.service.system.RoleService;

@Service("roleService")
public class RoleServiceImp extends BaseServiceImp<Role> implements RoleService {

	RoleDao roleDao;

	// 给父类的baseDao对象赋值
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		super.setBaseDao(roleDao);
	}

	// 注入权限资源的dao对象
	@Resource
	ResourcesDao resourcesDao;

	@Override
	public void bindResoureces(Long roleId, String resId) {
		// 获取当前角色持久态对象
		Role role = roleDao.findOne(roleId);
		// 首先要清空当前角色之前绑定的所有的id
		role.setResourceses(null);

		// 创建一个set集合存储所有要绑定的资源对象
		Set<Resources> resources = new HashSet<>();

		// 处理资源id的字符串
		String[] resIdArr = resId.split(",");

		// 遍历
		for (String rid : resIdArr) {
			// 通过资源id查询资源对象
			Resources res = resourcesDao.findOne(Long.parseLong(rid));
			// 存储到集合中
			resources.add(res);
		}
		// 角色绑定资源
		role.setResourceses(resources);

	}
	
	@Resource
	UserDao userDao;

	@Override
	public Page<Role> findByPage(Specification<Role> spec, Pageable pageable, Long userId) {
		// 查询当前用户并获取该用户绑定过的角色
		User user = userDao.findOne(userId);
		// 该角色绑定的角色
		Set<Role> roleSet = user.getRoles();
		// 获取所有的角色id 并存入集合中
		Set<Long> roleIdSet = new HashSet<>();
		for (Role role : roleSet) {
			roleIdSet.add(role.getId());
		}
		// 获取分页对象
		Page<Role> page = roleDao.findAll(spec, pageable);

		// 循环遍历分页数据
		List<Role> resList = page.getContent();
		for (Role role : resList) {
			// 判断在绑定过的资源的id集合中是否包含当前资源 如果是 给当前资源设置一个checked属性 为true
			if (roleIdSet.contains(role.getId())) {
				role.setChecked(true);
			}
		}

		return page;
	}

}
