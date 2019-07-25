package com.cunzhi.manager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cunzhi.entity.Text;

public interface TextManager {
	public int saveText(String text,String email);//保存文档内容
	public int saveTitle(String title, String email,String folder_id);//创建文档
	public List<Map<String, Object>> findoc(String email,String folder_id,String clickbtn);
	//public List<Map<String, Object>> findocs(String email,String folder_id);//点击最近编辑
	public String findContent(String id);
	public int updateDoc(String id,String text);
	
	//文件夹相关方法
	public int saveFolder(String title, String email,String folder_aid);//创建文件夹
	public List<Map<String, Object>> findFolder(String email,String folder_aid,String clickbtn);
	public int updateFol(String id,String text);
	
}
