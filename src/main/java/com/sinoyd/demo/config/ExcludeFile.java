package com.sinoyd.demo.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-08 16:47
 */
public class ExcludeFile implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        if (className.contains("SecurityAutoConfiguration")){
            return true;
        }
        if(className.contains("com.sinoyd.frame.base.configuration")){
            return true;
        }
        return false;
    }
}
