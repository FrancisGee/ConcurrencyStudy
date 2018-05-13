import java.util.concurrent.*;
import java.util.Random;


//写线程
//The writer thread randomly modifies the map (put and remove).
public class WriterThread extends Thread {
	private ConcurrentHashMap<Integer, String> map;
	private Random random;
	private String name;

	public WriterThread(ConcurrentHashMap<Integer, String> map,
			String threadName,long randomSeed){
		this.map = map;
		this.random = new Random(randomSeed);
		this.name = threadName;
	}

	/* （非 Javadoc）
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		while(true) {
			Integer key = random.nextInt(10);
			String value = name;

			//写操作
			if (map.putIfAbsent(key, value) == null) {
				long time = System.currentTimeMillis();
				String output = String.format("%d: %s has put [%d => %s]",
						time, name, key, value);
				System.out.println(output);
			}

			Integer keyToRemove = random.nextInt(10);

			//写操作
			if (map.remove(keyToRemove, value)){
				long time = System.currentTimeMillis();
				String output = String.format("%d: %s has removed [%d => %s]",
						time, name, keyToRemove, value);
				System.out.println(output);
			}


			//sleep相当于让线程睡眠，交出CPU，让CPU去执行其他的任务。
			//sleep方法不会释放锁，
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}



	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
