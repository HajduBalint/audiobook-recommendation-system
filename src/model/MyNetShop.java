package model;

public class MyNetShop {
	public MyNetShop(int rew, int cust, int book) {
		this.numOfBooks = book;
		this.numOfCustomers = cust;
		this.numOfReview = rew;
		reviews = new int[numOfCustomers][numOfBooks];
	}

	private int numOfReview;
	private int numOfCustomers;
	private int numOfBooks;
	private int[][] reviews;
	private int[][] recommend;
	private double[][] topRandom;
	private double[][] leftRandom;

	public void setRecommend(int[][] rec) {
		recommend = rec;
	}

	public int getOneRecommend(int x, int y) {
		return recommend[x][y];
	}

	public void setTopRandom(double[][] top) {
		topRandom = top;
	}

	public double getTopRandom(int x, int y) {
		// if (x < numOfBooks && y < 30 && x > -1 && y > -1) {
		return topRandom[x][y];
		// } else {
		// return -1;
		// }
	}

	public void addToOneTopRandom(int x, int y, double n) {
		// if (topRandom[x][y] + n < 0 || topRandom[x][y] + n > 5) {
		// return;
		// }
		topRandom[x][y] += n;
	}

	public void setLeftRandom(double[][] left) {
		leftRandom = left;
	}

	public double getLeftRandom(int x, int y) {
		// if (x < 30 && y < numOfCustomers && x > -1 && y > -1) {
		return leftRandom[x][y];
		// } else {
		// return -1;
		// }
	}

	public void addToOneLeftRandom(int x, int y, double n) {
		// if (leftRandom[x][y] + n < 0 || leftRandom[x][y] + n > 1) {
		// return;
		// }
		leftRandom[x][y] += n;
	}

	public int getNumOfBooks() {
		return numOfBooks;
	}

	public void setNumOfBooks(int numOfBooks) {
		this.numOfBooks = numOfBooks;
	}

	public int getNumOfReview() {
		return numOfReview;
	}

	public void setNumOfReview(int numOfReview) {
		this.numOfReview = numOfReview;
	}

	public int getNumOfCustomers() {
		return numOfCustomers;
	}

	public void setNumOfCustomers(int numOfCustomers) {
		this.numOfCustomers = numOfCustomers;
	}

	public void addReview(int userId, int bookId, int rew) {
		if (userId < numOfCustomers && bookId < numOfBooks) {
			reviews[userId][bookId] = rew;
		}
	}

	public int getReview(int x, int y) {
		// if (x < numOfCustomers && y < numOfBooks && x > -1 && y > -1) {
		return reviews[x][y];
		// } else {
		// return -1;
		// }
	}
}
