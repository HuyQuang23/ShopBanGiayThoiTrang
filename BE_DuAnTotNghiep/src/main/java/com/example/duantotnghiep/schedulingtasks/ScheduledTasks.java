package com.example.duantotnghiep.schedulingtasks;


import com.example.duantotnghiep.service.giam_gia_service.impl.GiamGiaServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// ScheduledTasks.java

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final GiamGiaServiceimpl giamGiaService;

    @Autowired
    public ScheduledTasks(GiamGiaServiceimpl giamGiaService) {
        this.giamGiaService = giamGiaService;
    }
//sau 8h chaycron = "0 0 8 * * ?"  sau 2s chay fixedRate = 2000
    @Scheduled(cron = "0 0 8 * * ?")
    public void checkAndSetStatus() {
        log.info("Running scheduled task to check and update GiamGia status");
        giamGiaService.checkAndSetStatus();
    }
}
