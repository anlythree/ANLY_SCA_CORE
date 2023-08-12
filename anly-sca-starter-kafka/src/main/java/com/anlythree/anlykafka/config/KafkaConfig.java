package com.anlythree.anlykafka.config;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @DATE: 2023/8/10
 * @USER: anlythree
 */
@AutoConfiguration
public class KafkaConfig {
    // start of producer

    @Value("${spring.kafka.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;

    @Value("${spring.kafka.thread.number:5}")
    private Integer THREAD_NUM;

    @Value("${spring.kafka.batch-size:200}")
    private Integer BATCH_SIZE;

    @Value("${spring.kafka.producer.acks:all}")
    private String ACK_S;

    @Value("${spring.kafka.producer.retries:0}")
    private Integer RETRIES;

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>(16);
        // 多个节点的话用逗号隔开，例如：xxx:9092,xxx:9092,xxx:9092
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        // 当retris为0时，produce不会重复。retirs重发，此时repli节点完全成为leader节点，不会产生消息丢失。
        props.put(ProducerConfig.RETRIES_CONFIG, RETRIES);
        // acks = 0 如果设置为零，则生产者将不会等待来自服务器的任何确认，该记录将立即添加到套接字缓冲区并视为已发送。在这种情况下，无法保证服务器已收到记录，并且重试配置将不会生效（因为客户端通常不会知道任何故障），为每条记录返回的偏移量始终设置为-1。
        // acks = 1 这意味着leader会将记录写入其本地日志，但无需等待所有副本服务器的完全确认即可做出回应，在这种情况下，如果leader在确认记录后立即失败，但在将数据复制到所有的副本服务器之前，则记录将会丢失。
        // acks = all（-1） 这意味着leader将等待完整的同步副本集以确认记录，这保证了只要至少一个同步副本服务器仍然存活，记录就不会丢失，这是最强有力的保证，这相当于acks = -1的设置。
        // 可以设置的值为：all, -1, 0, 1
        props.put(ProducerConfig.ACKS_CONFIG, ACK_S);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
        // 每次批量发送消息的数量,produce积累到一定数据，一次发送
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, BATCH_SIZE);
        // 指定消息key和消息体的编解码方式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    // end of producer

    // start of listener


    @Value("${spring.kafka.consumer.groupId}")
    private String GROUP_ID;

    @Value("${spring.kafka.consumer.enable-auto-commit:true}")
    private Boolean ENABLE_AUTO_COMMIT_CONFIG;

    @Value("${spring,kafka.consumer.auto-commit-interval:100}")
    private Integer AUTO_COMMIT_INTERVAL_MS_CONFIG;

    @Value("${spring,kafka.consumer.auto-offset-reset:smallest}")
    private String AUTO_OFFSET_RESET_CONFIG;



    @Bean
    @ConditionalOnMissingBean(name = "kafkaBatchListenerForReceiver")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaBatchListenerForReceiver() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory();
        factory.setConcurrency(1);
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean(name = "kafkaBatchListener")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaBatchListener() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory();
        factory.setConcurrency(THREAD_NUM);
        return factory;
    }

    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>(16);
        // 连接的kafka消费地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        // 指定默认消费者group id --> 由于在kafka中，同一组中的consumer不会读取到同一个消息，依靠groud.id设置组名
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        // smallest和largest才有效，如果smallest重新0开始读取，如果是largest从logfile的offset读取。一般情况下我们都是设置smallest
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,AUTO_OFFSET_RESET_CONFIG);
        // 设置自动提交offset
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, ENABLE_AUTO_COMMIT_CONFIG);
        // 如果'enable.auto.commit'为true，则消费者偏移自动提交给Kafka的频率（以毫秒为单位），默认值为5000。
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,AUTO_COMMIT_INTERVAL_MS_CONFIG);
        // props.put(ConsumerConfig.CLIENT_ID_CONFIG, IPUtil.getHostName() + “-h5-kafka”);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,10);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 9000);
        return props;
    }

    // end of listener
}
