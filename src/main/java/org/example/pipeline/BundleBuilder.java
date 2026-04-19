package org.example.pipeline;

import org.example.models.VideoFile;

public class BundleBuilder {
    public void buildBundle(VideoFile videoFile) {
        System.out.println("Assembling final streaming package and preparing delivery for " + videoFile.getFilename());
    }
}
