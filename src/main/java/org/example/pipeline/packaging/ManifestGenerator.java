package org.example.pipeline.packaging;

import org.example.models.VideoFile;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManifestGenerator {

    private static final String MANIFEST_OUTPUT_PATH = "/manifest.json";

    public void generateManifests(VideoFile videoFile) throws IOException {
        String base = videoFile.getOutputDirectory();
        new File(base).mkdirs();
        writeManifestJson(base + MANIFEST_OUTPUT_PATH);
    }

    private void writeManifestJson(String path) throws IOException {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write("{\n");
            fw.write("  \"version\": 1,\n");
            fw.write("  \"streams\": [\n");
            fw.write("    {\"codec\": \"h264\", \"resolution\": \"720p\",  \"file\": \"video/h264/720p_h264.mp4\"},\n");
            fw.write("    {\"codec\": \"h264\", \"resolution\": \"1080p\", \"file\": \"video/h264/1080p_h264.mp4\"},\n");
            fw.write("    {\"codec\": \"h264\", \"resolution\": \"4k\",    \"file\": \"video/h264/4k_h264.mp4\"},\n");
            fw.write("    {\"codec\": \"vp9\",  \"resolution\": \"720p\",  \"file\": \"video/vp9/720p_vp9.webm\"},\n");
            fw.write("    {\"codec\": \"vp9\",  \"resolution\": \"1080p\", \"file\": \"video/vp9/1080p_vp9.webm\"},\n");
            fw.write("    {\"codec\": \"vp9\",  \"resolution\": \"4k\",    \"file\": \"video/vp9/4k_vp9.webm\"},\n");
            fw.write("    {\"codec\": \"hevc\", \"resolution\": \"720p\",  \"file\": \"video/hevc/720p_hevc.mkv\"},\n");
            fw.write("    {\"codec\": \"hevc\", \"resolution\": \"1080p\", \"file\": \"video/hevc/1080p_hevc.mkv\"},\n");
            fw.write("    {\"codec\": \"hevc\", \"resolution\": \"4k\",    \"file\": \"video/hevc/4k_hevc.mkv\"}\n");
            fw.write("  ],\n");
            fw.write("  \"audio\": \"audio/ro_dub_synthetic.aac\",\n");
            fw.write("  \"subtitles\": \"text/source_transcript.txt\",\n");
            fw.write("  \"sprite_map\": \"images/sprite_map.jpg\"\n");
            fw.write("}\n");
        }
        System.out.println("  OK manifest.json -> " + path);
    }
}
