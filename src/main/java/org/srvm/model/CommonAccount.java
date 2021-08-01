package org.srvm.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Account")
public class CommonAccount {
    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id;

    @Getter
    @Setter
    @NotNull
    private String nickname;

    @Getter
    @Setter
    @NotNull
    private String interests;

    @Getter
    @Setter
    @NotNull
    private Integer age;

    @Getter
    @Setter
    @NotNull
    private String gender;

    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "PICTURES")
    @MapKeyColumn(name = "FILENAME")
    @Column(name = "DESCRIPTION")
    protected Map<String, String> pictures = new HashMap<>();

    protected CommonAccount() {
        // no-args constructor by POJO
    }

    public CommonAccount(String nickname,
                         String interests,
                         Integer age,
                         String gender) {
        this.nickname = nickname;
        this.interests = interests;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "MatchInfo{" +
                "nickname=" + nickname +
                ", interests='" + interests + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
