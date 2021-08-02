package org.srvm.test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;
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
import java.util.stream.Stream;

public class MultithreadedTest extends MultithreadedTestCase {
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void initialize() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("jpa.hibernate.postgres");
    }

    public void thread1() throws InterruptedException {
        List<User> users = new ArrayList<>();
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

                        Thread.sleep(100);
                    }
                    catch (Exception ex) {
                        //
                    }
                }
        );

        EntityManager em = entityManagerFactory.createEntityManager();
        service.setEntityManager(em);
        List<?> resultList = service.getUserMatches("cats");
        List <CommonAccount> list =
                resultList
                        .stream()
                        .map(item -> item instanceof CommonAccount ? (CommonAccount) item : null)
                        .collect(Collectors.toList());

        list.forEach(e -> System.out.println(e.toString()));

        em.close();
    }

    public void thread2() throws InterruptedException {
        Stream<Integer> ints = Stream
                .iterate(0, i -> i + 1);

        ints
            .limit(20)
            .forEach(System.out::println);
    }


    @Override
    public void finish() {
        entityManagerFactory.close();
    }

    @Test
    public void testThreads() throws Throwable {
        TestFramework.runManyTimes(new MultithreadedTest(), 10);
    }
}
