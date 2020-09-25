package com.dlw.demo.service.impl;

import com.dlw.demo.constant.Constant;
import com.dlw.demo.service.ChangeLeaderService;
import org.apache.coyote.http2.ByteUtil;
import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Description 服务主从切换触发业务代码
 * @Author diaoliwei
 * @Date 2019/6/10 9:41
 */
@Service
public class ChangeLeaderServiceImpl implements ChangeLeaderService {

    private final static Logger log = LoggerFactory.getLogger(ChangeLeaderServiceImpl.class);
;
    @Autowired
    private SSDB ssdb;

    @Override
    public void taskExecut() {
        //TODO 从ssdb 中查询出定时任务的标识，是否正常执行完成，未完成的话，在这里再触发执行。
        log.info("===ChangeLeaderServiceImpl===taskExecut()===");
        /*Response response = ssdb.get(Constant.TEST_STATE);
        if (response.ok() && response.datas.size() > 0) {
            int tenantState = byteArrayToInt(response.datas.get(0));
            if (Constant.TASK_EXECUTING == tenantState) { //当前任务未完成，接着完成
                // service.do(); 伪代码
                log.info("service.do();");
                ssdb.set(Constant.TEST_STATE, Constant.TASK_END);
            }
        }*/
    }

    /**
     * byte数组转int
     * @param b
     * @return
     */
    private int byteArrayToInt(byte[] b){
        String str = byteArrayToString(b);
        return StringToInt(str);
    }

    /**
     * byte数组转string
     * @param b
     * @return
     */
    private String byteArrayToString(byte[] b) {
        if (null == b || b.length == 0) {
            return "";
        }
        return new String(b);
    }

    /**
     * string转int
     * @param str
     * @return
     */
    private int StringToInt(String str){
        if (StringUtils.isEmpty(str)){
            return 0;
        }
        return Integer.parseInt(str);
    }
}
