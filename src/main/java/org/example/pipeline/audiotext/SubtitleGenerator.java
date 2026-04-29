package org.example.pipeline.audiotext;

import org.example.models.VideoFile;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SubtitleGenerator {

    private static final String TRANSCRIPT_SUBPATH  = "/text/source_transcript.txt";
    private static final String TRANSLATION_SUBPATH = "/text/ro_translation.txt";
    private static final String SUBTITLE_STREAM_MAP = "0:s:0";
    private static final String TRANSCRIPT_CONTENT  = "[Auto-generated source transcript placeholder]\n";
    private static final String TRANSLATION_CONTENT = "[Traducere automată placeholder / Stub Translation]\n";

    public void generateSubtitles(VideoFile videoFile) throws IOException {
        String base = videoFile.getOutputDirectory();
        new File(base, "text").mkdirs();

        extractOrFallback(videoFile.getFilename(), base + TRANSCRIPT_SUBPATH);
        writePlaceholderTranslation(base + TRANSLATION_SUBPATH);
    }

    private void extractOrFallback(String input, String output) throws IOException {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg", "-i", input,
                "-map", SUBTITLE_STREAM_MAP,
                "-y", output
            );
            pb.inheritIO();
            int exit = pb.start().waitFor();
            if (exit == 0) {
                System.out.println("  OK extracted subtitles" + output);
                return;
            }
        } catch (Exception e) {
            throw new IOException("ffmpeg subtitle extraction failed: " + e.getMessage(), e);
        }
        writePlaceholderTxt(output);
    }

    private void writePlaceholderTxt(String path) throws IOException {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(TRANSCRIPT_CONTENT);
        }
        System.out.println("  OK placeholder transcript written" + path);
    }

    private void writePlaceholderTranslation(String path) throws IOException {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(TRANSLATION_CONTENT);
        }
        System.out.println("  OK placeholder RO translation written" + path);
    }
}
