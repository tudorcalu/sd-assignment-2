package org.example.pipeline.audiotext;

import org.example.models.VideoFile;
import java.io.File;
import java.io.IOException;

public class SubtitleGenerator {
    public void generateSubtitles(VideoFile videoFile) {
        System.out.println("Generating auto-captions and subtitle files for " + videoFile.getFilename());
        createTestFile(videoFile.getOutputDirectory(), "text/source_transcript.txt");
        createTestFile(videoFile.getOutputDirectory(), "text/ro_translation.txt");
    }

    private void createTestFile(String base, String path) {
        File file = new File(base, path);
        file.getParentFile().mkdirs();
        try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
    }
}
