package com.itheima.bos.web.base.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Area;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.CourierService;
import com.itheima.bos.service.base.StandardService;
import com.itheima.bos.web.action.BaseAction;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Namespace("/courier")
@ParentPackage("struts-default")
public class CourierAction extends BaseAction<Courier> {
	
	
	CourierService courierService;
	@Resource
	StandardService standardService;
	@Resource
	public void setCourierService(CourierService courierService) {
		this.courierService = courierService;
		super.setBaseService(courierService);
	}

	@Override
	public Specification<Courier> getSpecification() {
		final Courier courier = getModel();
		// 条件的拼接
		// 条件对象
		return new Specification<Courier>() {
			@Override
			public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<>();
				if(courier.getName()!=null&& !courier.getName().equals("")){
					preList.add(cb.like(root.get("name").as(String.class), "%"+courier.getName()+"%"));
				}
				if(courier.getCourierNum()!=null&&!courier.getCourierNum().equals("")){
					
					preList.add(cb.like(root.get("courierNum").as(String.class),"%"+courier.getCourierNum()+"%"));
				}
				if(courier.getTelephone()!=null&&!courier.getTelephone().equals("")){
					preList.add(cb.like(root.get("telephone").as(String.class), "%"+courier.getTelephone()+"%"));
				}
				
				if(courier.getStandard()!=null&&!courier.getStandard().equals("")){
						if(courier.getStandard().getId()!=null&&!courier.getStandard().getId().equals("")){
							//获取一个join
							Join<Courier, Standard> join = root.join("standard",JoinType.INNER);
							//拼接一个条件
							preList.add(cb.equal(join.get("id").as(Long.class),courier.getStandard().getId()));
						}
				
				}
				
							
				
				Predicate[] arr = new Predicate[preList.size()];
				return cb.and(preList.toArray(arr));
			}
		};

	}
	//定义一个成员变量接收文件的名称,并提供set方法
		private File upload;
			
		public void setUpload(File file) {
			this.upload = file;
		}

	//文件上传
		@Action("batchImport")
		public String batchImport() throws Exception{
			System.out.println(upload);
			
			try {
				//文件上传
				//创建输入流读取Excel文件
				InputStream ips=new FileInputStream(upload);
				//读取工作簿
				HSSFWorkbook wb=new HSSFWorkbook(ips);
				//通过工作簿来读取工作单
				HSSFSheet sheet = wb.getSheetAt(0);
				
				//定义一个集合存储所有的区域信息
				List<Courier>list=new ArrayList<>();
				//遍历工作单，读取每一行
				for (Row row : sheet) {
					//跳过第一行
					if(row.getRowNum()==0){
						continue;
					}
					//通过行读取每一列
					//创建一个实例化对象
					Courier Courier =new Courier();
					Courier.setCourierNum(row.getCell(0).getStringCellValue());
					Courier.setName(row.getCell(1).getStringCellValue());
					
					Cell cell = row.getCell(2);
					if(cell!=null){
						cell.setCellType(cell.CELL_TYPE_STRING);
						Courier.setTelephone(cell.getStringCellValue());
					}
					Courier.setPda(row.getCell(3).getStringCellValue());
					
					Cell cells = row.getCell(4);
					if(cells!=null){
						cells.setCellType(cells.CELL_TYPE_STRING);
						Courier.setCheckPwd(cells.getStringCellValue());
					}
					Courier.setCompany(row.getCell(5).getStringCellValue());
					String stanName = row.getCell(6).getStringCellValue();
					Standard standard = standardService.findByNames(stanName);
					if(standard!=null){
						Courier.setStandard(standard);
					}
					//添加到集合中
					list.add(Courier);
				}
				
				//调用service层的方法
				courierService.save(list);
				//保存成功则写出数据
				result.put("success", true);
			} catch (Exception e) {			
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", e.getMessage());
			}
			return writerJson(result);
		}
		@Action("exportcourier")
		public String exportCourier() throws Exception{
			//获取拼接的条件
			Specification<Courier> spec = getSpecification();
			//调用service的方法--获取到了要导出的数据
			List<Courier> list = courierService.findAll(spec);
			System.out.println(list);
			//创建工作簿
			HSSFWorkbook wb= new HSSFWorkbook();
			//根据工作簿创建工作单
			HSSFSheet sheet = wb.createSheet("courier");
			//先创建第一行表头
			Row frow = sheet.createRow(0);
			//根据行创建每一列
			frow.createCell(0).setCellValue("快递员员工号");
			frow.createCell(1).setCellValue("姓名");
			frow.createCell(2).setCellValue("电话");
			frow.createCell(3).setCellValue("查台密码");
			frow.createCell(4).setCellValue("PDA号码");
			frow.createCell(5).setCellValue("收派标准");
			frow.createCell(6).setCellValue("公司");
			//遍历集合 根据工作单 创建每一行
			for (int i = 1; i <=list.size(); i++) {
				Row row = sheet.createRow(i);
				//获取每一个Area对象
				Courier courier=list.get(i-1);
				row.createCell(0).setCellValue(courier.getCourierNum());
				System.out.println(courier.getCourierNum());
				row.createCell(1).setCellValue(courier.getName());
				row.createCell(2).setCellValue(courier.getTelephone());
				row.createCell(3).setCellValue(courier.getCheckPwd());
				row.createCell(4).setCellValue(courier.getPda());
				Standard standard = courier.getStandard();
				if(standard!=null){
					String Name  = standard.getName();
					row.createCell(5).setCellValue(Name);	
				}
						
				row.createCell(6).setCellValue(courier.getCompany());
			}
			
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream out = response.getOutputStream();
			
			//通知浏览器以文件下载的形式处理这些数据
			String  filename="快递员导出信息表.xls";		
			//判断浏览器是否是火狐浏览器
			HttpServletRequest request = ServletActionContext.getRequest();
			String header = request.getHeader("user-agent");
			String contentDispostion = "";
			if(header.contains("Firefox")){
				contentDispostion="attachment;filename*=";
			}else{
				contentDispostion="attachment;filename=";
			}				
			response.setHeader("content-disposition",contentDispostion+URLEncoder.encode(filename,"utf-8"));
			//写出文件
			wb.write(out);
			return NONE;
		}

}
