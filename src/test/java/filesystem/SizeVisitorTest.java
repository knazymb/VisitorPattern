package filesystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SizeVisitorTest {

    @Test
    void shouldCalculateTotalSize() {

        DirectoryNode root = new DirectoryNode("Root");
        DirectoryNode folder = new DirectoryNode("Folder");
        DirectoryNode subfolder = new DirectoryNode("Subfolder");

        root.addChild(new FileNode("a.txt", 100));
        folder.addChild(new FileNode("b.txt", 200));
        subfolder.addChild(new FileNode("c.txt", 300));

        folder.addChild(subfolder);
        root.addChild(folder);

        TotalSizeVisitor visitor = new TotalSizeVisitor();

        assertEquals(600, visitor.calculate(root));
    }

    @Test
    void shouldReturnZeroForEmptyDirectory() {

        DirectoryNode root = new DirectoryNode("Empty");

        TotalSizeVisitor visitor = new TotalSizeVisitor();

        assertEquals(0, visitor.calculate(root));
    }

    @Test
    void shouldNotMultiplyResultAfterRepeatedExecution() {

        DirectoryNode root = new DirectoryNode("Root");
        root.addChild(new FileNode("a.txt", 100));

        TotalSizeVisitor visitor = new TotalSizeVisitor();

        assertEquals(100, visitor.calculate(root));
        assertEquals(100, visitor.calculate(root));
    }
}