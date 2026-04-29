package org.example.pipeline;

import org.example.models.VideoFile;
import org.example.pipeline.ingest.IngestService;
import org.example.pipeline.analysis.AnalysisService;
import org.example.pipeline.visuals.VisualsService;
import org.example.pipeline.audiotext.AudioTextService;
import org.example.pipeline.compliance.ComplianceService;
import org.example.pipeline.packaging.PackagingService;
import java.util.concurrent.atomic.AtomicReference;

public class WorkflowOrchestrator {
    private PipelinePhase currentPhase;
    private final VideoFile masterFile;
    private final IngestService ingestService;
    private final AnalysisService analysisService;
    private final VisualsService visualsService;
    private final AudioTextService audioTextService;
    private final ComplianceService complianceService;
    private final PackagingService packagingService;

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

        System.out.println("Transitioning " + currentPhase + " -> VISUALS + AUDIO_TEXT");
        this.currentPhase = PipelinePhase.VISUALS;

        AtomicReference<RuntimeException> visualsError   = new AtomicReference<>();
        AtomicReference<RuntimeException> audioTextError = new AtomicReference<>();

        Thread visualsThread = new Thread(() -> {
            System.out.println("[VISUALS thread] Starting visuals processing");
            try {
                visualsService.process(masterFile);
            } catch (RuntimeException e) {
                visualsError.set(e);
            }
            System.out.println("[VISUALS thread] Done.");
        }, "visuals-worker");

        Thread audioTextThread = new Thread(() -> {
            System.out.println("[AUDIO_TEXT thread] Starting audio/text processing");
            try {
                audioTextService.process(masterFile);
            } catch (RuntimeException e) {
                audioTextError.set(e);
            }
            System.out.println("[AUDIO_TEXT thread] Done.");
        }, "audio-text-worker");

        visualsThread.start();
        audioTextThread.start();

        try {
            visualsThread.join();
            audioTextThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Pipeline interrupted during parallel phase", e);
        }

        if (visualsError.get() != null) {
            throw visualsError.get();
        }
        if (audioTextError.get() != null) {
            throw audioTextError.get();
        }

        System.out.println("Both VISUALS and AUDIO_TEXT phases complete.");

        transitionTo(PipelinePhase.COMPLIANCE);
        complianceService.process(masterFile);

        transitionTo(PipelinePhase.PACKAGING);
        packagingService.process(masterFile);

        transitionTo(PipelinePhase.COMPLETE);
        System.out.println("Pipeline finished successfully for " + masterFile.getFilename());
    }

    private void transitionTo(PipelinePhase newPhase) {
        System.out.println("Transitioning " + currentPhase + "to" + newPhase);
        this.currentPhase = newPhase;
    }
}
