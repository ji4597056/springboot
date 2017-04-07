package com.study.spring.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:44
 */
@Component
// 可以使用locations指定配置文件路径,eg：locations = "classpath:config/application.properties"
@ConfigurationProperties(prefix = "cfg")
public class DemoConfig {

    private String author;

    private int version;

    private String project;

    private List<String> members;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Configuation{");
        sb.append("author='").append(author).append('\'');
        sb.append(", version=").append(version);
        sb.append(", project='").append(project).append('\'');
        sb.append(", members=").append(members);
        sb.append('}');
        return sb.toString();
    }
}
