import java.util.concurrent.*;
import java.util.Iterator;
import java.util.Hashtable;

//读线程
//The reader thread iterates over each key-value pair in the map and prints it out.
public class ReaderThread extends Thread{
	private ConcurrentHashMap<Integer, String> map;
	private String name;

	public ReaderThread(ConcurrentHashMap<Integer, String> map,
			String threadName) {
		this.map = map;
		this.name = threadName;
	}
	//继承Thread类必须重写run()方法
	public void run(){
		while (true) {
			ConcurrentHashMap.KeySetView<Integer, String> keySetView = map.keySet();
			Iterator<Integer> iterator = keySetView.iterator();

			long time = System.currentTimeMillis();
			String output = time + ": " + name + ": ";

			while (iterator.hasNext()) {
				Integer key = iterator.next();
				String value = map.get(key);
				output += key + "=>" + value + ": ";
			}
			System.out.println(output);

			try {
				Thread.sleep(200);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
