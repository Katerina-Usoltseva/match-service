package org.srvm.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class MatchInfo {
    @Getter
    @Setter
    @NotNull
    private String interests;

    @Getter
    @Setter
    @NotNull
    private String gender;

    @Getter
    @Setter
    @NotNull
    private Integer age;

    protected MatchInfo() {
    }

    public MatchInfo(String interests, String gender, Integer age) {
        this.interests = interests;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return "MatchInfo{" +
                "interests=" + interests +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
