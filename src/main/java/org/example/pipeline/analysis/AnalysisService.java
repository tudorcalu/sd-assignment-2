package org.example.pipeline.analysis;

import org.example.models.VideoFile;

public class AnalysisService {
    private IntroOutroDetector introOutroDetector;
    private CreditRoller creditRoller;
    private SceneIndexer sceneIndexer;
    private ComplexityAnalyzer complexityAnalyzer;

    public AnalysisService() {
        this.introOutroDetector = new IntroOutroDetector();
        this.creditRoller = new CreditRoller();
        this.sceneIndexer = new SceneIndexer();
        this.complexityAnalyzer = new ComplexityAnalyzer();
    }

    public void process(VideoFile videoFile) {
        System.out.println("Starting Analysis Phase");
        introOutroDetector.detectTheme(videoFile);
        creditRoller.detectCredits(videoFile);
        sceneIndexer.indexScenes(videoFile);
        complexityAnalyzer.analyzeVisualEntropy(videoFile);
        System.out.println("Analysis Phase completed successfully.");
    }
}
