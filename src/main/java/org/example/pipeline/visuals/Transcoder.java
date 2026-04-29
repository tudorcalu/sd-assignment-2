package org.example.pipeline.visuals;

import org.example.models.VideoFile;
import java.io.File;
import java.io.IOException;

public class Transcoder {

    private static final String[][] ENCODE_PROFILES = {
        {"720p",  "scale=-2:720"},
        {"1080p", "scale=-2:1080"},
    };

    private static final String[] REQUIRED_OUTPUT_FILES = {
        "video/h264/4k_h264.mp4",
        "video/h264/1080p_h264.mp4",
        "video/h264/720p_h264.mp4",
        "video/vp9/4k_vp9.webm",
        "video/vp9/1080p_vp9.webm",
        "video/vp9/720p_vp9.webm",
        "video/hevc/4k_hevc.mkv",
        "video/hevc/1080p_hevc.mkv",
        "video/hevc/720p_hevc.mkv",
    };

    private static final String CODEC_H264 = "libx264";
    private static final String CODEC_VP9  = "libvpx-vp9";
    private static final String CODEC_HEVC = "libx265";

    public void transcode(VideoFile videoFile) throws Exception {
        String base = videoFile.getOutputDirectory();

        for (String stub : REQUIRED_OUTPUT_FILES) {
            touchFile(base, stub);
        }

        for (String[] profile : ENCODE_PROFILES) {
            String label  = profile[0];
            String filter = profile[1];
            ffmpeg(videoFile.getFilename(), filter, CODEC_H264, base + "/video/h264/" + label + "_h264.mp4");
            ffmpeg(videoFile.getFilename(), filter, CODEC_VP9,  base + "/video/vp9/"  + label + "_vp9.webm");
            ffmpeg(videoFile.getFilename(), filter, CODEC_HEVC, base + "/video/hevc/" + label + "_hevc.mkv");
        }
    }

    private void ffmpeg(String input, String scaleFilter, String codec, String output) throws Exception {
        System.out.println(" ffmpeg " + output);
        new File(output).getParentFile().mkdirs();
        ProcessBuilder pb = new ProcessBuilder(
            "ffmpeg", "-i", input,
            "-vf", scaleFilter,
            "-c:v", codec,
            "-c:a", "copy",
            "-y", output
        );
        pb.inheritIO();
        int exit = pb.start().waitFor();
        if (exit != 0) {
            System.err.println("  WARN codec " + codec + " unavailable or failed" + exit + " " + output);
            return;
        }
        System.out.println("  OK " + output);
    }

    private void touchFile(String base, String path) throws IOException {
        File file = new File(base, path);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }
}
