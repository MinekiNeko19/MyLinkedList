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
        if (index==size()) {
            add(value);
        } else {
            Node temp = new Node(value);
            nodeAtIndex(index); // start gets set to the node at index; helper can throw exception
            size++;

            // links prev and temp
            temp.setPrev(start.prev());
            if (start.prev()!=null) {
                start.prev().setNext(temp);
            }

            // connecting temp and start
            temp.setNext(start);
            start.setPrev(temp);
        }

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
        String s = "[";
        while (start.next() != null) {
            s+=start.data();
            start = start.next();
            s+=", ";
            if (start.next() == null) {
                s+= start.data();
            }
        }
        reset();
        return s+"]";
    }

    public String toStringReversed() {
        reset();
        String s = "[";
        while (end.prev() != null) {
            s+=end.data();
            end = end.prev();
            s+=", ";
            if (end.prev() == null) {
                s+= end.data();
            }
        }
        reset();
        return s+"]";
    }

    public String remove(int index) {
        reset();
        size--;
        // remove head
        if (index==0) {
            String s = start.data();
            start = start.next();
            start.prev().setNext(null);
            start.setPrev(null);
            return s;
        }
        // remove tail
        if (index==size()) {
            String s = end.data();
            end = end.prev();
            end.next().setPrev(null);
            end.setNext(null);
            return s;
        }
        // one element

        // remove middle
        return "";
    }

    /*
    *@postcondition: All of the elements from other are removed from the other, and connected to the end of this linked list.
    *@postcondition: The size of other is reduced to 0.
    *@postcondition: The size of this is now the combined sizes of both original lists
    */
    public void extend(MyLinkedList other){

    }

    //Any helper method that returns a Node object MUST BE PRIVATE!
    private Node nodeAtIndex(int index) {
        reset();
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
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
        // a.add("unicellular organism");
        // System.out.println(a.size());
        // System.out.println(a.get(0));

        a.add("multicellular organism"); a.add("plants"); a.add("aquatic animals");

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

        // REMEMBER TO TEST FOR EMPTY NODES
        //testing remove
        //head
        // System.out.println(a.remove(0));
        // System.out.println(a.get(0));
        // System.out.println(a.toString());
        //tail
        System.out.println(a.remove(a.size()-1));
        System.out.println(a.get(a.size()-1));
        System.out.println(a.toString());
    }
   }