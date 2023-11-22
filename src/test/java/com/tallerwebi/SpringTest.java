/*package com.tallerwebi;

import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateTestConfig.class,SpringWebTestConfig.class})
public abstract class SpringTest {
    @Autowired
    private SessionFactory sessionFactory;
    protected SessionFactory session(){return (SessionFactory) this.sessionFactory.getCurrentSession();}
}
 */
