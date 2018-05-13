/*****************
 * @
 *
 * 学习ConcurrentHashMap的基本使用
 * 生成2个写线程和5个读线程，共享同一个ConcurrentHashMap
 *
 */
import java.util.concurrent.*;
public class ConcurrentHashMapExamples {

	public static void main(String[] args) {
		ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

		 WriterThread w1 = new WriterThread(map, "Writer-1", 1);
		 System.out.println(w1.currentThread());
/*		new WriterThread(map, "Writer-2", 2).start();

		for (int i = 1; i <= 5; i++) {
			new ReaderThread(map, "Reader-" + i).start();
		}*/

	}

}
