package org.example.pipeline;

import org.example.models.VideoFile;

public class ComplexityAnalyzer {
    public void analyzeVisualEntropy(VideoFile videoFile) {
        System.out.println("Running ffprobe on " + videoFile.getFilename());
    }
}
