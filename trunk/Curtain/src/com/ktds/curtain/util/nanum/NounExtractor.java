package com.ktds.curtain.util.nanum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import kr.ac.kaist.swrc.jhannanum.comm.Eojeol;
import kr.ac.kaist.swrc.jhannanum.comm.Sentence;
import kr.ac.kaist.swrc.jhannanum.hannanum.Workflow;
import kr.ac.kaist.swrc.jhannanum.plugin.MajorPlugin.MorphAnalyzer.ChartMorphAnalyzer.ChartMorphAnalyzer;
import kr.ac.kaist.swrc.jhannanum.plugin.MajorPlugin.PosTagger.HmmPosTagger.HMMTagger;



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
	
	private String getCurrentJavaPath (String[] requestPathSplit) {
		String nanumClassPath = "";
		
		for (int i = 0; i < requestPathSplit.length; i++) {
			nanumClassPath += requestPathSplit[i] + "\\";
			
			if (requestPathSplit[i].contains("workspace")) {
				return nanumClassPath;
			}
		}
		return null;
	}
	
	public List<String> nounExtractorByTitle(String title, String[] requestPathSplit) {
		
		String currentClassPath = getCurrentJavaPath(requestPathSplit);
		
		Workflow workflow = new Workflow(currentClassPath + "Curtain\\WebContent\\resource\\nanum");
		workflow.setMorphAnalyzer(new ChartMorphAnalyzer(), "conf\\plugin\\MajorPlugin\\MorphAnalyzer\\ChartMorphAnalyzer.json");
		workflow.setPosTagger(new HMMTagger(), "conf\\plugin\\MajorPlugin\\PosTagger\\HmmPosTagger.json");
		
		try {

			/* Activate the work flow in the thread mode */
			workflow.activateWorkflow(true);

			/* Analysis using the work flow */
			workflow.analyze(title);

			LinkedList<Sentence> resultList = workflow.getResultOfDocument(new Sentence(0, 0, false));

			Eojeol[] eojeolArray = null;
			List<String> nounList = new ArrayList<String>();
			
			for (Sentence s : resultList) {
				eojeolArray = s.getEojeols();
				for (int i = 0; i < eojeolArray.length; i++) {
					if (eojeolArray[i].length > 0) {
						String[] morphemes = eojeolArray[i].getMorphemes();
						String str = "";
						for (int j = 0; j < morphemes.length; j++) {
							str += morphemes[j];
						}
						nounList.add(str);
					}
				}
			}
			
			workflow.close();

			return nounList;

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		/* Shutdown the work flow */
		workflow.close();
		return null;
	}
}