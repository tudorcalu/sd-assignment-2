package org.example.pipeline.analysis;

import org.example.models.VideoFile;

public class IntroOutroDetector {
    public void detectTheme(VideoFile videoFile) {
        System.out.println("Analyzing " + videoFile.getFilename() + " to find theme song timestamps");
    }
}
