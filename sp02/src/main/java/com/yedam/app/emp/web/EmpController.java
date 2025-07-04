package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;


@Controller
// DispatherServlet이 활용한 정보를 등록하는 Bean
// route : 사용자의 요청(endpoint)와 그에 대한 처리
// URI + METHOD => Service => Response(View of Data)
@RequiredArgsConstructor
public class EmpController {
	// 해당 컨트롤러에서 제공하는 서비스 목록
	private final EmpService empService;
	
	// GET : 조회, 빈페이지, 데이터 조작(삭제)
	// POST : 데이터 조작(등록, 수정, 삭제)
	
	// 전체조회 : GET
	@GetMapping("empList") // 1) URI + METHOD
	public String empList(Model model) {
		// 2) 수행기능 => Service
		List<EmpVO> list = empService.findAllList();
		// 2-1) View에 전달할 데이터 담기
		model.addAttribute("emps", list);
		// 3) 응답형태 : View
		return "emp/list";
		//classpath:/template/	emp/list	.html
		//prefix				return		suffix
	}
	
	// 단건조회 : GET
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.findInfoById(empVO);
		model.addAttribute("emp", findVO);
		return "emp/info";
	}
	
	// 등록 - 페이지 : GET
	@GetMapping("empinsert")
	public String empInsertForm() {
		return "emp/insert";
	}
	
	// 등록 - 처리 : POST => <form/> submit / QueryString
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.createInfo(empVO);
		String url = null;
		if(eid > -1) {
			// 정상적으로 등록
			url = "redirect:empinfo?employeeId=" + eid;
		}else {
			// 등록되지 않은 경우
			url = "redirect:empList";
		}
		return url;
	}
	
	// 수정 - 페이지 : GET
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.findInfoById(empVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
	}
	
	// 수정 - 처리 : POST
	@PostMapping("empUpdate")
	@ResponseBody // AJAX	
	public Map<String, Object> empUpdateProcess
			(@RequestBody EmpVO empVO) {
		return empService.modifyInfo(empVO);
	}
	
	// 삭제 - 처리 : GET
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.removeInfo(employeeId);
		return "redirect:empList";
	}
}
