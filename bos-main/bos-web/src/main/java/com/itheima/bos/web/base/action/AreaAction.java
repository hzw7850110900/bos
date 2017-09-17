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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Area;
import com.itheima.bos.service.base.AreaService;
import com.itheima.bos.utils.PinYin4jUtils;
import com.itheima.bos.web.action.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/area")
@ParentPackage("struts-default")
public class AreaAction extends BaseAction<Area> {
	
	
	AreaService areaService;
		
	@Resource
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
		super.setBaseService(areaService);
	}

	@Override
	public Specification<Area> getSpecification() {
		final Area area = getModel();
		// 条件的拼接
		// 条件对象
		return new Specification<Area>() {
			@Override
			public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<>();
				if(area.getProvince()!=null&& !area.getProvince().equals("")){
					preList.add(cb.like(root.get("province").as(String.class), "%"+area.getProvince()+"%"));
				}
				if(area.getCity()!=null&&!area.getCity().equals("")){
					
					preList.add(cb.like(root.get("city").as(String.class),"%"+area.getCity()+"%"));
				}
				if(area.getDistrict()!=null&&!area.getDistrict().equals("")){
					preList.add(cb.like(root.get("district").as(String.class), "%"+area.getDistrict()+"%"));
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
		
		try {
			//文件上传
			//创建输入流读取Excel文件
			InputStream ips=new FileInputStream(upload);
			//读取工作簿
			HSSFWorkbook wb=new HSSFWorkbook(ips);
			//通过工作簿来读取工作单
			HSSFSheet sheet = wb.getSheetAt(0);
			
			//定义一个集合存储所有的区域信息
			List<Area>list=new ArrayList<>();
			//遍历工作单，读取每一行
			for (Row row : sheet) {
				//跳过第一行
				if(row.getRowNum()==0){
					continue;
				}
				//通过行读取每一列
				//创建一个实例化对象
				Area area =new Area();
				area.setAreacode(row.getCell(0).getStringCellValue());
				area.setProvince(row.getCell(1).getStringCellValue());
				area.setCity(row.getCell(2).getStringCellValue());
				area.setDistrict(row.getCell(3).getStringCellValue());
				area.setPostcode(row.getCell(4).getStringCellValue());
				
				
				
				//区域简码的处理
				String province=area.getProvince().substring(0, area.getProvince().length()-1);
				String city=area.getCity().substring(0, area.getCity().length()-1);
				String district=area.getDistrict().substring(0, area.getDistrict().length()-1);
				//调用pinyin4j工具类处理区域简码
				String[] codeArr = PinYin4jUtils.getHeadByString(province+city+district);
				//把字符串数组使用拼接符连接起来
				String shortcode = PinYin4jUtils.stringArrayToString(codeArr, "");
				area.setShortcode(shortcode);
				
				//处理城市编码,并且处理空格
				String citycode = PinYin4jUtils.hanziToPinyin(province).replaceAll(" ", "");
				area.setCitycode(citycode);
				//添加到集合中
				list.add(area);
			}
			
			//调用service层的方法
			areaService.save(list);
			//保存成功则写出数据
			result.put("success", true);
		} catch (Exception e) {			
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		return writerJson(result);
	}
	@Action("exportArea")
	public String exportArea() throws Exception{
		//获取拼接的条件
		Specification<Area>spec=getSpecification();
		//调用service的方法--获取到了要导出的数据
		List<Area> list = areaService.findAll(spec);
		//创建工作簿
		HSSFWorkbook wb= new HSSFWorkbook();
		//根据工作簿创建工作单
		HSSFSheet sheet = wb.createSheet("area");
		//先创建第一行表头
		Row frow = sheet.createRow(0);
		//根据行创建每一列
		frow.createCell(0).setCellValue("区域编码");
		frow.createCell(1).setCellValue("省份");
		frow.createCell(2).setCellValue("城市");
		frow.createCell(3).setCellValue("区域");
		frow.createCell(4).setCellValue("邮编");
		frow.createCell(5).setCellValue("区域简码");
		frow.createCell(6).setCellValue("城市编码");
		
		//遍历集合 根据工作单 创建每一行
		for (int i = 1; i <=list.size(); i++) {
			Row row = sheet.createRow(i);
			//获取每一个Area对象
			Area area=list.get(i-1);
			row.createCell(0).setCellValue(area.getAreacode());
			row.createCell(1).setCellValue(area.getProvince());
			row.createCell(2).setCellValue(area.getCity());
			row.createCell(3).setCellValue(area.getDistrict());
			row.createCell(4).setCellValue(area.getPostcode());
			row.createCell(5).setCellValue(area.getShortcode());
			row.createCell(6).setCellValue(area.getCitycode());			
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream out = response.getOutputStream();
		
		//通知浏览器以文件下载的形式处理这些数据
		String  filename="区域信息表.xls";		
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
