package org.example.pipeline;

import org.example.models.VideoFile;

public class WorkflowOrchestrator {
    private PipelinePhase currentPhase;
    private VideoFile masterFile;
    private IngestService ingestService;
    private AnalysisService analysisService;
    private VisualsService visualsService;

    public WorkflowOrchestrator(VideoFile masterFile) {
        this.masterFile = masterFile;
        this.currentPhase = PipelinePhase.START;
        this.ingestService = new IngestService();
        this.analysisService = new AnalysisService();
        this.visualsService = new VisualsService();
    }

    public void runPipeline() {
        System.out.println("Pipeline starting for: " + masterFile.getFilename());
        
        transitionTo(PipelinePhase.INGEST);
        boolean ingestSuccess = ingestService.process(masterFile);
        if (!ingestSuccess) {
            System.out.println("Pipeline failed at INGEST phase.");
            return;
        }
        
        transitionTo(PipelinePhase.ANALYSIS);
        analysisService.process(masterFile);
        
        transitionTo(PipelinePhase.VISUALS);
        visualsService.process(masterFile);
        
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
