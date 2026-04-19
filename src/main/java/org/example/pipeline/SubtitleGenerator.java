package org.example.pipeline;

import org.example.models.VideoFile;

public class SubtitleGenerator {
    public void generateSubtitles(VideoFile videoFile) {
        System.out.println("Generating auto-captions and subtitle files for " + videoFile.getFilename());
    }
}
