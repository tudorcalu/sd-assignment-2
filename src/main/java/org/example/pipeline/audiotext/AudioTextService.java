package org.example.pipeline.audiotext;

import org.example.models.VideoFile;

public class AudioTextService {
    private AudioNormalizer audioNormalizer;
    private LanguageDetector languageDetector;
    private SubtitleGenerator subtitleGenerator;

    public AudioTextService() {
        this.audioNormalizer = new AudioNormalizer();
        this.languageDetector = new LanguageDetector();
        this.subtitleGenerator = new SubtitleGenerator();
    }

    public void process(VideoFile videoFile) {
        System.out.println("Starting Audio/Text Phase");
        audioNormalizer.normalize(videoFile);
        languageDetector.detectLanguage(videoFile);
        subtitleGenerator.generateSubtitles(videoFile);
        System.out.println("Audio/Text Phase completed successfully");
    }
}
