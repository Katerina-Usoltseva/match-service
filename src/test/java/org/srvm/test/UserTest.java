package org.srvm.test;

import org.junit.Before;
import org.junit.Test;
import org.srvm.model.CommonAccount;
import org.srvm.model.MatchInfo;
import org.srvm.model.User;
import org.srvm.service.AuctionService;
import org.srvm.service.AuctionServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUsers() throws Exception {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("jpa.hibernate.postgres");

        List<User> users = new ArrayList<User>();
        User user = new User("Tanya", "Sidorova", "88008008080");
        user.setCommonAccount(new CommonAccount("tanya", "cats", 19, "female"));
        CommonAccount commonAccount = user.getCommonAccount();
        commonAccount.getPictures().put("lake.jpg", "lake 2020");
        user.setMatchInfo(new MatchInfo("dogs", "male", 25));
        users.add(user);

        user = new User("Ivan", "Ivanov", "88008008081");
        user.setCommonAccount(new CommonAccount("ivannn", "dogs", 23, "male"));
        commonAccount = user.getCommonAccount();
        commonAccount.getPictures().put("mountains.jpg", "mountains 2019");
        user.setMatchInfo(new MatchInfo("cats", "female", 20));
        users.add(user);

        AuctionService service = new AuctionServiceImpl();
        users.forEach(u -> {
                    try {
                        EntityManager em = entityManagerFactory.createEntityManager();
                        service.setEntityManager(em);

                        em.getTransaction().begin();
                        service.storeUser(u);
                        em.getTransaction().commit();
                        em.close();
                    }
                    catch (Exception ex) {
                        //
                    }
                }
        );
    }

    @Test
    public void findUserMatches() throws Exception{
        AuctionService service = new AuctionServiceImpl();
        EntityManager em = entityManagerFactory.createEntityManager();
        service.setEntityManager(em);

        User user = service.findUser(31L);
        String interests = user.getMatchInfo().getInterests();
        List<?> resultList = service.getUserMatches(interests);

        List <CommonAccount> list =
                resultList
                .stream()
                .map(item -> item instanceof CommonAccount ? (CommonAccount) item : null)
                .collect(Collectors.toList());

        list.forEach(e -> System.out.println(e.toString()));

        em.close();
    }
}
