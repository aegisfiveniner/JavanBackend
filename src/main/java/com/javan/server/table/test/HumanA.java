package com.javan.server.table.test;

public class HumanA {

    private String fieldA;
    

    public String getFieldA() {
        return fieldA;
    }


    public void setFieldA(String fieldA) {
        this.fieldA = fieldA;
    }


    public void behaviorA(String something) {
        System.out.println("This is behavior A From A");
        behaviorB();
    }

    public void behaviorB() {
        System.out.println("This is behavior B From A");
    }

    public void behaviorC(String fieldA) {
        System.out.println("This is behavior C From A");
        setFieldA(fieldA);
    
    }
    
}
