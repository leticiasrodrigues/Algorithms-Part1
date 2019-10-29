public class Permutation {
	public static void main(String[] args)
	{
	RandomizedQueue<String> rq = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			rq.enqueue(StdIn.readString());
		}
		int k = Integer.parseInt(args[0]);
		for(String s : rq){
			if (k-- <= 0){
				break;
			}
			StdOut.println(s);
		}
	}
}