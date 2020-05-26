import java.util.concurrent.*;
import java.util.stream.*;
import java.util.function.*;

public class ForkJoinSum {
	public static void main(String[] args) {
		System.out.println("ForkJoin sum done in :" + measureSumPref(ForkJoinSumCalculator::ForkJoinSum,10_001));
	}

	public static long measureSumPref(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Result: " + sum);
			if (duration < fastest) fastest = duration;
		}
		
		return fastest;
	}
	
	static class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {
		private final long[] numbers;
		private final int start;
		private final int end;
		
		public static final long THRESHOLD = 10_000;
		
		public ForkJoinSumCalculator(long[] numbers){
			this(numbers, 0, numbers.length);
		}
		
		public ForkJoinSumCalculator(long[] numbers, int start, int end) {
			this.numbers = numbers;
			this.start = start;
			this.end = end;
		}
		
		public static long ForkJoinSum(long n) {
				long [] numbers = LongStream.rangeClosed(1, n).toArray();
				ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
				return new ForkJoinPool().invoke(task);
		}
		
		private long computeSequentially() {
			long sum = 0;
			for (int i = start; i < end; i++ ) {
				sum += numbers[i];
			}
			return sum;
		}
		
		/*
		 * Computes task sequentially if the task is small enough or no longer divisible.
		 * else splits task in two subtask, calls this method recursively possibly further splitting each subtask.
		 * Waits for the completion of all subtasks and returns the combine result of each subtask
		 * 
		 * @param
		 * @return computed task or subtask
		 */
		@Override
		protected Long compute(){
			int length = end - start;
			if (length <= THRESHOLD) {
				return computeSequentially();
			}
			ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
			
			//Asynchronously execute the newly created subtask using another thread of the ForkJoinPool
			leftTask.fork();
			ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
			Long rightResult = rightTask.compute();
			Long leftResult = leftTask.join();
			return leftResult + rightResult;
		}
	}
}