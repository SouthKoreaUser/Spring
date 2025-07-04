package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;
@Service // AOP(공통기능)에 대해 지원이 가능한 Bean
		 // 예를 들어, @Transactional
public class EmpServiceImpl implements EmpService {
	private EmpMapper empMapper;
	
	@Autowired // EmpMapper 인터페이시를 구현한 Mapper를 자동으로 주입
	public EmpServiceImpl(EmpMapper empMapper) {
		this.empMapper = empMapper;
	}
	
	@Override
	public List<EmpVO> findAllList() {
		List<EmpVO> list = empMapper.selectAll();
		return list;
	}

	@Override
	public EmpVO findInfoById(EmpVO empVO) {
		EmpVO result = empMapper.selectInfo(empVO);
		return result;
	}

	@Override
	public int createInfo(EmpVO empVO) {
		int result = empMapper.insertInfo(empVO);
		return result == 1 ? empVO.getEmployeeId() : -1;
	}

	@Override
	public Map<String, Object> modifyInfo(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result
				= empMapper.updateInfo
					(empVO.getEmployeeId(), empVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", map);
		/*
			{
				"result" : true,
				"target" : {"employeeId" : 100, ...}
			}
		 */
		return map;
	}

	@Override
	public Map<String, Object> removeInfo(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
