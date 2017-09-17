package com.itheima.bos.web.take_delivery.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.web.action.BaseAction;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Namespace("/image")
@ParentPackage("struts-default")
public class ImageAction extends BaseAction<Object> {

	@Override
	public Specification<Object> getSpecification() {

		return null;
	}

	// 接收要上传的文件
	private File imgFile;

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	// 获取要上传的文件名称
	private String imgFileFileName;

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	// 文件上传的处理方法
	@Action("upload")
	public String upload() {
		try {
			//先获取项目下要存储图片的路径upload的绝对路径
			String uploadPath = ServletActionContext.getServletContext().getRealPath("upload");
			//生成唯一的文件名
			String uuidName=UUID.randomUUID().toString();
			//截取上传的文件的后缀名 --从最后一个点开始截取到末尾
			String extName=imgFileFileName.substring(imgFileFileName.lastIndexOf("."));
			//获取文件名
			String fileName=uuidName+extName;
			//将上传的文件名赋值到项目下的upload
			//1.复制源公司
			//2.存储的目标位置
			FileUtils.copyFile(imgFile, new File(uploadPath+"/"+fileName));	
			
			//构建该文件的项目的相对路径
			String contextPath = ServletActionContext.getRequest().getContextPath()+"/upload/"+fileName;
			
			result.put("error", 0);
			//文件名的相对路径
			result.put("url", contextPath);
			
		} catch (IOException e) {			
			e.printStackTrace();
			result.put("error", 1);
			result.put("message", e.getMessage());
		}
		return writerJson(result);
	
	}
	//图片空间管理
	@Action("manager")
	public String manager(){

		//获得upload绝对路径
		String currentPath = ServletActionContext.getServletContext().getRealPath("upload");
		//获得图片根目录的相对路径
		String currentUrl  = ServletActionContext.getRequest().getContextPath()+"/upload/";
		//图片扩展名
		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

		//设置upload目录
		File currentPathFile=new File(currentPath);

		//设计一个list集合存储所有需要返回的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		//判断upload目录下的文件是否为空
		if(currentPathFile.listFiles() != null) {
			//遍历目录取的文件信息
			for (File file : currentPathFile.listFiles()) {
				//创建一个hashable存放每个文件的信息
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				//获得当前文件名
				String fileName = file.getName();
				//判断该节点是不是文件夹
				if(file.isDirectory()) {
					//是否为目录
					hash.put("is_dir", true);
					//是否还有字文件
					hash.put("has_file", (file.listFiles() != null));
					//文件大小
					hash.put("filesize", 0L);
					//是否有拖
					hash.put("is_photo", false);
					//文件类型 文件夹则为空
					hash.put("filetype", "");
				} else if(file.isFile()){
					//获得文件的后缀名称
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					//是否是目录
					hash.put("is_dir", false);
					//是否为文件
					hash.put("has_file", false);
					//文件大小
					hash.put("filesize", file.length());
					//是否是图片
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					//图片类型
					hash.put("filetype", fileExt);
				}
				//将文件名称存储进集合
				hash.put("filename", fileName);
				//图片的最后更新时间
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				//添加到最终的存储集合中
				fileList.add(hash);
			}
		}
		//上一级的目录路径
		result.put("moveup_dir_path", "");
		//upload上传的相对路径
		result.put("current_url", currentUrl);
		//文件的总数量
		result.put("total_count", fileList.size());
		//返回的文件信息
		result.put("file_list", fileList);
		return writerJson(result);		
	}
	
		//接收需要删除的图片地址
		private String url;
		
		public void setUrl(String url) {
			this.url = url;
		}
	
		//扩展删除功能
		@Action("delete")
		public String delete() throws IOException{
			System.out.println(url);
			//获得到文件的绝对路径
			String filePath = ServletActionContext.getServletContext().getRealPath(url.substring(url.lastIndexOf("upload")));
			
			//读取文件
			File file=new File(filePath);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			//判空
			if(file!=null){
				//删除
				file.delete();
				//删除成功--返回值位1  则代表成功
				response.getWriter().write("1");
			}else{
				response.getWriter().write("-1");
			}
			return NONE;						
		}

}
