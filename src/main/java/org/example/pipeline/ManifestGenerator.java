package org.example.pipeline;

import org.example.models.VideoFile;

public class ManifestGenerator {
    public void generateManifests(VideoFile videoFile) {
        System.out.println("Generating DASH and HLS manifests for " + videoFile.getFilename());
    }
}
