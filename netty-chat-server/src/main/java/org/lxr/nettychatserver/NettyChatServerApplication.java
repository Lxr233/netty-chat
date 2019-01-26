package org.lxr.nettychatserver;

import org.lxr.nettychatserver.server.NettyChatServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyChatServerApplication implements CommandLineRunner{

	@Autowired
	@Qualifier("nettyChatServer")
	private NettyChatServer nettyChatServer ;


	public static void main(String[] args) {
		SpringApplication.run(NettyChatServerApplication.class, args);
	}

	@Override
	public void run(String... args)
	{
		nettyChatServer.start();
	}
}

