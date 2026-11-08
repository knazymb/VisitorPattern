package filesystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsVisitorTest {

    @Test
    void shouldCalculateCorrectStatistics() {

        DirectoryNode root = new DirectoryNode("Root");

        DirectoryNode folder1 = new DirectoryNode("Folder1");
        DirectoryNode folder2 = new DirectoryNode("Folder2");

        root.addChild(new FileNode("a.txt", 100));

        folder1.addChild(new FileNode("b.txt", 200));

        folder2.addChild(new FileNode("c.txt", 300));

        folder1.addChild(folder2);
        root.addChild(folder1);

        StatisticsVisitor visitor = new StatisticsVisitor();

        visitor.analyze(root);

        assertEquals(3, visitor.getFileCount());
        assertEquals(3, visitor.getDirectoryCount());
        assertEquals(3, visitor.getMaxDepth());
    }

    @Test
    void shouldHandleEmptyDirectory() {

        DirectoryNode root = new DirectoryNode("Empty");

        StatisticsVisitor visitor = new StatisticsVisitor();

        visitor.analyze(root);

        assertEquals(0, visitor.getFileCount());
        assertEquals(1, visitor.getDirectoryCount());
        assertEquals(0, visitor.getMaxDepth());
    }

    @Test
    void shouldResetStatisticsOnRepeatedExecution() {

        DirectoryNode root = new DirectoryNode("Root");

        root.addChild(new FileNode("a.txt", 100));

        StatisticsVisitor visitor = new StatisticsVisitor();

        visitor.analyze(root);

        assertEquals(1, visitor.getFileCount());

        visitor.analyze(root);

        assertEquals(1, visitor.getFileCount());
        assertEquals(1, visitor.getDirectoryCount());
    }
}