import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Created by Karan on 6/18/16.
 */

    public class Deque<Item> implements Iterable<Item> {

    private class Node<Item> {
        Item slot;
        Node prev;
        Node next;
    }

    private class DequeIterator<Item> implements Iterator<Item> {

        Node currentNode = first;

        @Override
        public boolean hasNext() {
            return (currentNode!=null);
        }

        @Override
        public Item next() {

            if(currentNode == null){
                throw new NoSuchElementException("next returned exception");
            }

            Node temp = currentNode;
            currentNode = currentNode.next;
            return (Item)temp.slot;

            /*
            if(size == 0){
                return null;
            }
            else if(size==1){
                this.currentNode = first;
                return (Item)currentNode.slot;
            }
            else{
                currentNode = currentNode.next;
                return (Item)currentNode.slot;
            }
            */
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }

        @Override
        public void forEachRemaining(Consumer<? super Item> action) {
            throw new UnsupportedOperationException("For each remaining not supported");
        }
    }


        private Deque.Node first;
        private Deque.Node last;
        private int size;

        public Deque(){
            first = last = null;
            size = 0;
        }                           // construct an empty deque
        public boolean isEmpty(){
            return (size==0);
        }                 // is the deque empty?

        public int size(){
            return size;
        }                        // return the number of items on the deque

        private Node createNode(Item item, Node prev, Node next){
            //create a new node

            if(item == null){
                throw new java.lang.NullPointerException("item is null");
            }

            Node<Item> newnode = new Node<Item>();
            newnode.slot = item;
            newnode.prev = prev;
            newnode.next = next;
            return newnode;
        }

        public void addFirst(Item item){

            Node newnode = createNode(item, null, null);
            if(first == null){
                first = last = newnode;
            }
            else{
                 newnode.next = first;
                 first.prev = newnode;
                 first = newnode;
            }
            size++;
        }          // add the item to the front

        public void addLast(Item item){
            Node newnode = createNode(item, null, null);
            if(first == null){
                first = last = newnode;
            }
            else{
                newnode.prev = last;
                last.next = newnode;
                last = newnode;
            }
            size++;
        }           // add the item to the end

        public Item removeFirst(){

            if(first == null){
                throw new NoSuchElementException("Queue is empty");
            }

            else if(size == 1){
                size--;
                Node tempfirst = first;
                first = last = null;
                return (Item)tempfirst.slot;
            }

            else{
                Node secondnode = first.next;
                secondnode.prev  = null;
                first.next = null;
                Node tempfirst = first;
                first = secondnode;
                size--;
                return (Item)tempfirst.slot;
            }

        }                // remove and return the item from the front
        public Item removeLast() {

            if (first == null) {
                throw new NoSuchElementException("Queue is empty");
            }
            else if (size == 1) {
                size--;
                Node tempfirst = first;
                first = last = null;
                return (Item)tempfirst.slot;
            }
            else {
                Node secondlast = last.prev;
                secondlast.next = null;
                last.prev=null;
                Node templast = last;
                last = secondlast;
                size--;
                return (Item)templast.slot;
            }                 // remove and return the item from the end
        }
        public Iterator<Item> iterator() {
            return new DequeIterator<>();
        }
            // return an iterator over items in order from front to end
        public static void main(String[] args){
                // unit testing
            }
    }

