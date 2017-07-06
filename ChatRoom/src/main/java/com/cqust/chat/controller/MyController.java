package com.cqust.chat.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cqust.chat.dao.TestDao;
import com.cqust.chat.entity.Student;
import com.cqust.chat.service.StudentService;
import com.cqust.chat.websocket.MyHandler;

@Controller
@RequestMapping("/msg")
public class MyController {
	
	@Autowired
	private MyHandler myHandler;
	@Autowired
	private TestDao testDao;
	@Autowired
	private StudentService studentService;

	/**
	 * 测试连通性
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/test",method={RequestMethod.GET})
	public ModelAndView test(ModelAndView mv){
		mv.setViewName("test");
		Student s = new Student();
		s.setName("黄国余");
		s.setPassword("123456");
		testDao.insert(s);
		return mv;
	}
	/**
	 * 测试登录
	 * @param request
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView doLogin(HttpServletRequest request){
		request.getSession().setAttribute("id", Long.parseLong(request.getParameter("id")));
		System.out.println(request.getSession(false).getAttribute("id"));
		System.out.println(request.getParameter("id")+"----------");
		request.getSession().setAttribute("name", request.getParameter("name"));
		return new ModelAndView("redirect:talk");
	}
	/**
	 * 聊天界面
	 * @return
	 */
	@RequestMapping("talk")
	public ModelAndView talk(HttpServletRequest request){
		if(request.getSession(false) != null)
		return new ModelAndView("talk");
		else
		return new ModelAndView("login");
	}
	/**
	 * 登录逻辑
	 * @param s
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping("stuLogin")
	public ModelAndView stuLogin(Student s,HttpServletRequest request,ModelAndView mv){
		Student stu = studentService.doLogin(s);
		System.out.println(stu);
		mv.setViewName("redirect:talk");
		request.getSession().setAttribute("stu", stu);
		//获取所有学生列表，并且添加是否在线
		List<Student> stus = getAllStu();
		//添加到requestScope中
		request.getSession().setAttribute("stus",stus );
		if(stu == null)
		{
			mv.addObject("loginTag", "登录失败");
			mv.setViewName("forward:loginPage");
		}
		return mv;
	}
	
	public List<Student> getAllStu(){
		List<Student> stus = studentService.findAll();
		for(int i=0;i<stus.size();i++)
		{
			if(MyHandler.userSocketSessionMap.get(stus.get(i).getId()) != null){
				stus.get(i).setActive(true);
			}
		}
		return stus;
	}
	/**
	 * 登录页跳转
	 * @return
	 */
	@RequestMapping("loginPage")
	public ModelAndView loginpage(){
		return new ModelAndView("login");
	}
	/**
	 * 注册页面跳转
	 * @return
	 */
	@RequestMapping("toRegister")
	public ModelAndView toRegister(){
		return new ModelAndView("register");
	}
	@RequestMapping("registerstu")
	public ModelAndView addStu(HttpServletRequest request,Student s,ModelAndView mv){
		
		Long id = studentService.addStu(s);
		mv.addObject("id", id);
		mv.setViewName("register");
		request.setAttribute("tag", true);
		return mv;
	}
	@RequestMapping("register")
	public ModelAndView toRegisterPage(){
		return new ModelAndView("register");
	}
	
	@RequestMapping("refreshList")
	@ResponseBody
	public List<Student> refresh(){
		return getAllStu();
	}
}
