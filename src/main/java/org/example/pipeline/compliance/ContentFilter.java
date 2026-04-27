package org.example.pipeline.compliance;

import org.example.models.VideoFile;

public class ContentFilter {
    public void filterContent(VideoFile videoFile) {
        System.out.println("Running automated checks for region specific compliance on " + videoFile.getFilename());
    }
}
