package org.example.pipeline;

import org.example.models.VideoFile;

public class Transcoder {
    public void transcode(VideoFile videoFile) {
        System.out.println("Transcoding " + videoFile.getFilename() + " to 4K, 1080p, and 720p (H264, VP9, HEVC)");
    }
}
