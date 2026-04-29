package org.example.pipeline.visuals;

import org.example.models.VideoFile;
import java.io.File;

public class SpriteGenerator {

    private static final String THUMBNAIL_FILTER    = "fps=1/10,scale=160:-1";
    private static final String SPRITE_MAP_FILTER   = "fps=1/10,scale=160:-1,tile=10x10";
    private static final String JPEG_QUALITY        = "3";
    private static final String SPRITE_FRAMES_LIMIT = "1";
    private static final String THUMBNAILS_SUBDIR   = "images/thumbnails";
    private static final String THUMBNAIL_PATTERN   = "/images/thumbnails/thumb_%04d.jpg";
    private static final String SPRITE_MAP_PATH     = "/images/sprite_map.jpg";

    public void generateSprites(VideoFile videoFile) throws Exception {
        String base = videoFile.getOutputDirectory();
        new File(base, THUMBNAILS_SUBDIR).mkdirs();
        extractThumbnails(videoFile.getFilename(), base + THUMBNAIL_PATTERN);
        buildSpriteMap(videoFile.getFilename(), base + SPRITE_MAP_PATH);
    }

    private void extractThumbnails(String input, String outputPattern) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(
            "ffmpeg", "-i", input,
            "-vf", THUMBNAIL_FILTER,
            "-q:v", JPEG_QUALITY,
            "-y", outputPattern
        );
        pb.inheritIO();
        int exit = pb.start().waitFor();
        if (exit != 0) {
            throw new RuntimeException("ffmpeg thumbnail extraction failed with exit code " + exit);
        }
        System.out.println("  OK thumbnails");
    }

    private void buildSpriteMap(String input, String output) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(
            "ffmpeg", "-i", input,
            "-vf", SPRITE_MAP_FILTER,
            "-frames:v", SPRITE_FRAMES_LIMIT,
            "-q:v", JPEG_QUALITY,
            "-y", output
        );
        pb.inheritIO();
        int exit = pb.start().waitFor();
        if (exit != 0) {
            throw new RuntimeException("ffmpeg sprite map generation failed with exit code " + exit);
        }
        System.out.println("  OK sprite_map " + output);
    }
}
