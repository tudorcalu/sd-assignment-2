package org.example.pipeline.visuals;

import org.example.models.VideoFile;

public class VisualsService {

    private final Transcoder transcoder;
    private final SpriteGenerator spriteGenerator;

    public VisualsService() {
        this.transcoder = new Transcoder();
        this.spriteGenerator = new SpriteGenerator();
    }

    public void process(VideoFile videoFile) {
        System.out.println("Starting Visuals Phase");
        try {
            transcoder.transcode(videoFile);
            spriteGenerator.generateSprites(videoFile);
        } catch (Exception e) {
            throw new RuntimeException("Visuals phase error: " + e.getMessage(), e);
        }
        System.out.println("Visuals Phase completed successfully");
    }
}
