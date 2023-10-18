package com.llmcu.tuling.l1103.constant;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/10 11:18
 */
public enum Action {
    // 处理成功
    ACCEPT,
    // 可以重试的错误
    RETRY,
    // 无需重试的错误
    REJECT;
}
