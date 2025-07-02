package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Sp02ApplicationTests {
		
	@Autowired
	private EmpMapper empMapper;
	
	@Test
	public void selectAll() {
		List<EmpVO> list = empMapper.selectAll();
		for (EmpVO emp : list) {
			System.out.println(emp.getLastName());
		}
		assertTrue(!list.isEmpty());
	}
	
	@Test
	public void selectOne() {
		 // 클래스가 가진 전역 메서드 중 builder를 호출. 
		EmpVO emp = EmpVO.builder()
							// employeeId에 100이라는 값을 준 인스턴스를 생성 후
							.employeeId(100)
							// 해당 값을 바탕으로 인스턴스 반환
							.build();
		
		EmpVO findVO = empMapper.selectInfo(emp);
		System.out.println(findVO);
		// 내가 원하는 결과가 첫번째 매개변수, 실행한 비교값이 같을 경우 성공.
		assertEquals("King", findVO.getLastName());
		
	}
	
	//@Test // 해당 메서드를 테스트 하겠다고 선언 !
	public void insertValue() {
		EmpVO emp = EmpVO.builder()
						.employeeId(1000)
						.lastName("Kang")
						.email("kg@google.com")
						.jobId("SA_REP")
						.build();
		
		int result = empMapper.insertInfo(emp);
		
		assertEquals(1, result);
	}
	
	@Test
	public void insertSelectKey() {
		EmpVO emp = EmpVO.builder()
						.lastName("Hong")
						.email("hong@naver.com")
						.jobId("IT_PROG")
						.salary(1200)
						.build();
	}
	
	@Test
	public void updateInfo() {
		// 1) 단건조회
		EmpVO emp = EmpVO.builder()
						 .employeeId(1001)
						 .build();
		EmpVO findVO = empMapper.selectInfo(null);
		// 2) 값변경
		findVO.setSalary(2550);
		// 3) 테이블에 업데이트
		int result = empMapper.updateInfo(1001, findVO);
		
		assertEquals(1, result);
	}
}
