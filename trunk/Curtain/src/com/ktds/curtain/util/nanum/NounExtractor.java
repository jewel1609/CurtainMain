package com.ktds.curtain.util.nanum;

import java.util.LinkedList;

import de.jetwick.snacktory.HtmlFetcher;
import kr.ac.kaist.swrc.jhannanum.comm.Eojeol;
import kr.ac.kaist.swrc.jhannanum.comm.Sentence;
import kr.ac.kaist.swrc.jhannanum.hannanum.Workflow;
import kr.ac.kaist.swrc.jhannanum.hannanum.WorkflowFactory;

/**
 * This is a demo program of HanNanum that helps users to utilize the HanNanum library easily.
 * It uses a predefined work flow for noun extracting, which extracts only the nouns in the
 * given document. <br>
 * <br>
 * It performs noun extraction for a Korean document with the following procedure:<br>
 * 		1. Create a predefined work flow for morphological analysis, POS tagging, and noun extraction.<br>
 * 		2. Activate the work flow in multi-thread mode.<br>
 * 		3. Analyze a document that consists of several sentences.<br>
 * 		4. Print the result on the console.<br>
 * 		5. Repeats the procedure 3~4 with activated work flow.<br>
 * 		6. Close the work flow.<br>
 * 
 * @author Sangwon Park (hudoni@world.kaist.ac.kr), CILab, SWRC, KAIST
 */
public class NounExtractor {

	public static void main(String[] args) {
		Workflow workflow = WorkflowFactory.getPredefinedWorkflow(WorkflowFactory.WORKFLOW_NOUN_EXTRACTOR);
		
		try {
			
			 HtmlFetcher fetcher = new HtmlFetcher();
			 // set cache. e.g. take the map implementation from google collections:
			 // fetcher.setCache(new MapMaker().concurrencyLevel(20).maximumSize(count).
			 //    expireAfterWrite(minutes, TimeUnit.MINUTES).makeMap();

//			 JResult res = fetcher.fetchAndExtract("https://ko.wikipedia.org/wiki/%EC%9D%B8%ED%84%B0%EB%84%B7", 10000, true);
//			 String text = res.getText(); 
//			 String title = res.getTitle(); 
//			 String imageUrl = res.getImageUrl();
//			 System.out.println(text);
			 
			 
			/* Activate the work flow in the thread mode */
			workflow.activateWorkflow(true);
			
			/* Analysis using the work flow */
			String document = "게시물 필터링 솔루션입니다. I.제품명 : 웹게시물 개인정보 필터링[WEBFILTER] II.특장점 : 행정업무용... 자체 DB를 이용한 욕설,광고,음란글 차단,유해링크 차단,로봇에 의한 도배글 차단,IP대상 차단 2.검출... "; //"나는 밥을 먹었다.\n";
			workflow.analyze(document);
			
			LinkedList<Sentence> resultList = workflow.getResultOfDocument(new Sentence(0, 0, false));
			for (Sentence s : resultList) {
				Eojeol[] eojeolArray = s.getEojeols();
				for (int i = 0; i < eojeolArray.length; i++) {
					if (eojeolArray[i].length > 0) {
						String[] morphemes = eojeolArray[i].getMorphemes();
						for (int j = 0; j < morphemes.length; j++) {
							System.out.print(morphemes[j]);
						}
						System.out.print(", ");
					}
				}
			}
			
			/* Once a work flow is activated, it can be used repeatedly. */
			/*
			document = "프로젝트 전체 회의.\n"
				+ "회의 일정은 다음과 같습니다.\n"
				+ "日時: 2010년 7월 30일 오후 1시\n"
				+ "場所: Coex Conference Room\n";
			
			workflow.analyze(document);
			
			resultList = workflow.getResultOfDocument(new Sentence(0, 0, false));
			for (Sentence s : resultList) {
				Eojeol[] eojeolArray = s.getEojeols();
				for (int i = 0; i < eojeolArray.length; i++) {
					if (eojeolArray[i].length > 0) {
						String[] morphemes = eojeolArray[i].getMorphemes();
						for (int j = 0; j < morphemes.length; j++) {
							System.out.print(morphemes[j]);
						}
						System.out.print(", ");
					}
				}
			}
			System.out.println();
			*/
			
			workflow.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		/* Shutdown the work flow */
		workflow.close();  	
	}
}