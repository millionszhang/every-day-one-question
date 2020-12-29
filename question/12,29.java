/*我们提供了一个类：

public class Foo {
  public void one() { print("one"); }
  public void two() { print("two"); }
  public void three() { print("three"); }
}
三个不同的线程将会共用一个 Foo 实例。

线程 A 将会调用 one() 方法
线程 B 将会调用 two() 方法
线程 C 将会调用 three() 方法
请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。

 

示例 1:

输入: [1,2,3]
输出: "onetwothree"
解释: 
有三个线程会被异步启动。
输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
正确的输出是 "onetwothree"。
示例 2:

输入: [1,3,2]
输出: "onetwothree"
解释: 
输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
正确的输出是 "onetwothree"。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/print-in-order
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/

package LeetCode;
import java.util.concurrent.Semaphore;
class Foo {
    public Semaphore seam_first_two = new Semaphore(0);
    
    public Semaphore seam_two_second = new Semaphore(0);
    
    public Foo() {
        
    }

    public void first(final Runnable printFirst) throws InterruptedException {
        printFirst.run();
        seam_first_two.release();
    }

    public void second(final Runnable printSecond) throws InterruptedException {
        seam_first_two.acquire();
        printSecond.run();
        seam_two_second.release();
    }

    public void third(final Runnable printThird) throws InterruptedException {
        seam_two_second.acquire();
        printThird.run();
    }
}