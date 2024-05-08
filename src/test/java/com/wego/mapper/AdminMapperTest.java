package com.wego.mapper;

import com.wego.entity.domain.Admin;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/2
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("/spring-mybatis.xml")
class AdminMapperTest {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    void selectByPrimaryKey() {
        final Admin admin = adminMapper.selectByPrimaryKey(1001L);
        System.out.println(admin);
    }

    @Test
    void insert() {
        Admin admin = new Admin();
        admin.setNickname("王二");
        final int res = adminMapper.insert(admin);
        System.out.println(res);
    }

    @Test
    void select() {
        Admin admin = Admin.builder()
                .nickname("张")
                .build();
        final List<Admin> adminList = adminMapper.select(admin);
        adminList.forEach(System.out::println);
    }

    @Test
    void selectByExample() {
        Example example = new Example(Admin.class);
        //example.selectProperties("id","account","nickname");
        example.excludeProperties("createTime", "updateTime")
                .orderBy("id").desc()
                .orderBy("createTime").asc();
        final Example.Criteria criteria = example.createCriteria();
        criteria.andLike("nickname", "%张%")
                .andGreaterThan("state", 7);


        final List<Admin> adminList = adminMapper.selectByExample(example);
        adminList.forEach(System.out::println);
    }

    @Test
    void selectByRowBounds() {
        int pageNum = 4;
        int pageSize = 8;
        Admin admin = Admin.builder()
                .account("%aa")
                .build();
        RowBounds rowBounds = new RowBounds((4 - 1) * 8, 8);
        final List<Admin> adminList = adminMapper.selectByRowBounds(admin, rowBounds);
        System.out.println(adminList);
    }

    @Test
    void deleteByIds() {
        final int res = adminMapper.deleteByIds("1100,1099,1098");
        System.out.println(res);
    }

    //@Test
    //void batchUpdate(){
    //    List<Admin> list = new ArrayList<>();
    //    list.add(Admin.builder().id(1098L).nickname("aa").build());
    //    list.add(Admin.builder().id(1099L).nickname("bb").build());
    //    list.add(Admin.builder().id(1100L).nickname("cc").build());
    //    adminMapper.batchUpdate(list);
    // }

    @Test
    void batchUpdateSelective() {
        List<Admin> list = new ArrayList<>();
        list.add(Admin.builder().id(1092L).nickname("cc").build());
        list.add(Admin.builder().id(1093L).nickname("aa").build());
        list.add(Admin.builder().id(1094L).nickname("bb").build());
        adminMapper.batchUpdateSelective(list);
    }

    @Test
    void insertList() {
        List<Admin> list = new ArrayList<>();
        list.add(Admin.builder().nickname("cc1").build());
        list.add(Admin.builder().nickname("aa1").build());
        list.add(Admin.builder().nickname("bb1").build());
        adminMapper.insertList(list);
    }

}