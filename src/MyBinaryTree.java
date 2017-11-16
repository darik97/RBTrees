import java.util.ArrayDeque;

public class MyBinaryTree<V extends Comparable<V>> implements MyTree<V> {

    protected RBNode<V> root;

    public MyBinaryTree() {
        root = null;
    }

    public Boolean checkForColorizing() {
        int leftHeight;
        int rightHeight;

        RBNode node = root;
        node.setBlackColor();
        if (node.getLeft() == null && node.getRight() == null || node == null) {
            return true;
        }

        leftHeight = getHeight(root.getLeft());
        rightHeight = getHeight(root.getRight());

        if (leftHeight > rightHeight)
            return 2 * rightHeight >= leftHeight;
        else
            return 2 * leftHeight >= rightHeight;
    }

    public Boolean isTrueRedBlackTree() {

        RBNode node = root;
        int height = 0;
        RBNode nodePrev = null;

        if (!node.isBlack()) {
            return false;
        }

        while (node != null) {
            nodePrev = node;
            node = node.getLeft();
        }
        height = nodePrev.getBlackheight();

        while (!node.isVisited()) {
            if (node.isRed() && (!node.getLeft().isBlack() || !node.getRight().isBlack()))
                return false;
            if (node.getLeft() == null || node.getLeft().isBlack() && node.getLeft().isVisited()) {
                if (node.getLeft() == null && node.getBlackheight() != height)
                    return false;
                if ((node.getRight() == null || node.getRight().isBlack() && node.getRight().isVisited()) && node != root) {
                    if (node.getRight() == null && node.getBlackheight() != height)
                        return false;
                    node.setAsVisited();
                    node = node.getParent();
                } else if (node.getRight() != null && !node.getRight().isVisited()) {
                    node = node.getRight();
                }
            }
            else node = node.getLeft();
        }

        return true;
    }

    @Override
    public Boolean delete(V value) {
        return null;
    }

    @Override
    public Boolean insert(V value) {
        RBNode newnode = new RBNode(value);
        RBNode prev = null;
        RBNode current = root;
        while(current != null) {
            prev = current;
            if(newnode.compareTo(current) >= 0) // if current < newnode
                current = current.getRight();
            else
                current = current.getLeft();
        }
        if(prev == null) {
            root = newnode;
            return true;
        }
        else if(newnode.compareTo(prev) >= 0) // if newnode > prev
            prev.setRight(newnode);
        else
            prev.setLeft(newnode);
        return true;
    }

    @Override
    public V find(V value) {
        return (V) find(root, value).getValue();
    }

    protected RBNode find(RBNode node, V value) {
        if(node == null) return null;
        if(value.equals(node.getValue())) return node;
        if(value.compareTo((V)node.getValue()) > 0) // if value > node
            return find(node.getRight(), value);
        else
            return find(node.getLeft(), value);
    }

    @Override
    public V findNext(V value) {
        RBNode finded = find(root, value);
        RBNode nextVal = null;
        if(finded == null) return null;
        if(!finded.isLeaf()) {
            nextVal = minimum(finded.getRight());
        }
        while (nextVal.getParent() != null && nextVal.compareTo(finded) == 0) {
            nextVal = nextVal.getParent();
        }
        if(nextVal.compareTo(finded) == 0) return null;
        return (V) nextVal.getValue();
    }

    @Override
    public V findPrev(V value) {
        RBNode finded = find(root, value);
        if(finded == null) return null;
        RBNode preVal = null;
        if(!finded.isLeaf()) {
            preVal = maximum(finded.getLeft());
        }
        else return null;
        return (V) preVal.getValue();
    }

    public void clear() {
        root = null;
    }

    protected int getHeight(RBNode begin) {
        if(begin == null) return 0;
        ArrayDeque<RBNode> deque = new ArrayDeque<>();
        deque.add(begin);
        int height = 1;
        RBNode last = null;
        while(!deque.isEmpty()) {
            last = deque.pop();
            if(last.getLeft() != null)
                deque.add(last.getLeft());
            if(last.getRight() != null)
                deque.add(last.getRight());
        }

        while(last != begin) {
            height++;
            if(last == null) break;
            last = last.getParent();
        }
        return height;
    }

    protected String getMargin(int count) {
        String str = "";
        for(int i = 0; i < count; i++)
            str += "\t";
        return str;
    }

    public V minimum() {
        return (V) minimum(root).getValue();
    }

    public V maximum() {
        return (V) maximum(root).getValue();
    }

    protected RBNode maximum(RBNode node) {
        RBNode current = node;
        RBNode prev = null;
        while (current != null) {
            prev = current;
            current = current.getRight();
        }
        return prev;
    }

    protected RBNode minimum(RBNode node) {
        RBNode current = node;
        RBNode prev = null;
        while (current != null) {
            prev = current;
            current = current.getLeft();
        }
        return prev;
    }

    @Override
    public String toString() {
        if(root == null) return "";
        String output = "";
        String shift = "";
        int height = getHeight(root);
        int cols = (int) (Math.pow(2, height) - 1);
        RBNode nullNode = new RBNode(0);
        int count = 0;
        int level = 1;

        ArrayDeque<RBNode> deque = new ArrayDeque<>();
        deque.add(root);
        while(!deque.isEmpty()) {
            count++;
            RBNode x = deque.pop();
            if(x == nullNode)
                output += getMargin(cols / 2) + "(  )" + getMargin(cols / 2) + "\t";
            else
                output += shift + getMargin(cols / 2) + x + getMargin(cols / 2) + "\t";
            if (x.getLeft() != null)
                deque.add(x.getLeft());
            else
                deque.add(nullNode);
            if (x.getRight() != null)
                deque.add(x.getRight());
            else
                deque.add(nullNode);
            if(count == Math.pow(2, level) - 1) {
                output += "\n";
                cols /= 2;
                level++;
            }
            if(height < level)
                break;

        }
        return output;
    }
}
