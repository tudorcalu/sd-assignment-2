package org.example.pipeline;

import org.example.models.VideoFile;

public class LanguageDetector {
    public void detectLanguage(VideoFile videoFile) {
        System.out.println("Identifying primary audio language for " + videoFile.getFilename());
    }
}
