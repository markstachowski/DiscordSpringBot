package com.stachowski.discordspringbot.events;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public abstract class MessageListener {

  public Mono<Void> processCommand(Message eventMessage) {
    return Mono.just(eventMessage)
        .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
        .filter(message -> message.getContent().equalsIgnoreCase("!hello"))
        .flatMap(Message::getChannel)
        .flatMap(
            channel ->
                channel.createMessage(
                    "Greetings, I'm Mr. Poopybutthole and here to:\n"
                        + " - give stock market news updates\n"
                        + " - generate charts\n"
                        + " - KILL THE MALAYSIAN PRIME MINISTER "))
        .then();
  }
}
