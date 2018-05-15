//Oracle的例子中讲述按照以下规则, 将SynchronizedRGB类变成不可变类

//1.Don't provide "setter" methods — methods that modify fields or objects referred to by fields.
//2.Make all fields final and private.
//3.Don't allow subclasses to override methods.
//The simplest way to do this is to declare the class as final.
//A more sophisticated approach is to make the constructor private and construct instances in factory methods.
//4.If the instance fields include references to mutable objects, don't allow those objects to be changed:
//A>Don't provide methods that modify the mutable objects.
//B>Don't share references to the mutable objects.
//Never store references to external, mutable objects passed to the constructor;
//if necessary, create copies, and store references to the copies.
//Similarly, create copies of your internal mutable objects when necessary to avoid returning the originals in your methods.
public class SynchronizedRGB {
    // Values must be between 0 and 255.
    private int red;
    private int green;
    private int blue;
    private String name;

    private void check(int red,
                       int green,
                       int blue) {
        if (red < 0 || red > 255
            || green < 0 || green > 255
            || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public SynchronizedRGB(int red,
                           int green,
                           int blue,
                           String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public void set(int red,
                    int green,
                    int blue,
                    String name) {
        check(red, green, blue);
        synchronized (this) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }
    }

    public synchronized int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void invert() {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
        name = "Inverse of " + name;
    }
}
