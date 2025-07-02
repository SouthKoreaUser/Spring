package com.yedam.app.aop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aop.mapper.AopMapper;
import com.yedam.app.aop.service.AopService;

import lombok.RequiredArgsConstructor;
@Service // AOP(관심분리) 기능을 적용할 수 있는 대상
@RequiredArgsConstructor
public class AopServiceImpl implements AopService{
	// (권장) Lombok을 활용한 생성자 주입방식
	private final AopMapper aopMap;
	
	@Transactional// 트랜잭션을 적용하여 메서드 실행 시 데이터베이스 작업을 묶음 처리. @Service가 붙은 클래스에서만 동작.
	@Override
	public void insert() {
		aopMap.insert("101"); // commit
		aopMap.insert("a101"); // error로 인해 rollback
		
	}
}
