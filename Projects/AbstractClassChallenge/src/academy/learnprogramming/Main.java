package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {
	// write your code here
        LList list = new LList(null);
        list.traverse(list.getRoot());

        //String strData = "Darwin Brisbane Perth Melbourne Canberra Adelaide Sydney Canberra";
        String strData = "5 7 3 9 8 2 1 0 4 6";

        String[] data = strData.split(" ");

        for (String s : data) {
            // create new item with value set to the string s
            list.addItem(new Node(s));
        }

        list.traverse(list.getRoot());

        list.removeItem(new Node("3"));
        list.traverse(list.getRoot());

        list.removeItem(new Node("5"));
        list.traverse(list.getRoot());

        list.removeItem(new Node("0"));
        list.removeItem(new Node("4"));
        list.removeItem(new Node("2"));
        list.traverse(list.getRoot());

        list.removeItem(new Node("9"));
        list.traverse(list.getRoot());

        list.removeItem(new Node("8"));
        list.traverse(list.getRoot());

        list.removeItem(new Node("6"));
        list.traverse(list.getRoot());

        list.removeItem(list.getRoot());
        list.traverse(list.getRoot());

        list.removeItem(list.getRoot());
        list.traverse(list.getRoot());
    }
}
