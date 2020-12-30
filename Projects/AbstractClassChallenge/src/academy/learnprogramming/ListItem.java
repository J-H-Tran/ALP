package academy.learnprogramming;

public abstract class ListItem {
    // each listItem object can hold a reference to 'previous' and 'next' item
    // declared as protected rather than private because we need to be able to access them from our concrete subclasses
    // If we left access modifier off would've made the member variables package-private: subclasses in same package
    // would be able to access them, but not subclasses in other classes, so we use protected
    protected ListItem rightLink = null;
    protected ListItem leftLink = null;

    protected Object value;

    // any concrete class that inherits ListItem will need a constructor to set the value
    // because it needs to be done each time it makes sense to create a constructor in this class
    public ListItem(Object value) {
        this.value = value;
    }

    // declares what must be implemented by the derived classes
    abstract ListItem next();   // returns reference to next listItem in the List
    abstract ListItem setNext(ListItem item);   // sets the reference for the next item to refer to
    abstract ListItem previous();
    abstract ListItem setPrevious(ListItem item);

    abstract int compareTo(ListItem item);  // retruns 0 if equal, '-' number if less, '+' number if greater

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
