package com.viewhigh.inspection.scheduled;

import com.viewhigh.inspection.bean.ScheduledEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangS
 * @Description 
 * @date 2019-12-17 17:50
 */
@Component
@Slf4j
public class ScheduledWorkList {
   static Map<String,IScheduledWork> map=new HashMap<>();

    public static void register(IScheduledWork scheduledWork){
        map.put(scheduledWork.getClass().getSimpleName(),scheduledWork);
    }
    public void exc(ScheduledEnum se){
         Set<String> keyInfo = map.keySet();
         for(String str:keyInfo){
             log.info(str+"##############");
             IScheduledWork scheduledWork = map.get(str);
             switch (se) {
                 case MONTH:
                     scheduledWork.scheduledWork_month(se.getName());
                     break;
                 case QUARTER:
                     scheduledWork.scheduledWork_quarter(se.getName());
                     break;
                 case HALFYEAR:
                     scheduledWork.scheduledWork_halfYear(se.getName());
                     break;
                 case YEAR:
                     scheduledWork.scheduledWork_year(se.getName());
                     break;
                 default:
             }
         }
    }
}
