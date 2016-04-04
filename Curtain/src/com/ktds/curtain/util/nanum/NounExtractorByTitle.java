package com.ktds.curtain.util.nanum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import kr.ac.kaist.swrc.jhannanum.comm.Eojeol;
import kr.ac.kaist.swrc.jhannanum.comm.Sentence;
import kr.ac.kaist.swrc.jhannanum.hannanum.Workflow;
import kr.ac.kaist.swrc.jhannanum.plugin.MajorPlugin.MorphAnalyzer.ChartMorphAnalyzer.ChartMorphAnalyzer;
import kr.ac.kaist.swrc.jhannanum.plugin.MajorPlugin.PosTagger.HmmPosTagger.HMMTagger;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.MorphemeProcessor.UnknownMorphProcessor.UnknownProcessor;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.PlainTextProcessor.InformalSentenceFilter.InformalSentenceFilter;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.PlainTextProcessor.SentenceSegmentor.SentenceSegmentor;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.PosProcessor.NounExtractor.NounExtractor;

public class NounExtractorByTitle {

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
		
		Workflow workflow = new Workflow( currentClassPath + "\\Curtain\\WebContent\\resource\\nanum");
		workflow.appendPlainTextProcessor(new SentenceSegmentor(), null);
		workflow.appendPlainTextProcessor(new InformalSentenceFilter(), null);
		
		workflow.setMorphAnalyzer(new ChartMorphAnalyzer(), "conf\\plugin\\MajorPlugin\\MorphAnalyzer\\ChartMorphAnalyzer.json");
		workflow.appendMorphemeProcessor(new UnknownProcessor(), null);
		
		workflow.setPosTagger(new HMMTagger(), "conf\\plugin\\MajorPlugin\\PosTagger\\HmmPosTagger.json");
		workflow.appendPosProcessor(new NounExtractor(), null);
		
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
