package org.example.pipeline.compliance;

import org.example.models.VideoFile;
import java.io.File;

public class WatermarkInjector {

    private static final String INPUT_SUBPATH   = "/video/h264/720p_h264.mp4";
    private static final String OUTPUT_SUBPATH  = "/video/h264/720p_h264_watermarked.mp4";
    private static final String DRAWTEXT_FILTER =
        "drawtext=text='STUDIO_ID\\:%{pts\\:hms}':fontsize=18:fontcolor=white@0.4:x=w-tw-10:y=10";
    private static final String VIDEO_CODEC     = "libx264";

    public void injectWatermark(VideoFile videoFile) throws Exception {
        String base   = videoFile.getOutputDirectory();
        String input  = base + INPUT_SUBPATH;
        String output = base + OUTPUT_SUBPATH;
        new File(output).getParentFile().mkdirs();

        ProcessBuilder pb = new ProcessBuilder(
            "ffmpeg", "-i", input,
            "-vf", DRAWTEXT_FILTER,
            "-c:v", VIDEO_CODEC,
            "-c:a", "copy",
            "-y", output
        );
        pb.inheritIO();
        int exit = pb.start().waitFor();
        if (exit != 0) {
            throw new RuntimeException("ffmpeg watermark injection failed with exit code " + exit);
        }
        System.out.println("  OK watermark applied -> " + output);
    }
}
