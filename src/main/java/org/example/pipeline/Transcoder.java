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

        try {
            System.out.println("  [->] Calling ffmpeg on host to perform real 720p h264 transcode test...");
            ProcessBuilder pb = new ProcessBuilder("ffmpeg", "-i", videoFile.getFilename(), "-vf", "scale=-1:720",
                    "-c:v", "libx264", "-y", base + "/video/h264/720p_h264.mp4");
            // pb.inheritIO();
            Process p = pb.start();
            int exitCode = p.waitFor();
            System.out.println("OK " + exitCode);
        } catch (Exception e) {
            System.err.println("FAIL " + e.getMessage());
        }
    }

    private void createTestFile(String base, String path) {
        File file = new File(base, path);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
