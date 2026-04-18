package org.example.pipeline;

import org.example.models.VideoFile;

public class IntroOutroDetector {
    public void detectTheme(VideoFile videoFile) {
        System.out.println("Analyzing " + videoFile.getFilename() + " to find theme song timestamps");
    }
}
