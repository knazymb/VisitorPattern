package filesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class SearchVisitor implements Visitor {

    private final Predicate<String> nameCriterion;
    private final long minSize;

    private final List<String> results;

    private String currentPath;
    private boolean firstDirectory;

    public SearchVisitor(Predicate<String> nameCriterion, long minSize) {

        if (nameCriterion == null) {
            throw new IllegalArgumentException(
                    "Name criterion cannot be null"
            );
        }

        if (minSize < 0) {
            throw new IllegalArgumentException(
                    "Minimum size cannot be negative"
            );
        }

        this.nameCriterion = nameCriterion;
        this.minSize = minSize;

        results = new ArrayList<>();
        currentPath = "";
        firstDirectory = true;
    }

    public List<String> search(Element root) {

        results.clear();
        currentPath = "";
        firstDirectory = true;

        root.accept(this);

        Collections.sort(results);

        return new ArrayList<>(results);
    }

    @Override
    public void visit(FileNode file) {

        boolean nameMatches = nameCriterion.test(file.getName());
        boolean sizeMatches = file.getSize() >= minSize;

        if (nameMatches && sizeMatches) {

            String path = currentPath + file.getName();

            results.add(path);
        }
    }

    @Override
    public void visit(DirectoryNode directory) {

        String oldPath = currentPath;

        if (firstDirectory) {
            firstDirectory = false;
        } else {
            currentPath = currentPath + directory.getName() + "/";
        }

        for (Node child : directory.getChildren()) {
            child.accept(this);
        }

        currentPath = oldPath;
    }
}