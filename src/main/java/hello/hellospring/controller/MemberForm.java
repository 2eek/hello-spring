package hello.hellospring.controller;

public class MemberForm {
    //회원가입 시 name="name"이 멤버변수 이름에 해당됨 setName에 들어감 -> getName으로 꺼낼 수 있음
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
