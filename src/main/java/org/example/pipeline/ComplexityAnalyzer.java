package org.example.pipeline;

import org.example.models.VideoFile;
import java.io.File;
import java.io.IOException;

public class ComplexityAnalyzer {
    public void analyzeVisualEntropy(VideoFile videoFile) {
        System.out.println("Running ffprobe on " + videoFile.getFilename());
        createTestFile(videoFile.getOutputDirectory(), "metadata/scene_analysis.json");
    }
    
    private void createTestFile(String base, String path) {
        File file = new File(base, path);
        file.getParentFile().mkdirs();
        try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
    }
}
