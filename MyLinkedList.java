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
        size++;
        Node temp = new Node(value);
        nodeAtIndex(index); // start gets set to the node at index; helper can throw exception

        // links prev and start
        temp.setPrev(start.prev());
        start.prev().setNext(temp);

        // connecting temp and start
        temp.setNext(start);
        start.setPrev(temp);

    }

    public String get(int index) {
        return nodeAtIndex(index).data(); // helper can throw exception
    }

    public String set(int index, String value) {
        nodeAtIndex(index); // helper can throw exception
        String temp = start.data();
        start.setData(value);
        return temp;
    }

    public String toString() {
        reset();
        String s = "";
        while (start.next() != null) {
            s+=start.data();
            start = start.next();
            if (start.next() != null) {
                s+=", ";
            }
        }
        reset();
        return s;
    }

    public String toStringReversed() {
        reset();
        String s = "";
        while (end.prev() != null) {
            s+=end.data();
            end = end.prev();
            if (end.prev() != null) {
                s+=", ";
            }
        }
        reset();
        return s;
    }

    //Any helper method that returns a Node object MUST BE PRIVATE!
    private Node nodeAtIndex(int index) {
        reset();
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
        // MyLinkedList a = new MyLinkedList();
        // a.add("primodial soup");
        // System.out.println(a.size());
        // // System.out.println(a.get(0));
        // a.add("unicellular organism");
        // System.out.println(a.size());
        // System.out.println(a.get(0));

        // a.add("multicellular organism"); a.add("plants"); a.add("aquatic animals");

        //testing helper method nodeAtIndex
        // System.out.println(a.nodeAtIndex(3).data());

        //testing helper method reset()
        // a.reset();
        // System.out.println(a.start.prev());
        // System.out.println(a.end.next());


        //testing add(index, value)
        // a.add(3,"algae");
        // a.reset();
        // System.out.println(a.nodeAtIndex(3).data());

        //testing get(index)
        // System.out.println(a.get(4));
        // System.out.println(a.get(2));

        //testing set
        // System.out.println(a.set(4, "sea plants"));
        // System.out.println(a.get(4));

        // testing toString
        // System.out.println(a.toString());
        // System.out.println(a.toStringReversed());

        // testing exceptions
        // try {
            // a.set(10, "hooman");
            // a.get(12);
        //     a.add(50,"rocks");
        // } catch (IndexOutOfBoundsException e) {
        //     e.printStackTrace();
        //     System.out.println("negative index or index greater than size");
        // }
    }
   }