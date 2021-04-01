package org.zzy.lib.skynet.interceptor.handler;

import org.zzy.lib.skynet.interceptor.CheckRequest;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2021/3/18 18:40
 * 描    述：
 * 修订历史：
 * ================================================
 */
public abstract class SecurityCheckHandler {

    private SecurityCheckHandler next;

    public SecurityCheckHandler setNext(SecurityCheckHandler next) {
        this.next = next;
        return this;
    }

    public SecurityCheckHandler getNext() {
        return next;
    }

    public void handle(CheckRequest request){
        if(isOpen(request)){
            check(request);
        }else if(next!=null){
            next.check(request);
        }
    }

    protected abstract boolean isOpen(CheckRequest request);

    protected abstract void check(CheckRequest request);
}
