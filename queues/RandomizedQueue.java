import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] s;
	private int size = 0;
	private int capacityAtBegin = 1;

	public static void main(String[] args){ System.out.println("Ok");}

	// construct an empty randomized queue
	public RandomizedQueue()
	{
		s = (Item[]) new Object[capacityAtBegin]; 
	}

	// is the randomized queue empty?                 
	public boolean isEmpty()
	{
		return size == 0;
	} 

	// return the number of items on the randomized queue
	public int size()
	{
		return size;
	}

	// add the item
	public void enqueue(Item item)
	{
		if (item == null) { throw new IllegalArgumentException("Item must not be null."); }
		s[size++] = item;
		if(size == s.length) resize(s.length*2);
	}

	// remove and return a random item
	public Item dequeue()
	{
		if(isEmpty()) { throw new NoSuchElementException("RandomizedQueue is empty"); }
		int index = StdRandom.uniform(size);
		Item item = s[index];
		s[index] = s[size-1];
		s[size-1] = null;
		size--;
		if(size > 0 && size <= s.length/4) resize(s.length/2);
		return item;
	}

	// return a random item (but do not remove it)
	public Item sample()
	{
		if(isEmpty()) { throw new NoSuchElementException("RandomizedQueue is empty"); }
		int index = StdRandom.uniform(size);
		size--;
		return s[index];
	}                    

	// return an independent iterator over items in random order
	public Iterator<Item> iterator()         
	{
		return new RandomizedQueueIterator();
	}
	private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] copy;
        private int current;

        public RandomizedQueueIterator() {
            copyQueue();
            StdRandom.shuffle(copy);
        }

        private void copyQueue() {
            copy = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                copy[i] = s[i];
            }
        }

        public boolean hasNext() {
            return current < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy[current++];
        }
    }

	private void resize(int capacity)
	{
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++)
			copy[i] = s[i];
		s = copy;
	}
}