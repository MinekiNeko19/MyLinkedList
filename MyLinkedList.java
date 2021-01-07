import java.lang.IndexOutOfBoundsException;

public class MyLinkedList{
    private int size;
    private Node start,end;  
    public MyLinkedList() {
        size = 0;
        start = null;
        end = null;
    }
    public int size() {
        return size;
    };
    public boolean add(String value) {
        size++;
        if (start == null) {
            start = new Node(value);
            end = start;
            return true;
        }
        Node temp = new Node(value);
        end.setNext(temp);
        temp.setPrev(end);
        end = temp;
        return true;
    }
    public void add(int index, String value) {
        Node temp = nodeAtIndex(index);

    }
    public String get(int index) {
        return start.next().data();
        // not correct
    }
    public String set(int index, String value) {
        return "";
    }
    public String toString() {
        return "";
    }
    //Any helper method that returns a Node object MUST BE PRIVATE!
    private Node nodeAtIndex(int index) {
        if (index < 1 || index > size()) throw new IndexOutOfBoundsException();
        for (int i = 0; i < index; i++) {
            start=start.next();
        }
        return start;
    }

    private void reset() {
        while (start.prev() != null) {
            start = start.prev();
        }
        while (end.next() != null) {
            end = end.next();
        }
    }

    public static void main(String[] args) {
        // Testing size and add(String value) GET(INT INDEX) CURRENTLY IS NOT CORRECT
        MyLinkedList a = new MyLinkedList();
        a.add("primodial soup");
        // System.out.println(a.size());
        // // System.out.println(a.get(0));
        a.add("unicellular organism");
        // System.out.println(a.size());
        // System.out.println(a.get(0));

        a.add("multicellular organism"); a.add("plants"); a.add("aquatic animals");

        //testing helper method nodeAtIndex
        System.out.println(a.nodeAtIndex(3).data());

        //testing helper method reset()
        // a.reset();
        // System.out.println(a.start.prev());
        // System.out.println(a.end.next());


        //testing add(index, value)

    }
   }