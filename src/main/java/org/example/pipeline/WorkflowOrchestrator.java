package org.example.pipeline;

import org.example.models.VideoFile;

public class WorkflowOrchestrator {
    private PipelinePhase currentPhase;
    private VideoFile masterFile;

    public WorkflowOrchestrator(VideoFile masterFile) {
        this.masterFile = masterFile;
        this.currentPhase = PipelinePhase.START;
    }

    public void runPipeline() {
        System.out.println("Pipeline starting for: " + masterFile.getFilename());
        
        transitionTo(PipelinePhase.INGEST);
        transitionTo(PipelinePhase.ANALYSIS);
        transitionTo(PipelinePhase.VISUALS);
        transitionTo(PipelinePhase.AUDIO_TEXT);
        transitionTo(PipelinePhase.COMPLIANCE);
        transitionTo(PipelinePhase.PACKAGING);
        transitionTo(PipelinePhase.COMPLETE);

        System.out.println("Pipeline finished successfully for " + masterFile.getFilename());
    }
    
    private void transitionTo(PipelinePhase newPhase) {
        System.out.println("Transitioning " + currentPhase + " -> " + newPhase);
        this.currentPhase = newPhase;
    }
}
