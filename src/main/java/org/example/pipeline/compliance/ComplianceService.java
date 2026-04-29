package org.example.pipeline.compliance;

import org.example.models.VideoFile;

public class ComplianceService {
    private final ContentFilter contentFilter;
    private final WatermarkInjector watermarkInjector;

    public ComplianceService() {
        this.contentFilter = new ContentFilter();
        this.watermarkInjector = new WatermarkInjector();
    }

    public void process(VideoFile videoFile) {
        System.out.println("Starting Compliance Phase");
        try {
            contentFilter.filterContent(videoFile);
            watermarkInjector.injectWatermark(videoFile);
        } catch (Exception e) {
            throw new RuntimeException("Compliance phase error: " + e.getMessage(), e);
        }
        System.out.println("Compliance Phase completed successfully");
    }
}
