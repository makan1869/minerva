package ir.serenade.minerva;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
public class PublisherRoutingConfiguration {

    @Bean(name = "hamrahangConnectionFactory")
    @Primary
    public ConnectionFactory hamrahangConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.16.93.12:61616");
        return connectionFactory;
    }

    @Bean(name = "hamrahangListenerContainerFactory")
    public JmsListenerContainerFactory<?> hamrahangListenerContainerFactory( @Qualifier("hamrahangConnectionFactory") ConnectionFactory connectionFactory,
                                                                      DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean(name = "tiasbConnectionFactory")
    public ConnectionFactory tiasbConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.17.90.3:51515");
        return connectionFactory;
    }

    @Bean(name = "tiasbListenerContainerFactory")
    public JmsListenerContainerFactory<?> tiasbListenerContainerFactory( @Qualifier("tiasbConnectionFactory") ConnectionFactory connectionFactory,
                                                                             DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }


    @Bean(name = "herminiusConnectionFactory")
    public ConnectionFactory herminiusConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.17.90.49:61616");
        return connectionFactory;
    }

    @Bean(name = "herminiusListenerContainerFactory")
    public JmsListenerContainerFactory<?> herminiusListenerContainerFactory( @Qualifier("herminiusConnectionFactory") ConnectionFactory connectionFactory,
                                                                         DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
