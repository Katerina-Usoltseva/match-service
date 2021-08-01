package org.srvm.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
public class User implements Serializable {
    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false,
            cascade = CascadeType.ALL
    )
    @JoinColumn(unique = true)
    private CommonAccount commonAccount;

    @Getter
    @Setter
    private MatchInfo matchInfo;

    protected User() {
    }

    public User (String firstname, String lastname, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname=" + firstname +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

