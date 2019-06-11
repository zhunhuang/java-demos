import util.JacksonUtil;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author: zhun.huang
 * @create: 2018-05-04 下午5:39
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        User user = new User();
        String json = JacksonUtil.serialize(user);
        try {
            HashMap<String, String> map = (HashMap<String, String>)JacksonUtil.jsonToMap(json);
            System.out.println(json);
            map.put("123",null);
            System.out.println(map.toString());
            User user1 = JacksonUtil.deSerialize(json, User.class);
            System.out.println(user1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class User {
        String userName = "123";

        Date start = new Date();

        Love love = new Love();

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Date getStart() {
            return start;
        }

        public void setStart(Date start) {
            this.start = start;
        }

        public Love getLove() {
            return love;
        }

        public void setLove(Love love) {
            this.love = love;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + '\'' +
                    ", start=" + start +
                    ", love=" + love +
                    '}';
        }

        public static class Love {
            String name = "book";
            int id = 1;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return "Love{" +
                        "name='" + name + '\'' +
                        ", id=" + id +
                        '}';
            }
        }
    }
}
