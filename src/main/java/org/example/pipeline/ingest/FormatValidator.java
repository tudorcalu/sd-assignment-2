package org.example.pipeline.ingest;

import org.example.models.VideoFile;

public class FormatValidator {
    public boolean validateFormat(VideoFile file) {
        try {
            ProcessBuilder pb = new ProcessBuilder("ffprobe", "-v", "error", "-show_format", file.getFilename());
            pb.inheritIO();
            Process p = pb.start();
            int exitCode = p.waitFor();
            if (exitCode == 0) {
                System.out.println("OK format verified.");
                return true;
            } else {
                System.err.println("FAIL format validation exit code: " + exitCode + ". Is ffprobe installed/available for this file?");
                return false;
            }
        } catch (Exception e) {
            System.err.println("FAIL ffprobe execution failed: " + e.getMessage());
            return false;
        }
    }
}
