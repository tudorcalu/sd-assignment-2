package org.example.pipeline.compliance;

import org.example.models.VideoFile;

public class WatermarkInjector {
    public void injectWatermark(VideoFile videoFile) {
        System.out.println("Injecting invisible forensic watermarks for piracy tracing into " + videoFile.getFilename());
    }
}
