package org.sbelei.booksvis;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BookVisCoreConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
/*
    @Bean
    public FlatFileItemReader<String> pdfReader() {
        return new FlatFileItemReaderBuilder<String>()
                .name("lineFromPDFReader")
                .resource("./dll/Bogdanovych_Mat_2ukr_2017.pdf")
                .build();
    }

 */

    @Bean
    public Job loadBookAndGetNouns() {
        return this.jobBuilderFactory.get("loadBookAndGetNouns")
                .preventRestart() //temporary. unless I'm sure how to deal with that
                .start(readLinesFromPdf())
//                .next(filterEmptyLines())
//                .next(leaveOnlyNouns())
//                .next(retriveTranslations())
//                .next(groupByTranslation())
//                .next(downloadImages())
                .build();
    }

    private Step readLinesFromPdf() {
        return this.stepBuilderFactory.get("readLinesFromPdf")
                .tasklet((contribution, chunkContext) -> null)
                .build();
    }
}
