package com.wego.commons.utils;

import com.wego.common.minio.MinioUtil;
import io.minio.Result;
import io.minio.StatObjectResponse;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
class MinioUtilTest {

    @Autowired
    private MinioUtil minioUtil;

    @Test
    void bucketExists() {
        boolean exists = minioUtil.bucketExists();
        System.out.println(exists);
    }

    @Test
    void createBucket() {
        boolean res = minioUtil.createBucket();
        System.out.println(res);
    }

    @Test
    void removeBucket() {
        boolean res = minioUtil.removeBucket();
        System.out.println(res);
    }

    @Test
    void getBucketPolicy() {
        String bucketPolicy = minioUtil.getBucketPolicy();
        System.out.println(bucketPolicy);
    }

    @Test
    void getBucket() {
        Bucket bucket = minioUtil.getBucket();
        System.out.println(bucket);
    }

    @Test
    void getAllBuckets() {
        List<Bucket> bucketList = minioUtil.getAllBuckets();
        bucketList.forEach(System.out::println);
    }

    @Test
    void createDirectory() {
        boolean res = minioUtil.createDirectory("bb/cc/dd");
        System.out.println(res);
    }

    @Test
    void objectExist() {
        boolean exist = minioUtil.objectExist("goods/mm2.jpg");
        System.out.println(exist);
    }

    @Test
    void folderExist() {
        String folder = "goods"; //顶层文件夹
        //folder = "bb/cc"; //二层子文件夹
        boolean res = minioUtil.folderExist(folder);
        System.out.println(res);
    }

    @Test
    void putObject1() throws Exception {
        File file = new File("E:\\图片\\android.jpg");
        MultipartFile multipartFile = new MockMultipartFile(
                "android1234.jpg", //文件名
                "android324.jpg", //originalName 相当于上传文件在客户机上的文件名
                MediaType.IMAGE_JPEG_VALUE, //文件类型
                new FileInputStream(file) //文件流
        );

        String url = minioUtil.putObject(multipartFile, "tmp", UUID.randomUUID().toString());
        System.out.println(url);
    }

    @Test
    void putObject2() throws Exception {
        File file = new File("E:\\图片\\android.jpg");
        MultipartFile multipartFile = new MockMultipartFile(
                "ab.jpg", //文件名
                "ab.jpg", //originalName 相当于上传文件在客户机上的文件名
                MediaType.IMAGE_JPEG_VALUE, //文件类型
                new FileInputStream(file) //文件流
        );

        String url = minioUtil.putObject(multipartFile, "哈哈");
        System.out.println(url);
    }

    @Test
    void putObject3() throws Exception {
        File file = new File("E:\\图片\\android.jpg");
        MultipartFile multipartFile = new MockMultipartFile(
                "ab.jpg", //文件名
                "abcd.jpg", //originalName 相当于上传文件在客户机上的文件名
                MediaType.IMAGE_JPEG_VALUE, //文件类型
                new FileInputStream(file) //文件流
        );

        String url = minioUtil.putObject(multipartFile);
        System.out.println(url);
    }

    @Test
    void putObject4() throws FileNotFoundException {
        File file = new File("E:\\图片\\android.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        String url = minioUtil.putObject(fileInputStream, "aa.jpg");
        System.out.println(url);
    }

    @Test
    void copyObject() {
        boolean res = minioUtil.copyObject("", "aa.jpg", "bb.jpg");
        System.out.println(res);
    }

    @Test
    void getObject1() {
        //这个方法通过Controller测试
        //HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        //minioUtil.getObject("aa.jpg",response);
    }

    @Test
    void getObject2() throws Exception {
        InputStream is = minioUtil.getObject("aa.jpg");
        StreamUtils.copy(is, new FileOutputStream("a1b1c1d1.jpg"));
    }

    @Test
    void getObjectInfo() {
        StatObjectResponse objectInfo = minioUtil.getObjectInfo("aa.jpg");
        System.out.println(objectInfo);
    }

    @Test
    void getObjectUrl() {
        String url = minioUtil.getObjectUrl("aa.jpg");
        System.out.println(url);
    }

    @Test
    void getAllBucketObjects() {
        List<Item> itemList = minioUtil.getBucketObjects();
        itemList.forEach(System.out::println);
    }

    @Test
    void getObjects() {
        Iterable<Result<Item>> objects = minioUtil.getObjects("aa", true);
        for (Result<Item> object : objects) {
            System.out.println(object);
        }
    }

    @Test
    void getAllObjectsByPrefix() {
        //查询名字第一个字母是a的文件
        List<Item> itemList = minioUtil.getAllObjectsByPrefix("a", true);
        itemList.forEach(System.out::println);
    }

    @Test
    void removeObject() {
        boolean res = minioUtil.removeObject("aa.jpg");
        System.out.println(res);
    }

    @Test
    void removeObjects() {
        boolean res = minioUtil.removeObjects("ab.jpg", "IMG_20230206_211229.jpg", "IMG_20230129_194908.jpg");
        System.out.println(res);
    }
}
