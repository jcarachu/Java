public class LinkedQueueMain {
	public static void main(String args [])
	{
		LinkedQueue<String> linkedqueue = new LinkedQueue<String>();
		
		linkedqueue.enqueue("A");
		linkedqueue.enqueue("B");
		linkedqueue.enqueue("C");
		linkedqueue.enqueue("D");
		
		while(!linkedqueue.isEmpty())
		{
			System.out.println("Element: " + linkedqueue.dequeue() + "Size: " + linkedqueue.size());
		}
	}	
}
