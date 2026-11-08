package filesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryNode extends Node {

    private final List<Node> children;

    public DirectoryNode(String name) {
        super(name);
        children = new ArrayList<>();
    }

    public void addChild(Node child) {

        if (child == null) {
            throw new IllegalArgumentException("Child cannot be null");
        }

        if (child.getParent() != null) {
            throw new IllegalArgumentException(
                    "This element already has a parent"
            );
        }

        for (Node current : children) {
            if (current.getName().equals(child.getName())) {
                throw new IllegalArgumentException(
                        "Duplicate child name: " + child.getName()
                );
            }
        }

        Node current = this;

        while (current != null) {
            if (current == child) {
                throw new IllegalArgumentException(
                        "Cycle is not allowed"
                );
            }

            current = current.getParent();
        }

        children.add(child);
        child.setParent(this);
    }

    public List<Node> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}