package filesystem;

public abstract class Node implements Element {

    private final String name;
    private DirectoryNode parent;

    public Node(String name) {
        if (name == null || name.isBlank()
                || name.contains("/")
                || name.contains("\\")
                || name.equals(".")
                || name.equals("..")) {
            throw new IllegalArgumentException("Invalid name");
        }

        this.name = name;
        this.parent = null;
    }

    @Override
    public String getName() {
        return name;
    }

    public DirectoryNode getParent() {
        return parent;
    }

    void setParent(DirectoryNode parent) {
        this.parent = parent;
    }

    @Override
    public abstract void accept(Visitor visitor);
}