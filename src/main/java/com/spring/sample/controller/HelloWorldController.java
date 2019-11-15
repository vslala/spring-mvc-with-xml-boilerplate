package com.spring.sample.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.converter.JobParametersConverter;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@ImportResource("classpath:/auditDataPurgeBatch/applicationContext.xml")
public class HelloWorldController {

    @Autowired
    ApplicationContext applicationContext;
    
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    JobLauncher jobLauncher;
    
    private JobParametersConverter jobParametersConverter = new DefaultJobParametersConverter();

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        String[] parameters = {
            "sku.workers.gridsize=" + 3, 
            "sku.worker.commit.interval=" + 250,
            "runId=" + new Date(),
            "nbr.of.days.to.keep.audit.data.for=" + 7,
            "hard.limit=" + 7
        };
    
        // TODO: Un-comment to List down all the loaded beans
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        Job auditDataPurgeJob = (Job) applicationContext.getBean("auditDataPurge");
        JobParameters jobParameters = jobParametersConverter.getJobParameters(StringUtils.splitArrayElementsIntoProperties(parameters, "="));
        jobLauncher.run(auditDataPurgeJob, jobParameters);
        return "Hellow World!";
    }
}
