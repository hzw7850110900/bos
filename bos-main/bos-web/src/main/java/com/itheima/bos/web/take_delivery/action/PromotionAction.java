package com.itheima.bos.web.take_delivery.action;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import com.itheima.bos.domain.take_delivery.Promotion;
import com.itheima.bos.service.take_delivery.PromotionService;
import com.itheima.bos.web.action.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/promotion")
@ParentPackage("struts-default")
public class PromotionAction extends BaseAction<Promotion> {

	PromotionService promotionService;

	@Resource
	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
		super.setBaseService(promotionService);
	}

	@Override
	public Specification<Promotion> getSpecification() {
		final Promotion promotion = getModel();
		return null;

	}

	// 提供一个变量接收表单中的文件
	private File titleImgFile;
	// 获取文件的名称
	private String titleImgFileFileName;

	public void setTitleImgFile(File titleImgFile) {
		this.titleImgFile = titleImgFile;
	}

	public void setTitleImgFileFileName(String titleImgFileFileName) {
		this.titleImgFileFileName = titleImgFileFileName;
	}

	// 重写save方法
	@Override
	@Action(value = "save", results = {
			@Result(name = "success", location = "/pages/take_delivery/promotion.jsp", type = "redirect"),
			@Result(name = "error", location = "/pages/error.jsp") })
	public String save() {
		try {
			// 获取struts封装的宣传任务对象
			Promotion promotion = getModel();
			// 设置宣传任务的状态 设置为 1 代表有效
			promotion.setStatus("1");
			if (titleImgFile != null) {
				// 获得upload文件的绝对路径
				String uploadPath = ServletActionContext.getServletContext().getRealPath("upload");
				// 生成uuid文件名
				String uuidName = UUID.randomUUID().toString();
				// 获得上传文件的文件后缀名
				String extName = titleImgFileFileName.substring(titleImgFileFileName.lastIndexOf("."));
				// 生成新的文件名称
				String fileName = uuidName + extName;
				// 保存图片到upload目录下
				FileUtils.copyFile(titleImgFile, new File(uploadPath + "/" + fileName));

				// 获得文件的相对路径
				String contextFilePath = ServletActionContext.getRequest().getContextPath() + "/upload/" + fileName;
				// 设置文件的路径
				promotion.setTitleImg(contextFilePath);
			}
			System.out.println(promotion);
			// 调用service层的方法
			promotionService.save(promotion);
			// 成功则跳转到列表页面
			return SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			// 失败则返回到提示页面
			return ERROR;
		}
	}

}
