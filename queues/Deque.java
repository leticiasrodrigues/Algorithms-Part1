import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	public static void main(String[] args){}

	private Node first = null;
	private Node last = null;
	private int size = 0;

	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;
		public boolean hasNext() { return current != null; }
		public void remove() { throw new UnsupportedOperationException("Method not supported"); }
		public Item next()
		{
			if(!hasNext()) { throw new NoSuchElementException("Deque is empty");}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	private class Node
	{
		Item item;
		Node next;
		Node last;
	}

	// construct an empty deque
	public Deque()                           
	{

	}
   
	// is the deque empty?
	public boolean isEmpty()                 
	{
		return size == 0;
	}
   
	// return the number of items on the deque
	public int size()
	{
		return size;
	}

	// add the item to the front
	public void addFirst(Item item)
	{
		if(item == null) { throw new IllegalArgumentException("Item must not be null.");}
		if(size == 0)
		{
			addFistItem(item);
			return;
		}
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		oldFirst.last = first;
		size++;
	}

	// add the item to the end
	public void addLast(Item item) 
	{
		if(item == null) { throw new IllegalArgumentException("Item must not be null.");}
		if(size == 0)
		{
			addFistItem(item);
			return;
		}
		Node newLast = new Node();
		newLast.item = item;
		last.next = newLast;
		newLast.last = last;
		last = newLast;
		size++;
	}

	// remove and return the item from the front
	public Item removeFirst()
	{
		if(size == 0) { throw new NoSuchElementException("Deque is empty"); }
		Node newFirst = first.next;
		first.next = null;
		newFirst.last = null;
		first = newFirst;
		size--;
		return first.item;
	}

	// remove and return the item from the end
	public Item removeLast()
	{
		if(size == 0) { throw new NoSuchElementException("Deque is empty"); }
		Node newLast = last.last;
		newLast.next = null;
		last.last = null;
		last = newLast;
		size--;
		return last.item;
	}               

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}        

	private void addFistItem(Item item)
	{
		Node node = new Node();
		node.item = item;
		first = node;
		last = node;
		size++;
	}
}