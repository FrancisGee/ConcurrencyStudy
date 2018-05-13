//简单易懂的死锁例子
//synchronized关键字修饰代码块，指定加锁对象，对给定对象加锁
//进入同步代码块前要获得给定对象的锁



//参考知乎上的解释: https://zhuanlan.zhihu.com/p/26945588
//死锁的出现，必须满足以下四个条件:
//1.互斥条件：指进程对所分配到的资源进行排它性使用，即在一段时间内某资源只由一个进程占用。
//如果此时还有其它进程请求资源，则请求者只能等待，直至占有资源的进程用毕释放。(只有一副钥匙)
//2.请求和保持条件：指进程已经保持至少一个资源，但又提出了新的资源请求，而该资源已被其它进程占有，
//此时请求进程阻塞，但又对自己已获得的其它资源保持不放。(拿着红钥匙的人在没有归还红钥匙的情况下，又提出要蓝钥匙)
//3.不剥夺条件： 指进程已获得的资源，在未使用完之前，不能被剥夺，只能在使用完时由自己释放。
// (人除非归还了钥匙，不然一直占用着钥匙)
//4.环路等待条件：指在发生死锁时，必然存在一个进程——资源的环形链，即进程集合{P0，P1，P2，···，Pn}中的P0正在等待一个P1占用的资源；
//P1正在等待P2占用的资源，……，Pn正在等待已被P0占用的资源。
//(拿着红钥匙的人在等蓝钥匙，同时那个拿着蓝钥匙的人在等红钥匙)

//要避免出现死锁的问题，只需要破坏四个条件中的任何一个就可以了。
//1.破话互斥条件: 给每个线程单独拷贝一份钥匙的副本
//2.破坏请求和保持条件: 一个线程获取一把钥匙之后，必须归还了钥匙之后才能请求另一把钥匙.
//3.破坏不剥夺条件: 设置一个最长占用时间,如果过了10分钟仍然没有进入下一个步骤，则归还已有的钥匙。
//4.破坏环路等待条件: 强制规定先取蓝钥匙，再取红钥匙
public class TestDeadLock {
	   public static Object Lock1 = new Object();
	   public static Object Lock2 = new Object();

	   public static void main(String args[]) {
	      ThreadDemo1 T1 = new ThreadDemo1();
	      ThreadDemo2 T2 = new ThreadDemo2();
	      T1.start();
	      T2.start();
	   }

	   private static class ThreadDemo1 extends Thread {
	      public void run() {
	         synchronized (Lock1) {
	            System.out.println("Thread 1: Holding lock 1...");
//
	            try { Thread.sleep(5); }
	            catch (InterruptedException e) {}
	            System.out.println("Thread 1: Waiting for lock 2...");
//	            try {
//					Lock1.wait(20);
//				} catch (InterruptedException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}

	            synchronized (Lock2) {
	               System.out.println("Thread 1: Holding lock 1 & 2...");
	            }
	         }
	      }
	   }
	   private static class ThreadDemo2 extends Thread {
	      public void run() {
	         synchronized (Lock2) {
	            System.out.println("Thread 2: Holding lock 2...");

	            try { Thread.sleep(20); }
	            catch (InterruptedException e) {}
	            System.out.println("Thread 2: Waiting for lock 1...");
//	            try {
//					Lock2.wait(20);
//				} catch (InterruptedException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}

	            synchronized (Lock1) {
	               System.out.println("Thread 2: Holding lock 1 & 2...");
	            }
	         }
	      }
	   }
}
