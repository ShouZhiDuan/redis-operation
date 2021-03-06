package lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @Author: qingshan
 * @Date: 2019/9/27 17:46
 * @Description: 咕泡学院，只为更好的你
 */
public class LockTest {
    private static RedissonClient redissonClient;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.10.21:6379");
        redissonClient= Redisson.create(config);
    }

    public static void main(String[] args) throws InterruptedException {
        RLock rLock = redissonClient.getLock("updateAccount");
        //100：获锁超时时长
        //10：锁的有效时长
        if(rLock.tryLock(100,10, TimeUnit.SECONDS)){
            System.out.println("获取锁成功");
        }
        //Thread.sleep(20000);
        rLock.unlock();
        redissonClient.shutdown();
    }
}
