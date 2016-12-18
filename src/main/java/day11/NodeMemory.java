package day11;

import java.util.HashSet;
import java.util.Set;

public enum NodeMemory {

    INSTANCE;

    public Set<Node> allNodes = new HashSet<>();


    public boolean contains(Node node) {
        if (allNodes.contains(node)) {
            return true;
        }
        return false;
    }

    public void addNode() {

    }
}
