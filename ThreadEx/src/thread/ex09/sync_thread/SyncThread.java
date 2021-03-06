package thread.ex09.sync_thread;

class Anum {
	int num = 0;
	
	synchronized void accumulate (int val) {
		num += val;
	}
}

class Sum implements Runnable {
	Anum aNum;
	int start, end;
	
	Sum(Anum aNum, int start, int end) {
		this.aNum = aNum;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		for (int i = start; i <= end; i++) 
			aNum.accumulate(i);
	}
}

public class SyncThread {

	public static void main(String[] args) throws InterruptedException {
		Anum aNum = new Anum();
		Sum sum0 = new Sum(aNum, 1, 50);
		Sum sum1 = new Sum(aNum, 51, 100);
		Thread t0 = new Thread(sum0);
		Thread t1 = new Thread(sum1);
		
		t0.start();
		t1.start();
		
		t0.join();
		t1.join();
		
		
		System.out.println("1 ~ 100 accum = " + aNum.num);
	}

}
