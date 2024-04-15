package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Movie movie = new Movie();
//            movie.setActor("길동");
//            movie.setDirector(("bbb"));
//            movie.setName("바람");
//            movie.setPrice(1000);
//            em.persist(movie);

//            Member member = new Member();
//            member.setName("hello");
//            member.setHomeAddress(new Address("city", "street", "zipcode"));
            Member member = new Member();
            member.setName("hello");
            member.setHomeAddress(new Address("city", "street", "zipcode"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("city1", "street1", "zipcode1"));
            member.getAddressHistory().add(new Address("city2", "street2", "zipcode2"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("========================== START ==========================");
            Member findMember = em.find(Member.class, member.getId());
            // city -> newCity
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            //치킨 -> 한식 (String이기 때문에 지우고 새로넣어야한다)
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            //equals, hashcode가 선언돼있어야한다. embedded타입이라서
            findMember.getAddressHistory().remove(new Address("city1", "street1", "zipcode1"));
            findMember.getAddressHistory().add(new Address("newCity", "street1", "zipcode1"));
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
