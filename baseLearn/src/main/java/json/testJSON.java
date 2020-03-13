package json;

import com.alibaba.fastjson.JSON;

/**
 * description:
 *
 * @author zhunhuang, 2020/3/13
 */
public class testJSON {

    public static void main(String[] args) {

        User user = new User();
        user.userName="123";
        user.age=123L;
        user.like=123.123d;
        user.money="321.321";
        System.out.println(JSON.toJSONString(user));
        String userStr="{\"age\":123,\"like\":123.123,\"money\":\"321.321\",\"userName\":\"123\"}";
        User2 user2 = JSON.parseObject(userStr, User2.class);
        System.out.println(JSON.toJSONString(user2));

        User user1 = JSON.parseObject(JSON.toJSONString(user2), User.class);
        System.out.println(JSON.toJSONString(user));
        System.out.println(JSON.toJSONString(user1));
    }

    public static class User {
        private String userName;
        private Long age;
        private Double like;
        private String money;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Long getAge() {
            return age;
        }

        public void setAge(Long age) {
            this.age = age;
        }

        public Double getLike() {
            return like;
        }

        public void setLike(Double like) {
            this.like = like;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        @Override
        public String toString() {
            return "{\"User\":{"
                    + "\"userName\":\"" + userName + "\""
                    + ", \"age\":\"" + age + "\""
                    + ", \"like\":\"" + like + "\""
                    + ", \"money\":\"" + money + "\""
                    + "}}";
        }
    }

    public static class User2 {
        private Long userName;
        private String age;
        private String like;
        private Double money;

        public Long getUserName() {
            return userName;
        }

        public void setUserName(Long userName) {
            this.userName = userName;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getLike() {
            return like;
        }

        public void setLike(String like) {
            this.like = like;
        }

        public Double getMoney() {
            return money;
        }

        public void setMoney(Double money) {
            this.money = money;
        }

        @Override
        public String toString() {
            return "{\"User2\":{"
                    + "\"userName\":\"" + userName + "\""
                    + ", \"age\":\"" + age + "\""
                    + ", \"like\":\"" + like + "\""
                    + ", \"money\":\"" + money + "\""
                    + "}}";
        }
    }
}
