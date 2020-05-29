package helper.generator;

import com.learn.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * description:
 *
 * @author zhunhuang, 2020/5/27
 */
public class UserGenerator {

    public static User generateRandom() {
        return generate(RandomStringUtils.random(10), RandomUtils.nextInt(0, 200));
    }

    public static User generate(String name, int age) {
        return generate(RandomUtils.nextInt(0, Integer.MAX_VALUE), name, age);
    }

    public static User generate(int id, String name, int age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);

        return user;
    }
}
