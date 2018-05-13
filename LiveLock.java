//活锁的例子
//参考: http://www.codejava.net/java-core/concurrency/understanding-deadlock-livelock-and-starvation-with-code-examples-in-java
//Livelock describes situation where two threads are busy responding to actions of each other.
//罪犯要酬金施法人质，警察要得到人质后给酬金
//a criminal kidnaps a hostage and he asks for ransom in order to release the hostage.
//A police agrees to give the criminal the money he wants once the hostage is released. The criminal releases the hostage only when he gets the money.
//Both are waiting for each other to act first, hence livelock.
public class LiveLock {

	static class Criminal {
	    private boolean hostageReleased = false;

	    public void releaseHostage(Police police) {
	        while (!police.isMoneySent()) {

	            System.out.println("Criminal: waiting police to give ransom");

//	            try {
//	                Thread.sleep(1000);
//	            } catch (InterruptedException ex) {
//	                ex.printStackTrace();
//	            }
	        }

	        System.out.println("Criminal: released hostage");

	        this.hostageReleased = true;
	    }

	    public boolean isHostageReleased() {
	        return this.hostageReleased;
	    }
	}


	static class Police {
	    private boolean moneySent = false;

	    public void giveRansom(Criminal criminal) {

	        while (!criminal.isHostageReleased()) {

	            System.out.println("Police: waiting criminal to release hostage");

//	            try {
//	                Thread.sleep(1000);
//	            } catch (InterruptedException ex) {
//	                ex.printStackTrace();
//	            }
	        }

	        System.out.println("Police: sent money");

	        this.moneySent = true;
	    }

	    public boolean isMoneySent() {
	        return this.moneySent;
	    }
	}

	public static void main(String[] args) {
		 Police police = new Police();
		 Criminal criminal = new Criminal();

	        Thread t1 = new Thread(new Runnable() {
	            public void run() {
	                police.giveRansom(criminal);
	            }
	        });
	        t1.start();

	        Thread t2 = new Thread(new Runnable() {
	            public void run() {
	                criminal.releaseHostage(police);
	            }
	        });
	        t2.start();
	}

}
