package com.viewhigh.inspection.scheduled;
/**
 * @author zhangS
 * @Description 
 * @date 2019-12-17 18:16
 */
public abstract class  IScheduledWork {
    /**
     *添加 新的业务 若是也用到类似的定时任务只需继承 此类即可
     */
    public IScheduledWork(){
        ScheduledWorkList.register(this);
    }
    abstract void scheduledWork_month(String se);
    abstract void scheduledWork_quarter(String se);
    abstract void scheduledWork_halfYear(String se);
    abstract void scheduledWork_year(String se);
}
