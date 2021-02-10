package com.wave.benchmark;

import com.wave.expr.AbstractExpr;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liqiu.qlq
 */
@Slf4j
@Data
public abstract class AbstractRunner implements Runnable {
    /**
     * 运行次数
     */
    private Integer runTimes;

    /**
     * 执行计划
     */
    private ComputeNode rootNode;

    private long cost = System.currentTimeMillis();

    public void start() {
        new Thread(this).start();
    }


    /**
     * 内部逻辑
     * @return
     */
    public abstract boolean runInner();

    public void run() {
        try {
            cost = System.currentTimeMillis();
            for (int i = 0; i < runTimes; i++) {
                runInner();
            }
            cost = System.currentTimeMillis() - cost;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
