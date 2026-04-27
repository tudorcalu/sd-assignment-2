package org.example.pipeline.analysis;

import org.example.models.VideoFile;
import java.io.File;
import java.io.IOException;

public class ComplexityAnalyzer {
    public void analyzeVisualEntropy(VideoFile videoFile) {
        System.out.println("Running ffprobe on " + videoFile.getFilename());
        try {
            ProcessBuilder pb = new ProcessBuilder("ffprobe", "-v", "error", "-show_format", "-show_streams",
                    videoFile.getFilename());
            // pb.inheritIO();
            Process process = pb.start();
            int exitCode = process.waitFor();
            System.out.println("OK" + exitCode);
        } catch (Exception e) {
            System.err.println("FAIL" + e.getMessage());
        }
        createTestFile(videoFile.getOutputDirectory(), "metadata/scene_analysis.json");
    }

    private void createTestFile(String base, String path) {
        File file = new File(base, path);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
