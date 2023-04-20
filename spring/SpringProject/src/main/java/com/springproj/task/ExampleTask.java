package com.springproj.task;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springproj.domain.BoardImg;
import com.springproj.service.BoardService;

@Component
public class ExampleTask {

	@Inject
	private BoardService service;

	@Scheduled(cron = " * 2  * * * *")
	public void sampleTask() {
		System.out.println("------------작업스케쥴링 시작-----");

		try {
			List<BoardImg> lst = service.getAllAppendFiles();
			
			for(BoardImg bi : lst) {
				// 1) 물리경로 +/resources/upfiles/bi.getFileName()의 Fiile 객체 생성 (DB에 저장 되어있는 파일들)
				
				// 2) 물리경로 +/resources/upfiles/ 하위의 모든 파일리스트를 가져온다. (하드디스크에 저장 되어있는 파일들)
				
				// 3) 2)번에는 있고, 1) 번에는 존재하지 않는 파일 삭제
				//new File(bi.getFileName());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
