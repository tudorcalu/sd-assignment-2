package org.example.pipeline;

import org.example.models.VideoFile;
import org.example.pipeline.ingest.IngestService;
import org.example.pipeline.analysis.AnalysisService;
import org.example.pipeline.visuals.VisualsService;
import org.example.pipeline.audiotext.AudioTextService;
import org.example.pipeline.compliance.ComplianceService;
import org.example.pipeline.packaging.PackagingService;

public class WorkflowOrchestrator {
    private PipelinePhase currentPhase;
    private VideoFile masterFile;
    private IngestService ingestService;
    private AnalysisService analysisService;
    private VisualsService visualsService;
    private AudioTextService audioTextService;
    private ComplianceService complianceService;
    private PackagingService packagingService;

    public WorkflowOrchestrator(VideoFile masterFile) {
        this.masterFile = masterFile;
        this.currentPhase = PipelinePhase.START;
        this.ingestService = new IngestService();
        this.analysisService = new AnalysisService();
        this.visualsService = new VisualsService();
        this.audioTextService = new AudioTextService();
        this.complianceService = new ComplianceService();
        this.packagingService = new PackagingService();
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
        audioTextService.process(masterFile);
        
        transitionTo(PipelinePhase.COMPLIANCE);
        complianceService.process(masterFile);
        
        transitionTo(PipelinePhase.PACKAGING);
        packagingService.process(masterFile);
        
        transitionTo(PipelinePhase.COMPLETE);

        System.out.println("Pipeline finished successfully for " + masterFile.getFilename());
    }
    
    private void transitionTo(PipelinePhase newPhase) {
        System.out.println("Transitioning " + currentPhase + " -> " + newPhase);
        this.currentPhase = newPhase;
    }
}
