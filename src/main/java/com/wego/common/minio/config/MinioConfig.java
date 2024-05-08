package com.wego.common.minio.config;

import io.minio.MinioClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Minio配置类
 *
 * @author hc
 */
public class MinioConfig {
    private String url;
    private String username;
    private String password;
    private String bucketName;

    {
        final Properties properties = new Properties();
        final InputStream is = MinioConfig.class.getResourceAsStream("/minio.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("minio.url");
        username = properties.getProperty("minio.username");
        password = properties.getProperty("minio.password");
        bucketName = properties.getProperty("minio.bucketName");
    }

    public static void main(String[] args) {
        final MinioConfig config = new MinioConfig();
        final MinioClient minioClient = config.getMinioClient();
        System.out.println(minioClient);
        System.out.println(config.getBucketName());
    }

    public String getBucketName() {
        return bucketName;
    }

    public MinioClient getMinioClient() {
        MinioClient minioClient = MinioClient.builder().endpoint(url)
                .credentials(username, password).build();
        return minioClient;
    }

}