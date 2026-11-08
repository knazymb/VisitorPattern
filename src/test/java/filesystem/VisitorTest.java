package filesystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisitorTest {

    @Test
    void fileShouldCallFileVisitMethod() {

        FileNode file = new FileNode("test.txt", 100);

        boolean[] called = {false};

        Visitor visitor = new Visitor() {

            @Override
            public void visit(FileNode file) {
                called[0] = true;
            }

            @Override
            public void visit(DirectoryNode directory) {
                fail("Wrong visit method");
            }
        };

        file.accept(visitor);

        assertTrue(called[0]);
    }

    @Test
    void directoryShouldCallDirectoryVisitMethod() {

        DirectoryNode directory = new DirectoryNode("Test");

        boolean[] called = {false};

        Visitor visitor = new Visitor() {

            @Override
            public void visit(FileNode file) {
                fail("Wrong visit method");
            }

            @Override
            public void visit(DirectoryNode directory) {
                called[0] = true;
            }
        };

        directory.accept(visitor);

        assertTrue(called[0]);
    }
}