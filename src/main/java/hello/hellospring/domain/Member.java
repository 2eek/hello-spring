package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity// jp가 관리하는 엔티티가 된다.
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //임의값. sequence 시스템에 저장되는 id

    //@Column(name = "username") 실제컬럼
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
