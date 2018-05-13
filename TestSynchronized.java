//三种情况的Synchronized
//1.修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁
//2.修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
//3.修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。

//参考: https://blog.csdn.net/javazejian/article/details/72828483
public class TestSynchronized implements Runnable {

	//Synchronized关键字修饰代码块，给指定对象加锁
	static TestSynchronized instance = new TestSynchronized();

    //共享资源(临界资源)
    static int i=0;

    /**
     * synchronized 修饰实例方法， 给实例加锁
     */
//    public synchronized void increase(){
//        i++;
//    }


    /**
     * synchronized 修饰静态方法,给类对象加锁
     */
    public static synchronized void increase(){
        i++;
    }



//	@Override
//	public void run() {
//		// TODO 自动生成的方法存根
//        for(int j=0;j<1000000;j++){
//            increase();
//        }
//
//	}

    ////Synchronized关键字修饰代码块，给指定对象加锁
	@Override
	public void run() {
		//省略其他耗时操作...
		//使用同步代码块对变量i进行同步操作,锁对象为instance
		// TODO 自动生成的方法存根
		synchronized (instance) {
	        for(int j=0;j<1000000;j++){
	            increase();
	        }
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		//给实例instance对象加锁, Synchronized关键字用于实例方法
//		TestSynchronized instance = new TestSynchronized();
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		//main线程等待t1完成
		t1.join();
		//main线程等待t2完成
		t2.join();

		System.out.println(i);

	}


}
