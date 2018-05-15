//Oracle文档上死锁的例子
//逻辑解释:
//这是synchronizd关键字修饰实例方法，用于给当前实例加锁, 进入同步块前
//要获得当前实例的锁
//线程1给gaston加锁，等着进入alphones的锁,
//线程2给alphones加锁, 等着进入gaston的锁, 最后发生了死锁
public class Deadlock {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                + "  has bowed to me!%n",
                this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                + " has bowed back to me!%n",
                this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse =
            new Friend("Alphonse");
        final Friend gaston =
            new Friend("Gaston");
        //线程1
        new Thread(new Runnable() {
            public void run() { alphonse.bow(gaston); }
        }).start();
        //线程2
        new Thread(new Runnable() {
            public void run() { gaston.bow(alphonse); }
        }).start();
    }
}
