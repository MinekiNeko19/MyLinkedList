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
        if (size == 0) return "[]";
        if (size == 1) return "[" + start.data() + "]";
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
        if (size == 0) return "[]";
        if (size == 1) return "[" + start.data() + "]";
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
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        reset();
        size--; // this changes the size immediately; this should be taken into account when using size as a reference
        // remove head
        if (index==0 && size > 0) {
            String s = start.data();
            start = start.next();
            start.prev().setNext(null);
            start.setPrev(null);
            return s;
        }
        // remove tail
        if (index==size() && size > 0) {
            String s = end.data();
            end = end.prev();
            end.next().setPrev(null);
            end.setNext(null);
            return s;
        }
        // one element
        if (size()==0) {
            String s = start.data();
            start.setData(null);
            end.setData(null);
            return s;
        }
        // remove middle
        nodeAtIndex(index);
        String s = start.data();
        start.prev().setNext(start.next());
        start.next().setPrev(start.prev());
        Node temp = start;
        start = nodeAtIndex(0);
        temp.setNext(null);
        temp.setPrev(null);
        return s;
    }

    /*
    *@postcondition: All of the elements from other are removed from the other, and connected to the end of this linked list.
    *@postcondition: The size of other is reduced to 0.
    *@postcondition: The size of this is now the combined sizes of both original lists
    */
    public void extend(MyLinkedList other){
        if (this.size == 0) {
            this.start = new Node(other.start.data());
            this.start.setNext(other.start.next());
            other.start.next().setPrev(this.start);

            this.end = new Node(other.end.data());
            this.end.setPrev(other.end.prev());
            other.end.prev().setNext(this.end);

            other.start.setNext(null);
            other.end.setPrev(null);
        }
        else if (this.size == 1 && other.size == 1) {
            add(other.start.data());
        }
        else if (other.size > 1 && this.size > 1) {
            this.end.setNext(new Node(other.start.data())); // links this end to other start
            this.end.next().setNext(other.start.next());
            this.end.next().setPrev(end);
            other.start.next().setPrev(this.end.next());    // links other start's next to this end

            this.end = new Node(other.end.data());  // moving end
            this.end.setPrev(other.end.prev());
            other.end.prev().setNext(this.end);

            other.start.setNext(null);
            other.end.setPrev(null);
        }
        this.size += other.size;
        other.size = 0;
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
        // Testing size and add(String value)
        MyLinkedList a = new MyLinkedList();
        a.add("primordial soup");
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

        // REMEMBER TO TEST FOR EMPTY NODES
        //testing remove
        //head
        // System.out.println(a.remove(0));
        // System.out.println(a.get(0));
        // System.out.println(a.toString());
        //tail
        // System.out.println(a.remove(a.size()-1));
        // System.out.println(a.get(a.size()-1));
        // System.out.println(a.toString());
        //one element
        // System.out.println(a.toString());
        // System.out.println(a.remove(0));
        // System.out.println(a.get(0));
        // System.out.println(a.toString());
        //middle
        // System.out.println(a.toString());
        // System.out.println(a.remove(2));
        // System.out.println(a.toString());

        //testing extend
        // MyLinkedList b = new MyLinkedList();
        // b.add("land fish"); b.add("legs no fins"); b.add("fur?");
        // System.out.println(b.toString());
        // a.extend(b);
        // System.out.println(a.toString());
        // System.out.println(b.toString());

        MyLinkedList c = new MyLinkedList();
        c.add("meow");
        System.out.println(a.toString());
        System.out.println(c.toString());
        c.extend(a);
        System.out.println(a.toString());
        System.out.println(c.toString());
        // System.out.println(c.size());

        //Mr. K's quick testing code for part 2
        // MyLinkedList a = new MyLinkedList();
        // MyLinkedList b = new MyLinkedList();
        // for(int i = 0; i < 10; i++){
        //   if(i < 5){
        //     a.add(i+"");
        //   }else{
        //     b.add(i+"");
        //   }
        // }
        // System.out.println();
        // System.out.println("A:"+a+a.size());
        // System.out.println("B:"+b+b.size());
    
        // a.extend(b);
        // System.out.println("A:"+a+a.size());
        // System.out.println("B:"+b+b.size());
        // System.out.println("A reversed:"+a.toStringReversed()+a.size());
        // System.out.println("B reversed:"+b.toStringReversed()+b.size());
    }
   }