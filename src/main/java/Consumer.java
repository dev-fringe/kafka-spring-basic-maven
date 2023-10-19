import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
public class Consumer {

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		Properties p = new Properties();
		p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		p.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
		p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory(p));
		return factory;
	}

	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(Consumer.class);
	}

	@KafkaListener(topics = "test")
	public void listenGroupFoo(String message) {
		System.out.println("Received Message in group group1: " + message);
	}
}