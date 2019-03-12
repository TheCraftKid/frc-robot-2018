package frc.team0000.robot.vision;

import edu.wpi.cscore.UsbCamera;
import frc.team0000.robot.common.ObservableData;

import java.util.List;

/**
 * A utility class for managing camera inputs and performing vision recognition.
 */
public class VisionProcessor {

    private static VisionProcessor instance;

    private ObservableData<Byte[][]> lastFrame = new ObservableData<>();

    private UsbCamera camera;

    public static VisionProcessor getInstance(UsbCamera camera) {
        if (instance == null) {
            instance = new VisionProcessor(camera);
        }
        return instance;
    }

    private VisionProcessor(UsbCamera camera) {
        this.camera = camera;
        lastFrame.addObserver((frame) -> {

        });
    }

    private void monitorCamera() {
        // TODO: Check if camera is still alive
        boolean shouldStop = false;
        Thread cameraThread = new Thread(() -> {
            while (!shouldStop) {
                try {

//                    lastFrame.setValue();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        cameraThread.start();
    }

    private void processFrame(byte[][] frame) {

    }
}
