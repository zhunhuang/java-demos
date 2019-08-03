package leetCode;

/**
 * description:
 *
 * @author zhun.huang 2019-08-02
 */
public class No12 {
     public String intToRoman(int num) {
        int values[]={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String reps[]={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        String res = "";
        for(int i=0; i<13; i++){
            while(num>=values[i]){
                num -= values[i];
                res = res.concat(reps[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new No12().intToRoman(3999));
    }
}
