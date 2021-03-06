package demo1;

public class App {

	private int count = 0 ;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		app.doWork();
	}
	
	//This function is needed because two thread want to modify a value at the same time.
	//Sometimes the value is modified at the same time of each thread, making some skips in the modification. 
	public synchronized void increment() {
		count++;
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					//count++; 
					increment();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					//count++;
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		//Wait until each tread finish its execution.
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Count is: "+count);
	}

}
