package org.example.pipeline.analysis;

import org.example.models.VideoFile;

public class AnalysisService {

    private final IntroOutroDetector introOutroDetector;
    private final CreditRoller creditRoller;
    private final SceneIndexer sceneIndexer;
    private final ComplexityAnalyzer complexityAnalyzer;

    public AnalysisService() {
        this.introOutroDetector = new IntroOutroDetector();
        this.creditRoller = new CreditRoller();
        this.sceneIndexer = new SceneIndexer();
        this.complexityAnalyzer = new ComplexityAnalyzer();
    }

    public void process(VideoFile videoFile) {
        System.out.println("Starting Analysis Phase");
        try {
            introOutroDetector.detectTheme(videoFile);
            creditRoller.detectCredits(videoFile);
            sceneIndexer.indexScenes(videoFile);
            complexityAnalyzer.analyzeVisualEntropy(videoFile);
        } catch (Exception e) {
            throw new RuntimeException("Analysis phase error: " + e.getMessage(), e);
        }
        System.out.println("Analysis Phase completed successfully.");
    }
}
