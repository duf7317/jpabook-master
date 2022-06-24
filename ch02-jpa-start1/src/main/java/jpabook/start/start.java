package jpabook.start;

import java.util.List;

import javax.persistence.*;

public class start {
    public static void main(String[] args) {

        EntityManagerFactory emf =
        		Persistence.createEntityManagerFactory("jpabook");
        // 엔티티 매니저 팩토리 - 생성
        EntityManager em = emf.createEntityManager();
        //엔티티 매니저 - 생성
        EntityTransaction tx = em.getTransaction();
        // 트랜잭션 - 획득
        try {
        	tx.begin();  //트랜잭션 - 시작
        	logic(em); // 비지니스 로직 실행
        	tx.commit(); // 트랜잭션 - 커밋
        } catch (Exception a) {
        	tx.rollback(); //트랜젝션 롤백
        }finally{
        	em.close(); //엔티티 매니저- 종료 
        }
        emf.close(); //엔티티 매니저 팩토리 -종료
    }
    private static void logic(EntityManager em) {
    	String id= "id1";
    	Member member = new Member();
    	member.setId(id);
    	member.setUsername("승열");
    	member.setAge(18);
    	//등록dsdsa
    	em.persist(member);
    	
    	//수정
    	member.setAge(28);
    	
    	//한 건 조회
    	Member findMember = em.find(Member.class, id);
    	System.out.println("findMember="+findMember.getUsername()+"age="+findMember.getAge());
    											//JPQL jpa 쿼리문 객체를 쿼리함
    	List<Member> members = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    	System.out.println("members.size = "+ members.size());
    	em.remove(member);
    } //    비지니스 로직
}
