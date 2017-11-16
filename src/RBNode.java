
public class RBNode<V extends Comparable<V>> implements Comparable<RBNode<V>>{

    private RBNode left;
    private RBNode right;
    private RBNode parent;

    private V value;
    private Boolean isblack;
    private Boolean isleft;
    private Boolean isvisited;

    public RBNode() {
        isblack = true;
        isvisited = false;
    }

    public RBNode(V value) {
        this();
        this.value = value;
    }

    public Boolean setValue(V value) {
        this.value = value;
        return true;
    }

    public void setLeft(RBNode node) {
        this.left = node;
        if(this.left != null) {
            this.left.setAsLeft();
            this.left.setParent(this);
        }
    }

    public void setRight(RBNode node) {
        this.right = node;
        if(this.right != null) {
            this.right.setAsRight();
            this.right.setParent(this);
        }
    }

    public void delete() {
        if(parent != null)
            if(isleft) parent.setLeft(null);
            else parent.setRight(null);
    }

    public RBNode getNeighbor() {
        if(parent != null)
            return (isleft) ? parent.getRight() : parent.getLeft();
        return null;
    }

    public Boolean haveChilds() {
        return left != null || right != null;
    }

    public Boolean setParent(RBNode parent) {
        this.parent = parent;
        return true;
    }

    public RBNode getParent() {
        return parent;
    }

    public V getValue() {
        return value;
    }

    public RBNode<V> getLeft() {
        return left;
    }

    public RBNode<V> getRight() {
        return right;
    }

    public Boolean isBlack() {
        return isblack;
    }

    public Boolean isRed() {
        return !isblack;
    }

    public void setRedColor() {
        isblack = false;
    }

    public void setBlackColor() {
        isblack = true;
    }

    public void setAsLeft() {
        this.isleft = true;
    }

    public void setAsRight() {
        this.isleft = false;
    }

    public Boolean isLeft() {
        return isleft;
    }

    public Boolean isRight() {
        return !isleft;
    }

    public Boolean isLeaf() {
        return left == null && right == null;
    }

    public void setAsVisited() {
        isvisited = true;
    }

    public void setUnvisited() {
        isvisited = false;
    }

    public Boolean isVisited() {
        return isvisited;
    }

    public Integer getBlackheight() {
        Integer count = 0;
        RBNode node = this;
        while(node != null) {
            if(node.isBlack()) count++;
            node = node.getParent();
        }
        return count;
    }

    @Override
    public int compareTo(RBNode<V> vrbNode) {
        return value.compareTo(vrbNode.getValue());
    }

    @Override
    public String toString() {
        String s = isblack ? "B" : "R";
        return s + "(" + value.toString() + ")";
    }
}
