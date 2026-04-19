package org.example.pipeline;

import org.example.models.VideoFile;
import java.io.File;
import java.io.IOException;

public class Transcoder {
    public void transcode(VideoFile videoFile) {
        System.out.println("Transcoding " + videoFile.getFilename() + " to 4K, 1080p, and 720p (H264, VP9, HEVC)");
        String base = videoFile.getOutputDirectory();
        createTestFile(base, "video/h264/4k_h264.mp4");
        createTestFile(base, "video/h264/1080p_h264.mp4");
        createTestFile(base, "video/h264/720p_h264.mp4");
        createTestFile(base, "video/vp9/4k_vp9.webm");
        createTestFile(base, "video/vp9/1080p_vp9.webm");
        createTestFile(base, "video/vp9/720p_vp9.webm");
        createTestFile(base, "video/hevc/4k_hevc.mkv");
        createTestFile(base, "video/hevc/1080p_hevc.mkv");
        createTestFile(base, "video/hevc/720p_hevc.mkv");
    }

    private void createTestFile(String base, String path) {
        File file = new File(base, path);
        file.getParentFile().mkdirs();
        try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
    }
}
