package com.openshift.fordevelopers;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Objects;

@Entity
public class Post extends PanacheEntity {
    private String title;
    private String content;
    private String hostname;
    private long timestamp;

    public Post() {
    }
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        try {
            this.hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            this.hostname = "";
        }
        this.timestamp = new Date().getTime();
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Post)) {
            return false;
        }
        Post other = (Post) obj;

        return Objects.equals(this.title, other.title) && Objects.equals(this.content, other.content)
                && Objects.equals(this.timestamp, other.timestamp) && Objects.equals(this.hostname, other.hostname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.content, this.timestamp);
    }
}
