import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author: zhun.huang
 * @create: 2018-03-09 下午1:50
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "PX";

    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        String result = set(jedis, lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        return LOCK_SUCCESS.equals(result);
    }

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        /**
         * 通过传入lua脚本, 保证解锁姿势是原子的
         */
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    /**
     * 统一set接口
     * @param jedis redis客户端单例
     * @param key
     * @param value
     * @param nxxx  NX|XX<br/>
     *              NX -- 仅当key不存在时，设置<br/>
     *              XX -- 仅当key存在时，设置
     * @param expx  EX|PX<br/>
     *              过期时间单位: EX = 秒（seconds）; PX = 毫秒(milliseconds)
     * @param time
     * @return Status code reply
     */
    public static String set(Jedis jedis, String key, String value, String nxxx, String expx, long time) {
        String ok = "";
        if (jedis != null) {
            ok = jedis.set(key, value, nxxx, expx, time);
        }
        return ok;
    }
}
