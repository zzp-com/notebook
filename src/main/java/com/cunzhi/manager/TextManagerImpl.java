package com.cunzhi.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.cunzhi.dao.FolderDao;
import com.cunzhi.dao.TextDao;
import com.cunzhi.entity.Folder;
import com.cunzhi.entity.Text;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class TextManagerImpl implements TextManager {
	@Resource
	private TextDao textDao;
	@Resource
	private FolderDao folderDao;
	
	
	
	
	/**
	 * 文档相关方法
	 */
	
	
	
	// 需要修改保存位置
	public int saveText(String str, String email) {
		JSONArray json = JSONArray.fromObject(str);
		Text text = new Text();
		for (int a = 0; a < json.size(); a++) {
			JSONObject info = (JSONObject) json.getJSONObject(a);
			text.setText_title(info.getString("title"));
			text.setText_content(info.getString("content"));
			text.setText_id(info.getString("doc_id"));
			
		}
			
		Date now=new Date();
		text.setText_revicetime(now);
		if (email == null || email == "") {
			// 邮箱为空,说明为登录,保存失败
			return -1;
		} else {
			text.setEmail(email);
		}

		// 判断id是否为空
		if (text.getText_id() == null && text.getText_id() == "") {
			return -1;
		}else {
			textDao.updateText(text);
		}
		return 1;
	}

	public int saveTitle(String title, String email,String folder_id) {
		Text text = new Text();
		String id = UUID.randomUUID().toString();
		text.setText_id(id);
		text.setText_title(title);
		if (email == null || email == "") {
			// 邮箱为空,说明为登录,保存失败
			return -1;
		}
		text.setEmail(email);
		Date now=new Date();
		text.setText_time(now);
		text.setText_revicetime(now);
		if(folder_id == null || folder_id=="") {
			folder_id="1";
		}
		text.setFolder_id(folder_id);
		textDao.saveText(text);
		return 1;
	}

	public List<Map<String, Object>> findoc(String email,String folder_id,String clickbtn) {
		//将值存入map中
		Map<String,String> map =new HashMap<String, String>();
		map.put("email", email);
		if(folder_id == null || folder_id=="") {
			folder_id="1";
		}
		map.put("folder_id", folder_id);
		
		
		if("recenedit".equals(clickbtn)) {
			
			Date now=new Date();
			long time=now.getTime();
			time-=86400000;
			now.setTime(time);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//System.out.println(sdf.format(now));
			map.put("date",sdf.format(now));
			
			return textDao.findrecendoc(map);
			
		}else if("marker".equals(clickbtn)) {
			
			
			return textDao.findmarkerdoc(map);
			
		}else {
			
			return textDao.findoc(map);
		}
		
		
	}

	//此方法不可用，需重构
	public List<Map<String, Object>> findocs(String email, String folder_id) {
		Map<String,String> map =new HashMap<String, String>();
		map.put("email", email);
		if(folder_id == null || folder_id=="") {
			folder_id="1";
		}
		map.put("folder_id", folder_id);
		Date now=new Date();
		long time=now.getTime();
		time-=86400000;
		now.setTime(time);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("date",sdf.format(now));
		
		return textDao.findocs(map);
	}
	
	
	
	
	//查询内容
	public String findContent(String id) {
		
		String str=textDao.findContent(id);
		return str;
	}
	
	//更新文档，1:标星 2：取消标星 3：重命名 4：移动到 5：删除
	public int updateDoc(String id,String text) {
		int i=0;
		Text texts = new Text();
		if("标星".equals(text)) {
			texts.setMarker(true);
			texts.setText_id(id);
			textDao.updateDoc(texts);
			i=1;
			
		}else if("删除".equals(text)) {
			texts.setText_id(id);
			textDao.delDoc(texts);
			i=5;
		}else if("移动到".equals(text)){
			
			i=4;
		}else if("取消标星".equals(text)) {
			texts.setMarker(false);
			texts.setText_id(id);
			textDao.updateDoc(texts);
			i=2;
		}else {
			//重命名
			texts.setText_id(id);
			texts.setText_title(text);
			textDao.updateDocName(texts);
			i=3;
			return i;
		}
		i=-1;
		
		return i;
	}
	
	
	
	
	
	
/**
 * 文件夹相关方法
 */
	
	
	
	
	public int saveFolder(String title, String email,String folder_aid) {
		Folder folder=new Folder();
		String id = UUID.randomUUID().toString();
		folder.setFolder_id(id);
		folder.setFolder_title(title);
		if (email == null || email == "") {
			// 邮箱为空,说明为登录,保存失败
			return -1;
		}
		folder.setEmail(email);
		Date now=new Date();
		folder.setFolder_time(now);
		if(folder_aid == null || folder_aid=="") {
			folder_aid="1";
		}
		folder.setFolder_aid(folder_aid);
		folderDao.saveFolder(folder);
		return 1;
	}

public List<Map<String, Object>> findFolder(String email,String folder_aid,String clickbtn) {
	Map<String,String> map =new HashMap<String, String>();
	if(folder_aid == null || folder_aid=="") {
		folder_aid="1";
	}
	map.put("folder_aid", folder_aid);
	map.put("email", email);
	if("marker".equals(clickbtn)) {
		return folderDao.findMarkerFolder(map);
	}
	return folderDao.findFolder(map);
}

//更新文档，1:重命名  2：删除
	public int updateFol(String id,String text) {
		int i=0;
		Folder fol=new Folder();
		if("删除".equals(text)) {
			fol.setFolder_id(id);
			folderDao.delFol(fol);
			folderDao.delaFol(fol);
			folderDao.deltFol(fol);
			i=2;
			return i;
		}else {
			//重命名
			fol.setFolder_id(id);
			fol.setFolder_title(text);
			folderDao.updateFolName(fol);
			i=1;
			return i;
		}
		
	}


	
	

	

}
