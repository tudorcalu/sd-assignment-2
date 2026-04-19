package org.example;

import org.example.models.VideoFile;
import org.example.pipeline.WorkflowOrchestrator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Processing video ingest pipeline");
        VideoFile master = new VideoFile("master_100GB_101.mkv", 100_000_000_000L);
        WorkflowOrchestrator orchestrator = new WorkflowOrchestrator(master);
        orchestrator.runPipeline();
    }
}
