package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptVO;

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
