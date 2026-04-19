package org.example.pipeline;

import org.example.models.VideoFile;

public class ComplianceService {
    private ContentFilter contentFilter;
    private WatermarkInjector watermarkInjector;

    public ComplianceService() {
        this.contentFilter = new ContentFilter();
        this.watermarkInjector = new WatermarkInjector();
    }

    public void process(VideoFile videoFile) {
        System.out.println("Starting Compliance Phase");
        contentFilter.filterContent(videoFile);
        watermarkInjector.injectWatermark(videoFile);
        System.out.println("Compliance Phase completed successfully");
    }
}
