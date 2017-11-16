public interface MyTree<V extends Comparable<V>> {
    Boolean delete(V value);
    Boolean insert(V value);
    V find(V value);
    V findNext(V value);
    V findPrev(V value);
}
