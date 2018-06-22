package com.retronic.remoting.system.services.hero;

import com.retronic.remoting.dtos.hero.SkillDto;
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
public class SkillRemoteTest {

    @Autowired
    private IGenericRemote<SkillDto, Integer> skillRemote;

    @Autowired
    private SecurityTestUtils securityTestUtils;

    @Test
    @SuppressWarnings("unchecked")
    public void testGetSkill() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        Response response = skillRemote.get(1);
        SkillDto dto = (SkillDto) response.getEntity();
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetSkills() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        Response response = skillRemote.getAll();
        List<SkillDto> dtos = (List<SkillDto>) response.getEntity();
        assertTrue(dtos.size() > 0);
    }
}
