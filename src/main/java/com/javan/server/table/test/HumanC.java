package com.javan.server.table.test;

public class HumanC extends HumanB {


    public void test() {
        super.behaviorC("Something From HUMAN C");
        super.behaviorA("Something From HUMAN C to Behavior A");
    }

    @Override
    public void behaviorB() {
        System.out.println("This is behavior Overide Behavior B from C");
    }

    
}
