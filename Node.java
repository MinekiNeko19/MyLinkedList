public class Node{
    public Node(String value) {
        data = value;
        next = null;
        prev = null;
    }
    private String data;
    private Node next,prev;
    //write get/set methods for all three instance variables.

    public void setData(String value) {
        data = value;
    }
    public void setNext(Node n) {
        next = n;
    }
    public void setPrev(Node n) {
        prev = n;
    }
}