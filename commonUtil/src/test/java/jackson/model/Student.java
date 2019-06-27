package jackson.model;

/**
 * description:
 * create: 2019-06-26
 *
 * @author zhun.huang
 */
public class Student extends Person {

	private int degree;

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
}
