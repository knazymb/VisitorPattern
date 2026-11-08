package filesystem;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Create root directory
        DirectoryNode root = new DirectoryNode("MyComputer");

        // Create files
        FileNode document = new FileNode("document.txt", 1000);
        FileNode photo1 = new FileNode("photo1.jpg", 2000);
        FileNode photo2 = new FileNode("photo2.png", 3000);
        FileNode report = new FileNode("report.pdf", 5000);
        FileNode notes = new FileNode("notes.txt", 500);

        // Create directories
        DirectoryNode photos = new DirectoryNode("Photos");
        DirectoryNode documents = new DirectoryNode("Documents");
        DirectoryNode projects = new DirectoryNode("Projects");

        // Add files to directories
        photos.addChild(photo1);
        photos.addChild(photo2);

        documents.addChild(report);
        projects.addChild(notes);

        // Build the file system
        root.addChild(document);
        root.addChild(photos);
        root.addChild(documents);
        root.addChild(projects);

        System.out.println("===== FILE SYSTEM =====");

        // Visitor 1: Total Size
        TotalSizeVisitor sizeVisitor = new TotalSizeVisitor();

        long totalSize = sizeVisitor.calculate(root);

        System.out.println("\n===== TOTAL SIZE =====");
        System.out.println("Total size: " + totalSize + " bytes");

        // Visitor 2: Search
        SearchVisitor searchVisitor = new SearchVisitor(
                name -> name.toLowerCase().contains("photo"),
                2500
        );

        List<String> searchResults = searchVisitor.search(root);

        System.out.println("\n===== SEARCH RESULTS =====");

        for (String path : searchResults) {
            System.out.println(path);
        }

        // Visitor 3: Statistics
        StatisticsVisitor statisticsVisitor =
                new StatisticsVisitor();

        statisticsVisitor.analyze(root);

        System.out.println("\n===== STATISTICS =====");

        System.out.println(
                "Files: " + statisticsVisitor.getFileCount()
        );

        System.out.println(
                "Directories: " + statisticsVisitor.getDirectoryCount()
        );

        System.out.println(
                "Maximum depth: " + statisticsVisitor.getMaxDepth()
        );
    }
}