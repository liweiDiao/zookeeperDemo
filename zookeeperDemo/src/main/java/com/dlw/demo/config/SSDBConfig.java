package com.dlw.demo.config;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.nutz.ssdb4j.SSDBs;
import org.nutz.ssdb4j.spi.SSDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description ssdb
 * @Author diaoliwei
 * @Date 2019/9/24 14:16
 */
@Configuration
public class SSDBConfig {

    private final static Logger log = LoggerFactory.getLogger(SSDBConfig.class);

    @Value("${spring.ssdb.master.host}")
    private String masterHost;

    @Value("${spring.ssdb.master.port}")
    private int masterPort;

    @Value("${spring.ssdb.slave.host}")
    private String slaveHost;

    @Value("${spring.ssdb.slave.port}")
    private int slavePort;

    @Value("${spring.ssdb.timeout}")
    private int timeout;

    @Value("${spring.ssdb.pool.maxIdle}")
    private int maxIdle;

    @Value("${spring.ssdb.pool.minIdle}")
    private int minIdle;

    @Value("${spring.ssdb.pool.maxActive}")
    private int maxActive;

    @Value("${spring.ssdb.pool.maxWait}")
    private int maxWait;

    @Value("${spring.ssdb.pool.whenExhaustedAction}")
    private byte whenExhaustedAction;

    @Value("${spring.ssdb.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.ssdb.pool.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.ssdb.pool.testWhileIdle}")
    private boolean testWhileIdle;

    @Bean
    public SSDB SSDBFactory() {
        SSDB ssdb = null;
        try {
            log.info("==========SSDB master connecting   {}:{}", masterHost, masterPort);
            log.info("==========SSDB slave connecting  {}:{}", slaveHost, slavePort);
            ssdb = SSDBs.replication(masterHost, masterPort, slaveHost, slavePort, timeout, getConfig());
        } catch (Exception e) {
            log.error("====SSDBConfig===SSDBFactory()===SSDB Connection Exception====={}", e);
            new RuntimeException("SSDB Connection Exception");
        }
        log.info("========== SSDB Master Slave connectioned successful !========");
        return ssdb;
    }

    private GenericObjectPool.Config getConfig() {
        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.maxIdle = maxIdle;
        config.minIdle = minIdle;
        config.maxActive = maxActive;
        config.maxWait = maxWait;
        config.whenExhaustedAction = whenExhaustedAction;
        return config;
    }
}
