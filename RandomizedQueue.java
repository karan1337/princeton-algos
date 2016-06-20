import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Created by Karan on 6/19/16.
 */

    public class RandomizedQueue<Item> implements Iterable<Item> {


    private class RandomizedIterator<Item> implements Iterator<Item> {

        private int currentIndex = 0;
        private int[] randomIndexList = new int[size];

        @Override
        public boolean hasNext() {
            if (currentIndex == 0) {

                for(int i=0 ;i<size; i++){
                    randomIndexList[i] = i;
                }
                StdRandom.shuffle(randomIndexList);
            }

            return (currentIndex < size);
        }

        @Override
        public Item next() {
            if(currentIndex == 0){
                for(int i=0 ;i<size; i++){
                    randomIndexList[i] = i;
                }
                StdRandom.shuffle(randomIndexList);
            }

            if(currentIndex >= size || size == 0){
                throw new NoSuchElementException("element does not exist");
            }

            return (Item)slot[randomIndexList[currentIndex++]];

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

        private int size;
        private Item[] slot;

        public RandomizedQueue(){
            slot = (Item[])new Object[2];
            size = 0;
        }                 // construct an empty randomized queue

        public boolean isEmpty(){
            return (size == 0);
        }
            // is the queue empty?

        public int size(){
            return size;
        }

        private void resize(int capacity){
            Item[] temp = (Item[])new Object[capacity];
            //copy existing elements into the new array
            for(int i=0; i<size; i++){
                temp[i] = slot[i];
            }
            slot = temp;
        }

        /*
        private int getRandomIndexInRange(int start, int end){
            Random rn = new Random();
            int range = end - start + 1;
            int randomNum = rn.nextInt(range) + start;
            return randomNum;
        }
        */

            // return the number of items on the queue
        public void enqueue(Item item){

            if(item == null){
                throw new java.lang.NullPointerException("item is null");
            }

            if(size == slot.length){
                resize(2*slot.length);
            }

            slot[size++] = item;
        }
                // add the item

        public Item dequeue(){

            if(isEmpty()){
                throw new NoSuchElementException("queue is empty");
            }

            //int index = getRandomIndexInRange(0, size-1);
            int index = StdRandom.uniform(size);

            Item itemAtIndex = slot[index];

            /*
            for(int i=index; i<size-1; i++){
                slot[i] = slot[i+1];
            }
            */

            //move last element to the position of the dequeued one (for constant time)
            slot[index] = slot[size-1];
            slot[--size] = null;

            //shrink slot
            if(size > 0 && size == slot.length/4){
                resize(slot.length/2);
            }

            return itemAtIndex;
        }
                // remove and return a random item

        public Item sample(){
            //int index = getRandomIndexInRange(0, size-1);

            if(isEmpty()){
                throw new NoSuchElementException("queue is empty");
            }

            int index = StdRandom.uniform(size);

            Item itemAtIndex = slot[index];

            return itemAtIndex;
        }
            // return (but do not remove) a random item

        public Iterator<Item> iterator(){
            return new RandomizedIterator<>();
        }         // return an independent iterator over items in random order

        public static void main(String[] args){

            /*
            RandomizedQueue<Integer> rq = new RandomizedQueue<>();

            for(int i=0; i<100; i++){
                StdOut.println(StdRandom.uniform(10));
            }
            */

        }   // unit testing
    }

