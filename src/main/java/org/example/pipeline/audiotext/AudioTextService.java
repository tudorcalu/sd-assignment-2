package org.example.pipeline.audiotext;

import org.example.models.VideoFile;

public class AudioTextService {

    private final AudioNormalizer audioNormalizer;
    private final LanguageDetector languageDetector;
    private final SubtitleGenerator subtitleGenerator;

    public AudioTextService() {
        this.audioNormalizer = new AudioNormalizer();
        this.languageDetector = new LanguageDetector();
        this.subtitleGenerator = new SubtitleGenerator();
    }

    public void process(VideoFile videoFile) {
        try {
            audioNormalizer.normalize(videoFile);
            languageDetector.detectLanguage(videoFile);
            subtitleGenerator.generateSubtitles(videoFile);
        } catch (Exception e) {
            throw new RuntimeException("Audio/Text phase error: " + e.getMessage(), e);
        }
        System.out.println("Audio/Text Phase completed successfully");
    }
}
