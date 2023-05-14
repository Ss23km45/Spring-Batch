package com.coding.ninja.spring.batch.SpringBatch.config;

import com.coding.ninja.spring.batch.SpringBatch.entitiy.ConsumersPriceIndex;
import com.coding.ninja.spring.batch.SpringBatch.entitiy.ModelClass;
import com.coding.ninja.spring.batch.SpringBatch.processor.ConsumerModelProcess;
import com.coding.ninja.spring.batch.SpringBatch.processor.ConsumerPriceIndexProcessor;
import com.coding.ninja.spring.batch.SpringBatch.repository.ConsumersPriceIndexRepo;
import com.coding.ninja.spring.batch.SpringBatch.writer.ConsumerWriter;
import com.coding.ninja.spring.batch.SpringBatch.writer.ModelWriter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerConfig {

//    @Autowired
//    private JobBuilder jobBuilder;
//
//    @Autowired
//    private StepBuilder stepBuilder;

    @Autowired
    private ConsumersPriceIndexRepo consumersPriceIndexRepo;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ConsumerWriter consumerWriter;

    @Autowired
    private ModelWriter modelWriter;

    /*
        reader() and lineMapper() are Basic configurations to read a data from a File...
     */
    @Bean
    public FlatFileItemReader<ConsumersPriceIndex> reader(){
        FlatFileItemReader<ConsumersPriceIndex> itemReader = new FlatFileItemReader<>();

        itemReader.setResource(new FileSystemResource("src/main/resources/templates/consumers-price-index.txt"));
        itemReader.setLinesToSkip(1);
        //setLineMapper we have to set up which line it has to consider for which Column
        itemReader.setLineMapper(lineMapper());

        return itemReader;
    }

    @Bean
    public LineMapper<ConsumersPriceIndex> lineMapper() {
        DefaultLineMapper<ConsumersPriceIndex> lineMapper = new DefaultLineMapper<>();

        //We have to set the line Delimiter and column values
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("Series_reference","Period","Data_value","STATUS_status","UNITS_units","Subject_subject","Group_grp","Series_title_1","UniqueNumber");

        //We have to set the Class to which it can map values from File
        BeanWrapperFieldSetMapper<ConsumersPriceIndex> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(ConsumersPriceIndex.class);

        //Set the Both tokenizer and mapper to DefaultLine Mapper
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    /*
        Now we have to create methods to use Step component methods
        itemReader is Completed -> public FlatFileItemReader<ConsumersPriceIndex> reader()
        itemProcessor -> Now this
        itemWriter
     */
    //Temparory Change to update the data to new DB Table....
    //return new ConsumerModePprocess
    @Bean
    public ConsumerPriceIndexProcessor processor(){
        return new ConsumerPriceIndexProcessor();
    }

//    @Bean
//    public RepositoryItemWriter<ConsumersPriceIndex> writer(){
//        RepositoryItemWriter<ConsumersPriceIndex> itemWriter = new RepositoryItemWriter<>();
//        itemWriter.setRepository(consumersPriceIndexRepo);
//        itemWriter.setMethodName("save");
//
//        return itemWriter;
//    }



    @Bean
    public Step step(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.afterPropertiesSet();
        return new StepBuilder("myStep1")
                .<ConsumersPriceIndex, ConsumersPriceIndex>chunk(352535,transactionManager)
                .repository(jobRepository)
                .reader(reader())
                .processor(processor()) //replace c
                .writer(consumerWriter)
                .taskExecutor(executor)
                .build();
    }

    //Duplicate Step
    /* We Can Schedule Many Steps with Same Functionality
    along With Threads so that File Reading time will reduce....

    @Bean
    public Step step2(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.afterPropertiesSet();
        return new StepBuilder("myStep2")
                .<ConsumersPriceIndex, ConsumersPriceIndex>chunk(352535,transactionManager)
                .repository(jobRepository)
                .reader(reader())
                .processor(processor()) //replace c
                .writer(consumerWriter)
                .taskExecutor(executor)
                .build();
    }

    @Bean
    public Step step3(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.afterPropertiesSet();
        return new StepBuilder("myStep3")
                .<ConsumersPriceIndex, ConsumersPriceIndex>chunk(352535,transactionManager)
                .repository(jobRepository)
                .reader(reader())
                .processor(processor()) //replace c
                .writer(consumerWriter)
                .taskExecutor(executor)
                .build();
    } */

    //New Job To Insert another data to Another Table
    @Bean
    public Step step2(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(500);
        executor.setMaxPoolSize(500);
        executor.afterPropertiesSet();
        return new StepBuilder("myStep2")
                .<ConsumersPriceIndex, ModelClass>chunk(3525,transactionManager)
                .repository(jobRepository)
                .reader(reader())
                .processor(processor2()) //replace c
                .writer(modelWriter)
                .taskExecutor(executor)
                .build();
    }

    @Bean
    public ConsumerModelProcess processor2(){
        return new ConsumerModelProcess();
    }

    @Bean
    public Job job(){
        //Implementing Parallel Flow in Step Runs
        /*Flow anotherflow1 = new FlowBuilder<Flow>("AnotherFlow")
                .start(step3())
                .build(); */

        Flow anotherflow = new FlowBuilder<Flow>("AnotherFlow")
                .start(step2())
                .build();
        Flow flow = new FlowBuilder<Flow>("Parallel")
                .start(step())
                .split(new SimpleAsyncTaskExecutor())
                .add(anotherflow)
                .build();
        return new JobBuilder("MyJob")
                .repository(jobRepository)
                .start(flow)
                .end()
                .build();
    }
//Using Thread pool in step method so commented this
//    @Bean
//    public TaskExecutor executor(){
//        SimpleAsyncTaskExecutor exec = new SimpleAsyncTaskExecutor();
//        exec.setConcurrencyLimit(32);
//        return exec;
//    }


}
