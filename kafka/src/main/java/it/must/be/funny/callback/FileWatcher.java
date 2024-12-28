package it.must.be.funny.callback;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;
public class FileWatcher {

    private final FileObject fileObject;

    public FileWatcher(FileObject fileObject) {
        this.fileObject = fileObject;
    }
    private void startWatching() throws IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path filePath = Paths.get(this.fileObject.FILE_PATH);
        Path directoryPath = filePath.getParent();
        directoryPath.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        new Thread(() -> {
            while (true) {
                try {
                    WatchKey key = watchService.take();
                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        Path changedFile = (Path) event.context();

                        Path eventFilePath = directoryPath.resolve(changedFile);
                        if (eventFilePath.equals(filePath) && kind == ENTRY_MODIFY) {
                            System.out.println("update file -> need to update properties");
                            System.out.println("before: " + fileObject);
                            FIleProcessor.readFile(fileObject);
                            System.out.println("after: "+ fileObject);
                        }
                    }
                    boolean valid = key.reset();
                    if (!valid) break;
                } catch (Exception e) {
                    System.out.println("err: "+ e.getMessage());
                }
            }
        }).start();
    }
    public void start() {
        try {
            this.startWatching();
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            System.out.println(e.toString());
        }
    }
}
