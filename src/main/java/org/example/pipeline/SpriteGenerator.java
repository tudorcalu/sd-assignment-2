package org.example.pipeline;

import org.example.models.VideoFile;

public class SpriteGenerator {
    public void generateSprites(VideoFile videoFile) {
        System.out.println("Generating scrub-bar filmstrip and periodic thumbnails for " + videoFile.getFilename());
    }
}
