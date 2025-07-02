package com.yedam.app;

import java.util.List;

import main.java.com.yedam.app.dept.mapper.DeptMapper;
import main.java.com.yedam.app.dept.service.DeptVO;


@SpringBootTest
class Work01ApplicationTests {

	DeptMapper deptMapper;
	
	@Test
	public void selectAll() {
		List<DeptVO> list = deptMapper.selectAll();
		for (DeptVO dept : list) {
			System.out.println(dept.getLastName());
		}
		assertTrue(!list.isEmpty());
	}
	
	@Test
	public void insertValue() {
		DeptVO dept = DeptVO.builder()
						.departmentId(100)
						.departmentName("")
						.managerId()
						.locationId()
						.build();
	}
}
