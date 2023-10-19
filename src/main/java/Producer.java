import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class Producer implements InitializingBean{

	@Bean
	public KafkaTemplate kafkaTemplate() {
		Properties p = new Properties();
		p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new KafkaTemplate(new DefaultKafkaProducerFactory(p));
	}
	
	@Autowired private KafkaTemplate<String, String> kafkaTemplate;

	public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Producer.class);
    }

	public void afterPropertiesSet() throws Exception {
		kafkaTemplate.send("test", "data");
	}
}