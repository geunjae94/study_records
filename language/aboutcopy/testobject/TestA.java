package sideproject.personalquestions.test2.aboutcopy.testobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TestA implements Cloneable{
    private int i;
    private String str;
    private TestB testB;

    @Override   //protected native Object clone() throws CloneNotSupportedException;
    public TestA clone() throws CloneNotSupportedException {
        return (TestA) super.clone();
    }
}
