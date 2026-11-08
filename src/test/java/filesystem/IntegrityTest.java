package filesystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegrityTest {

    @Test
    void shouldRejectNegativeFileSize() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new FileNode("test.txt", -100)
        );
    }

    @Test
    void shouldRejectDuplicateNames() {

        DirectoryNode root = new DirectoryNode("Root");

        root.addChild(new FileNode("test.txt", 100));

        assertThrows(
                IllegalArgumentException.class,
                () -> root.addChild(
                        new FileNode("test.txt", 200)
                )
        );
    }

    @Test
    void shouldRejectMultipleParents() {

        DirectoryNode first = new DirectoryNode("First");
        DirectoryNode second = new DirectoryNode("Second");

        FileNode file = new FileNode("test.txt", 100);

        first.addChild(file);

        assertThrows(
                IllegalArgumentException.class,
                () -> second.addChild(file)
        );
    }

    @Test
    void shouldRejectCycles() {

        DirectoryNode root = new DirectoryNode("Root");
        DirectoryNode child = new DirectoryNode("Child");

        root.addChild(child);

        assertThrows(
                IllegalArgumentException.class,
                () -> child.addChild(root)
        );
    }

    @Test
    void shouldAllowValidNestedDirectories() {

        DirectoryNode root = new DirectoryNode("Root");
        DirectoryNode folder = new DirectoryNode("Folder");

        FileNode file = new FileNode("data.txt", 100);

        root.addChild(folder);
        folder.addChild(file);

        assertEquals(folder, file.getParent());
        assertEquals(root, folder.getParent());
    }

    @Test
    void shouldNotAllowChangingChildrenDirectly() {

        DirectoryNode root = new DirectoryNode("Root");

        assertThrows(
                UnsupportedOperationException.class,
                () -> root.getChildren().add(
                        new FileNode("test.txt", 100)
                )
        );
    }
}