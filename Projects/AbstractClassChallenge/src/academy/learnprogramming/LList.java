package academy.learnprogramming;

public class LList implements NodeList {

    private ListItem root = null;

    public LList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if (this.root == null) {
            //list was empty so this become head of list
            this.root = newItem;
            return true;
        }

        // like a doubly linked list
        ListItem currentItem = this.root;
        while(currentItem != null) {
            int comparison = (currentItem.compareTo(newItem));
            if (comparison < 0) {
                // newItem is greater, move right if possible
                if (currentItem.next() != null) {
                    currentItem = currentItem.next();
                } else {
                    // no next at end of list, so insert at end
                    // sets previous of what's returned from setNext()
                    currentItem.setNext(newItem).setPrevious(currentItem);
                    // if we didn't do this then prev -> null, would be like an orphaned record when moving backwards
                    return true;
                }
            } else if (comparison > 0) {
                // insert before
                if (currentItem.previous() != null) {
                    // relies on the fact that we're returning the item and setting on the item that's been returned
                    currentItem.previous().setNext(newItem).setPrevious(currentItem.previous());
                    newItem.setNext(currentItem).setPrevious(newItem);
                } else {
                    // the node with a prev is the root, new head
                    newItem.setNext(this.root).setPrevious(newItem);
                    this.root = newItem;
                }
                return true;
            } else {
                // equal
                System.out.println(newItem.getValue() + " is already present, not added.");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());
        }

        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);

            if (comparison == 0) {
                //found item to delete, check head
                if (currentItem == this.root) {
                    this.root = currentItem.next();
                } else {
                    currentItem.previous().setNext(currentItem.next());
                    if (currentItem.next() != null) {
                        currentItem.next().setPrevious(currentItem.previous());
                    }
                }
                return true;
            } else if (comparison < 0) {
                currentItem = currentItem.next();
            } else {
                // comparison > 0, we're at an item greater than the one to be deleted. item not in list
                return false;
            }
        }
        // reached end of list found nothing to delete
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty");
        } else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }
        }

    }
}
