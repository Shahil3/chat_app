package com.example.chat.chat;

public class ChatMessage {

    private String content;
    private String sender;
    private MessageType type;

    // No-argument constructor
    public ChatMessage() {
    }

    // All-argument constructor
    public ChatMessage(String content, String sender, MessageType type) {
        this.content = content;
        this.sender = sender;
        this.type = type;
    }

    // Getters
    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public MessageType getType() {
        return type;
    }

    // Setters
    public void setContent(String content) {
        this.content = content;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    // Builder Class
    public static class Builder {
        private String content;
        private String sender;
        private MessageType type;

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder type(MessageType type) {
            this.type = type;
            return this;
        }

        public ChatMessage build() {
            return new ChatMessage(content, sender, type);
        }
    }

    // Builder Method
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", type=" + type +
                '}';
    }
}