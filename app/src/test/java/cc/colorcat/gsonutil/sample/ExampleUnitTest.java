package cc.colorcat.gsonutil.sample;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import cc.colorcat.gsonutil.GsonUtils;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testJson() {
        Person person = new Person();
        person.setId("001");
        person.setAge(21);
        person.setBirthday(new Date());
        person.setPhones(new String[]{"1000", "1239"});
        person.setFriends(Arrays.asList("Tom", "Jerry"));
        String json = GsonUtils.toJson(person);
        System.out.println(json);

        String testJson = "{\"id\":null,\"age\":null,\"birthday\":null,\"phones\":null,\"friends\":null}";
        Person testPerson = GsonUtils.fromJson(testJson, Person.class);
        System.out.println(testPerson);
        assertEquals(testPerson.getId(), "");
        assertEquals(testPerson.getAge(), 0);
        Assert.assertArrayEquals(testPerson.getPhones(), new String[]{});
        assertEquals(testPerson.getFriends(), Collections.<String>emptyList());
    }
}