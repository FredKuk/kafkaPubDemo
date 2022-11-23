package com.kafka.kafkaPub;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
// import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.messaging.MessageChannel;

import com.kafka.kafkaPub.model.Book;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
@Component
@RestController
public class KafkaPubApplication {
    private final StreamBridge streamBridge; // StreamBridge를 주입해주자.
	// @Autowired
	// private MessageChannel output;
    @Async
	@PostMapping("/publish")
	public Book pubishEvent(@RequestBody Book book){
		streamBridge.send("producer-out-0",MessageBuilder.withPayload(book).build());
		return book;
	}


	public static void main(String[] args) {
		SpringApplication.run(KafkaPubApplication.class, args);
	}

}
