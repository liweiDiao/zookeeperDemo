package com.dlw.demo.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description google guava 本地缓存
 * @Author diaoliwei
 * @Date 2021/1/5 18:15
 */
public class GuavaCacheUtil {

    private static final Logger log = LoggerFactory.getLogger(GuavaCacheUtil.class);

    /**
     * 缓存项最大数量
     */
    private static final long GUAVA_CACHE_SIZE = 100000;

    /**
     * 缓存时间：秒
     */
    private static final long GUAVA_CACHE_DAY = 60;

    /**
     * 缓存操作对象
     */
    private static LoadingCache<String, String> GUAVA_CACHE = null;


    static {
        try {
            GUAVA_CACHE = loadCache(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    // 处理缓存键不存在缓存值时的处理逻辑
                    return "";
                }
            });
        } catch (Exception e) {
            log.error("======GuavaCacheUtil=======init Guava Cache error:", e);
        }
    }

    /**
     * 全局缓存设置
     * @param cacheLoader
     * @return
     * @throws Exception
     */
    private static LoadingCache<String, String> loadCache(CacheLoader<String, String> cacheLoader) throws Exception {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                //缓存池大小，在缓存项接近该大小时， Guava开始回收旧的缓存项
                .maximumSize(GUAVA_CACHE_SIZE)
                //设置时间对象没有被读/写访问则对象从内存中删除(在另外的线程里面不定期维护)
                .expireAfterAccess(GUAVA_CACHE_DAY, TimeUnit.SECONDS)
                // 设置缓存在写入之后 设定时间 后失效
                .expireAfterWrite(GUAVA_CACHE_DAY, TimeUnit.SECONDS)
                //移除监听器,缓存项被移除时会触发
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> rn) {
                        //逻辑操作
                    }
                })
                //开启Guava Cache的统计功能
                .recordStats()
                .build(cacheLoader);
        return cache;
    }

    /**
     * 设置缓存值
     * @param key
     * @param value
     */
    public static void put(String key, String value) {
        try {
            GUAVA_CACHE.put(key, value);
        } catch (Exception e) {
            log.error("====GuavaCacheUtil=====put()==key:{}, value:{}==put cache error:", key, value, e);
        }
    }

    /**
     * 批量设置缓存值
     * @param map
     */
    public static void putAll(Map<? extends String, ? extends String> map) {
        try {
            GUAVA_CACHE.putAll(map);
        } catch (Exception e) {
            log.error("====GuavaCacheUtil=====putAll()=====put all by keys cache error", e);
        }
    }

    /**
     * 获取缓存值
     * @param key
     * @return
     */
    public static String get(String key) {
        String token = "";
        try {
            token = GUAVA_CACHE.get(key);
        } catch (Exception e) {
            log.error("====GuavaCacheUtil=====get()======key:{}====get cache error:", key, e);
            return "";
        }
        return token;
    }

    /**
     * 移除缓存
     * @param key
     */
    public static void remove(String key) {
        try {
            GUAVA_CACHE.invalidate(key);
        } catch (Exception e) {
            log.error("======GuavaCacheUtil======remove()=====key:{}===remove cache error:", key, e);
        }
    }

    /**
     * 批量移除缓存
     * @param keys
     */
    public static void removeAll(Iterable<String> keys) {
        try {
            GUAVA_CACHE.invalidateAll(keys);
        } catch (Exception e) {
            log.error("=======GuavaCacheUtil======removeAll()===remove all by keys cache error:", e);
        }
    }

    /**
     * 清空所有缓存
     */
    public static void removeAll() {
        try {
            GUAVA_CACHE.invalidateAll();
        } catch (Exception e) {
            log.error("========GuavaCacheUtil======removeAll()==error:", e);
        }
    }

    /**
     * 获取缓存数量
     * @return
     */
    public static long size() {
        long size = 0;
        try {
            size = GUAVA_CACHE.size();
        } catch (Exception e) {
            log.error("==============GuavaCacheUtil=================size()=====error:", e);
        }
        return size;
    }

    /**
     *
     *
     *
     *
     *     1. 移除机制
     * guava做cache时候数据的移除分为被动移除和主动移除两种。
     *
     * 被动移除分为三种：
     *
     * 基于大小的移除：数量达到指定大小，会把不常用的键值移除
     *
     * 基于时间的移除：expireAfterAccess(long, TimeUnit) 根据某个键值对最后一次访问之后多少时间后移除
     * 　　　　　　　　expireAfterWrite(long, TimeUnit) 根据某个键值对被创建或值被替换后多少时间移除
     *
     * 基于引用的移除：主要是基于java的垃圾回收机制，根据键或者值的引用关系决定移除
     *
     * 主动移除分为三种：1).单独移除：Cache.invalidate(key)
     *
     * 　　　　　　　　　2).批量移除：Cache.invalidateAll(keys)
     *
     * 　　　　　　　　　3).移除所有：Cache.invalidateAll()
     *
     * 如果配置了移除监听器RemovalListener，则在所有移除的动作时会同步执行该listener下的逻辑。
     *
     * 如需改成异步，使用：RemovalListeners.asynchronous(RemovalListener, Executor)
     *
     * 2. 遇到的问题
     * 在put操作之前，如果已经有该键值，会先触发removalListener移除监听器，再添加
     * 配置了expireAfterAccess和expireAfterWrite，但在指定时间后没有被移除。
     *
     * 解决方案：CacheBuilder构建的缓存不会在特定时间自动执行清理和回收工作，也不会在某个缓存项过期后马上清理，它不会启动一个线程来进行缓存维护，因为a）线程相对较重，b）某些环境限制线程的创建。它会在写操作时顺带做少量的维护工作，或者偶尔在读操作时做。当然，也可以创建自己的维护线程，以固定的时间间隔调用Cache.cleanUp()。
     *
     *
     *
     */

}
