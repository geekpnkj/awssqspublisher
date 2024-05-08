package com.tabletech.awssqspublisher;

import com.tabletech.awssqspublishe.utils.CommonUtils;
import com.tabletech.service.AwsUtilsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ComponentScan({"com.tabletech"})
@Configuration
@Slf4j
public class AwsSQSPublisherApplication {

    public static void main(String[] args) {
        int exitStatus = 1;
        SpringApplication springApplication = new SpringApplication(AwsSQSPublisherApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.addListeners(new ApplicationPidFileWriter("RUNNING_PID"));
        ConfigurableApplicationContext ctx = springApplication.run();
        try {
            Integer operationType = CommonUtils.parseIntegerFromString(args[0], 0);

            switch (operationType) {
                //app_ads_crawler 0 2 true 5 100000
                case 0 -> {
                    String jsonFilePath = args[1];
                    String queueName = args[2];
                    Date start = new Date();
                    log.debug("uploading started ......");
                    log.debug(ctx.getBean(AwsUtilsService.class).process(jsonFilePath, queueName));
                    Date end = new Date();
                    long diff = end.getTime() - start.getTime();
                    String TimeTaken = String.format("%s hours %s min %s secs", TimeUnit.MILLISECONDS.toHours(diff), TimeUnit.MILLISECONDS.toMinutes(diff), TimeUnit.MILLISECONDS.toSeconds(diff));
                    log.debug("Time taken {}", TimeTaken);

                }
                default -> {
                    System.out.println("Please pass 0 for app_flat_data and 1 for application master and 2 for reports");
                }
            }
            exitStatus = 0;
        } catch (Exception ex) {
            log.error("Error while executing cron " + ex.getMessage());
            ex.printStackTrace();
            log.error("Error while executing cron " + ex.getMessage());
        } finally {
            log.debug("**************Crawler End************");
            System.exit(exitStatus);
        }
    }

}
