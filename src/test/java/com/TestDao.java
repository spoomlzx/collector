package com;

import com.google.gson.Gson;
import com.lan.dao.PowerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * package com
 *
 * @author zzc
 * @date 2017/3/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestDao {
    @Autowired
    private PowerMapper powerMapper;
    @Test
    public void test(){
        Gson gson=new Gson();
        System.out.println(gson.toJson(powerMapper.selectByPrimaryKey(1)));
    }
}
