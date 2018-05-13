import java.util.concurrent.*;
import java.util.Random;


//д�߳�
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

	/* ���� Javadoc��
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		while(true) {
			Integer key = random.nextInt(10);
			String value = name;

			//д����
			if (map.putIfAbsent(key, value) == null) {
				long time = System.currentTimeMillis();
				String output = String.format("%d: %s has put [%d => %s]",
						time, name, key, value);
				System.out.println(output);
			}

			Integer keyToRemove = random.nextInt(10);

			//д����
			if (map.remove(keyToRemove, value)){
				long time = System.currentTimeMillis();
				String output = String.format("%d: %s has removed [%d => %s]",
						time, name, keyToRemove, value);
				System.out.println(output);
			}


			//sleep�൱�����߳�˯�ߣ�����CPU����CPUȥִ������������
			//sleep���������ͷ�����
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}



	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

	}

}
