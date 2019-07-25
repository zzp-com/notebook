package com.cunzhi.dao;

import java.util.List;
import java.util.Map;

import com.cunzhi.entity.Text;

public interface TextDao {
	public int saveText(Text text);
	public int updateText(Text text);
	public List<Map<String,Object>> findoc(Map map);
	public String findContent(String id);
	public int updateDoc(Text text);
	public List<Map<String,Object>> findocs(Map map);
	public List<Map<String,Object>> findrecendoc(Map map);
	public List<Map<String,Object>> findmarkerdoc(Map map);
	
	public int delDoc(Text text);
	public int updateDocName(Text text);
	
}
