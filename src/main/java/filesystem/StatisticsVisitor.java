package filesystem;

public class StatisticsVisitor implements Visitor {

    private int fileCount;
    private int directoryCount;
    private int maxDepth;
    private int currentDepth;

    public StatisticsVisitor() {
        reset();
    }

    private void reset() {
        fileCount = 0;
        directoryCount = 0;
        maxDepth = 0;
        currentDepth = 0;
    }

    public void analyze(Element root) {
        reset();
        root.accept(this);
    }

    @Override
    public void visit(FileNode file) {

        fileCount++;

        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    @Override
    public void visit(DirectoryNode directory) {

        directoryCount++;

        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }

        currentDepth++;

        for (Node child : directory.getChildren()) {
            child.accept(this);
        }

        currentDepth--;
    }

    public int getFileCount() {
        return fileCount;
    }

    public int getDirectoryCount() {
        return directoryCount;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}