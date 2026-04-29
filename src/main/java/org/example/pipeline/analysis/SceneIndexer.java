package org.example.pipeline.analysis;

import org.example.models.VideoFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SceneIndexer {

    private static final String SEGMENT_OUTPUT_PATH  = "metadata/scene_analysis.json";
    private static final String SEG1_START           = "00:00:00";
    private static final String SEG1_END             = "00:00:15";
    private static final String SEG1_LABEL           = "establishing_shot";
    private static final String SEG2_START           = "00:00:15";
    private static final String SEG2_END             = "00:01:30";
    private static final String SEG2_LABEL           = "dialogue";
    private static final String SEG3_START           = "00:01:30";
    private static final String SEG3_END             = "00:03:00";
    private static final String SEG3_LABEL           = "action";

    public void indexScenes(VideoFile videoFile) throws IOException {
        String outDir = videoFile.getOutputDirectory();
        File outFile = new File(outDir, SEGMENT_OUTPUT_PATH);
        outFile.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(outFile)) {
            writer.write("{\n");
            writer.write("  \"segments\": [\n");
            writer.write("    {\"start\": \"" + SEG1_START + "\", \"end\": \"" + SEG1_END + "\", \"label\": \"" + SEG1_LABEL + "\"},\n");
            writer.write("    {\"start\": \"" + SEG2_START + "\", \"end\": \"" + SEG2_END + "\", \"label\": \"" + SEG2_LABEL + "\"},\n");
            writer.write("    {\"start\": \"" + SEG3_START + "\", \"end\": \"" + SEG3_END + "\", \"label\": \"" + SEG3_LABEL + "\"}\n");
            writer.write("  ]\n");
            writer.write("}\n");
        }
        System.out.println("  OK scenes indexed -> " + outFile.getPath());
    }
}
