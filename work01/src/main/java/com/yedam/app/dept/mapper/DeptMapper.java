package com.yedam.app.dept.mapper;

import java.util.List;

import com.yedam.app.dept.service.DeptVO;

public class DeptMapper {
	// Mapper골격
	public List<DeptVO> selectAll();
	
	// 단건조회
	public DeptVO selectInfo(DeptVO deptVO);
	
	// 등록
	public DeptVO insertInfo(DeptVO deptVO);
}
