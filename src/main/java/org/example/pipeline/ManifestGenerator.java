package org.example.pipeline;

import org.example.models.VideoFile;
import java.io.File;
import java.io.IOException;

public class ManifestGenerator {
    public void generateManifests(VideoFile videoFile) {
        System.out.println("Generating DASH and HLS manifests for " + videoFile.getFilename());
        createTestFile(videoFile.getOutputDirectory(), "manifest.json");
    }

    private void createTestFile(String base, String path) {
        File file = new File(base, path);
        file.getParentFile().mkdirs();
        try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
    }
}
