package com.example.inventoryapp.mq;

import com.example.inventoryapp.mapper.ItemMapper;
import com.example.inventoryapp.model.Item;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MQRequestListener implements CommandLineRunner {
    private static final String TASK_QUEUE_NAME = "assignItem";

    private ItemMapper itemMapper;

    public MQRequestListener(ItemMapper itemMapper) throws Exception {
        this.itemMapper = itemMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

            JSONParser parser = new JSONParser();
            try {
                JSONObject json = (JSONObject) parser.parse(message);
                String action = (String) json.get("action");
                if (action.equals("updateAvail")) {
                    long idItem = (long) json.get("idItem");
                    boolean available = (boolean) json.get("available");
                    updateItemAvailability(idItem, available);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" [x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {
        });
    }

    private void updateItemAvailability(long idItem, boolean available) {
        Item item = itemMapper.getById(idItem);
        item.setAvailable(available);
        itemMapper.update(item);
    }
}
