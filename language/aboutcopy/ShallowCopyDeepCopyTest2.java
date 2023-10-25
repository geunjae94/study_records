package sideproject.personalquestions.test2.aboutcopy;

import sideproject.personalquestions.test2.aboutcopy.testobject.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShallowCopyDeepCopyTest2 {

    @BeforeEach
    public void beforeEach(){
    }

    @Test
    public void test() throws CloneNotSupportedException {

        TestB testB = new TestB(99, "zaza");

        TestA testA = new TestA(20, "kuma", testB); //얕은 복사(shallow copy)
        TestA clone = testA.clone(); //깊은 복사(deep copy)


        testA.setI(50);
        assertThat(testA.getI()).isEqualTo(50);
        assertThat(clone.getI()).isEqualTo(50); //failed,  Actual: "20"

        testA.setStr("zozo");
        assertThat(testA.getStr()).isEqualTo("zozo");
        assertThat(clone.getStr()).isEqualTo("zozo"); //failed,  Actual: "kuma"


        //sampleA.getSampleB().setI(199); 생략

        testA.getTestB().setStr("coco");
        assertThat(testA.getTestB().getStr()).isEqualTo("coco");
        assertThat(clone.getTestB().getStr()).isEqualTo("coco"); //passed

    }

    @AfterEach
    public void afterEach() {
    }
}
