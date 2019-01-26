package org.lxr.nettychatclient;

import org.lxr.nettychatclient.client.NettyChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyChatClientApplication implements CommandLineRunner{


	@Autowired
	@Qualifier("nettyChatClient")
	private NettyChatClient nettyChatClient ;

	public static void main(String[] args) {
		SpringApplication.run(NettyChatClientApplication.class, args);
	}

	@Override
	public void run(String... args)
	{
		nettyChatClient.start();
	}

}

