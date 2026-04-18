package org.example.pipeline;

import org.example.models.VideoFile;

public class VisualsService {
    private Transcoder transcoder;
    private SpriteGenerator spriteGenerator;

    public VisualsService() {
        this.transcoder = new Transcoder();
        this.spriteGenerator = new SpriteGenerator();
    }

    public void process(VideoFile videoFile) {
        System.out.println("Starting Visuals Phase");
        transcoder.transcode(videoFile);
        spriteGenerator.generateSprites(videoFile);
        System.out.println("Visuals Phase completed successfully");
    }
}
