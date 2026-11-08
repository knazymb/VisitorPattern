package filesystem;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchVisitorTest {

    @Test
    void shouldCombineNameAndSizeConditions() {

        DirectoryNode root = new DirectoryNode("Root");

        root.addChild(new FileNode("photo1.jpg", 1000));
        root.addChild(new FileNode("photo2.jpg", 3000));
        root.addChild(new FileNode("document.txt", 5000));

        SearchVisitor visitor = new SearchVisitor(
                name -> name.contains("photo"),
                2000
        );

        List<String> results = visitor.search(root);

        assertEquals(
                List.of("photo2.jpg"),
                results
        );
    }

    @Test
    void shouldReturnSortedRelativePaths() {

        DirectoryNode root = new DirectoryNode("Root");

        DirectoryNode photos = new DirectoryNode("Photos");
        DirectoryNode documents = new DirectoryNode("Documents");

        photos.addChild(new FileNode("z.jpg", 3000));
        photos.addChild(new FileNode("a.jpg", 3000));
        documents.addChild(new FileNode("b.jpg", 3000));

        root.addChild(photos);
        root.addChild(documents);

        SearchVisitor visitor = new SearchVisitor(
                name -> name.endsWith(".jpg"),
                1000
        );

        List<String> results = visitor.search(root);

        assertEquals(
                List.of(
                        "Documents/b.jpg",
                        "Photos/a.jpg",
                        "Photos/z.jpg"
                ),
                results
        );
    }

    @Test
    void shouldReturnEmptyListWhenNothingMatches() {

        DirectoryNode root = new DirectoryNode("Root");

        root.addChild(new FileNode("notes.txt", 100));

        SearchVisitor visitor = new SearchVisitor(
                name -> name.contains("photo"),
                1000
        );

        assertTrue(visitor.search(root).isEmpty());
    }

    @Test
    void shouldNotKeepOldSearchResults() {

        DirectoryNode root = new DirectoryNode("Root");

        root.addChild(new FileNode("photo.jpg", 2000));

        SearchVisitor visitor = new SearchVisitor(
                name -> name.contains("photo"),
                1000
        );

        assertEquals(1, visitor.search(root).size());
        assertEquals(1, visitor.search(root).size());
    }
}