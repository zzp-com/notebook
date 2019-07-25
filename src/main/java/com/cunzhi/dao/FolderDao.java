package com.cunzhi.dao;

import java.util.List;
import java.util.Map;

import com.cunzhi.entity.Folder;
import com.cunzhi.entity.Text;

public interface FolderDao {
	public int saveFolder(Folder folder);
	public List<Map<String,Object>> findFolder(Map map);
	public List<Map<String,Object>> findMarkerFolder(Map map);
	public int delFol(Folder folder);
	public int delaFol(Folder folder);
	public int deltFol(Folder folder);
	public int updateFolName(Folder folder);
}
