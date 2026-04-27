package org.example.pipeline.visuals;

import org.example.models.VideoFile;
import java.io.File;
import java.io.IOException;

public class SpriteGenerator {
    public void generateSprites(VideoFile videoFile) {
        System.out.println("Generating scrub-bar filmstrip and periodic thumbnails for " + videoFile.getFilename());
        String base = videoFile.getOutputDirectory();
        createTestFile(base, "images/sprite_map.jpg");
        new File(base, "images/thumbnails").mkdirs();
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
