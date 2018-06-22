package com.retronic.remoting.system.services.hero;

import com.retronic.remoting.dtos.hero.ClassDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.utils.SecurityTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-rest.xml"})
public class ClassRemoteTest {

    @Autowired
    private IGenericRemote<ClassDto, Integer> classRemote;

    @Autowired
    private SecurityTestUtils securityTestUtils;

    @Test
    @SuppressWarnings("unchecked")
    public void testGetClass() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        Response response = classRemote.get(1);
        ClassDto dto = (ClassDto) response.getEntity();
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetClasss() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        Response response = classRemote.getAll();
        List<ClassDto> dtos = (List<ClassDto>) response.getEntity();
        assertTrue(dtos.size() > 0);
    }
}
