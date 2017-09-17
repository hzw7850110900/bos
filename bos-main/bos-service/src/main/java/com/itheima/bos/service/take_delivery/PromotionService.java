package com.itheima.bos.service.take_delivery;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;

import com.itheima.bos.domain.take_delivery.PageBean;
import com.itheima.bos.domain.take_delivery.Promotion;
import com.itheima.bos.service.BaseService;

public interface PromotionService extends BaseService<Promotion> {
	
	//页面展示宣传任务的分页丰富
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/queryByPagePromotion")
	public  PageBean<Promotion> queryByPagePromotion(@QueryParam("page")Integer page,@QueryParam("pageSize")Integer pageSize);

	public void updateStatus();
	
}
