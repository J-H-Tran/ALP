package academy.learnprogramming;

public interface NodeList {
    // every data structure that we create must have a starting node that's why we put in getRoot()
    ListItem getRoot();
    boolean addItem(ListItem item);
    boolean removeItem(ListItem item);
    void traverse(ListItem root);
}
