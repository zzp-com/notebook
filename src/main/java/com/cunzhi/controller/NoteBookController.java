package com.cunzhi.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cunzhi.manager.TextManager;

/**
 * 完成登录之后页面的转发
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/suji/notebook")
public class NoteBookController {
	@Resource
	private TextManager textManager;

	private String file_id = null;

	// 查找控制器
	@RequestMapping("/doc.do")
	public ModelAndView document(HttpServletRequest request, HttpServletResponse response) {
		// 在这里添加
		ModelAndView model = new ModelAndView("document");
		String folder_id = request.getParameter("folder_id");
		String clickbtn = request.getParameter("clickbtn");//判断是点击了哪个标签

		// 将文件id存入session
		HttpSession session = request.getSession();
		session.setAttribute("folder_id", folder_id);
		
		
		List<Map<String, Object>> list = textManager.findoc(getEmail(request), folder_id,clickbtn);// 获取文档
		model.addObject("map", list);
		if(!"recenedit".equals(clickbtn)) {
		// 获取文件
		List<Map<String, Object>> folder = textManager.findFolder(getEmail(request), folder_id,clickbtn);
		model.addObject("folder", folder);
		}
		return model;

	}

	// 编辑控制器
	@RequestMapping("/edit.do")
	public String edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String doc_id = request.getParameter("doc_id");
		request.setAttribute("title", title);
		request.setAttribute("doc_id", doc_id);
		String content = textManager.findContent(doc_id);
		request.setAttribute("content", content);

		return "edit";

	}

	// 保存内容控制器
	@RequestMapping("/save.do")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		String str = request.getParameter("infos");
//		System.out.println(str);
		int i = textManager.saveText(str, getEmail(request));
		if (i == -1) {
			model.put("msg", "保存失败");
			model.put("status", "-1");
			return model;
		}
		model.put("msg", "保存成功");
		model.put("status", "1");
		return model;

	}

	// 更新文档
	@RequestMapping("/updateDoc.do")
	@ResponseBody
	public Map<String, Object> updateDoc(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String text = request.getParameter("text");
		int i = textManager.updateDoc(id, text);
		if (i == -1) {
			model.put("status", "-1");
			model.put("msg", text + "失败");
			return model;
		} else if (i == 2) {
			model.put("status", "2");
			return model;
		}else if (i == 5) {
			model.put("status", "5");
			return model;
		}else if(i==3) {
			model.put("status", "3");
			return model;
		}
		
		model.put("status", "1");
		model.put("msg", text + "成功");
		return model;

	}

	//更新文件夹
	@RequestMapping("/updateFol.do")
	@ResponseBody
	public Map<String, Object> updateFol(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String text = request.getParameter("text");
		//System.out.println(id);
		int i = textManager.updateFol(id, text);
		if (i == -1) {
			model.put("status", "-1");
			model.put("msg", text + "失败");
			return model;
		} else if (i == 2) {
			model.put("status", "2");
			return model;
		}
		model.put("status", "1");
		model.put("msg", text + "成功");
		return model;

	}
	
	
	
	// 保存文档名
	@RequestMapping("/saveTitle.do")
	@ResponseBody
	public Map<String, Object> saveTitle(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		String str = request.getParameter("title");
		HttpSession session = request.getSession();
		String folder_id = (String) session.getAttribute("folder_id");
		file_id = folder_id;
		int i = textManager.saveTitle(str, getEmail(request), folder_id);
		if (i == -1) {
			model.put("msg", "保存失败,未登录账号");
			model.put("status", "-1");
			return model;
		}
		model.put("msg", "保存成功");
		model.put("status", "1");
		if (folder_id != null && folder_id != "") {
			model.put("folder_id", folder_id);
		}

		System.out.println(folder_id + "saveTitle.do");

		return model;
	}

	// 保存文件名
	@RequestMapping("/savefile.do")
	@ResponseBody
	public Map<String, Object> savefile(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		String str = request.getParameter("title");

		HttpSession session = request.getSession();
		String folder_aid = (String) session.getAttribute("folder_id");

		file_id = folder_aid;

		int i = textManager.saveFolder(str, getEmail(request), folder_aid);
		if (i == -1) {
			model.put("msg", "保存失败,未登录账号");
			model.put("status", "-1");
			return model;
		}
		model.put("msg", "保存成功");
		model.put("status", "1");
		if (folder_aid != null && folder_aid != "") {
			model.put("folder_id", folder_aid);
		}
		return model;
	}

	// 得到用户邮箱
	public String getEmail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		return email;

	}

}
