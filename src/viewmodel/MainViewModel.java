package viewmodel;

import java.util.Random;

import model.MyNetShop;

public class MainViewModel {
	public void listData(MyNetShop myNS) {
		System.out.println(myNS.getNumOfReview() + " " + myNS.getNumOfCustomers() + " " + myNS.getNumOfBooks());
		System.out.println();

		for (int i = 0; i < 30; i++) {
			System.out.print("         ");
			for (int j = 0; j < myNS.getNumOfBooks(); j++) {
				System.out.print(myNS.getTopRandom(j, i) + " ");
			}
			System.out.println();
		}
		System.out.println();

		for (int i = 0; i < myNS.getNumOfCustomers(); i++) {
			for (int j = 0; j < myNS.getNumOfBooks() + 30; j++) {
				if (j < 30) {
					System.out.print(myNS.getLeftRandom(j, i) + " ");
				} else if (j == 30) {
					System.out.print(" " + myNS.getReview(i, j - 30) + "   ");
				} else {
					System.out.print(myNS.getReview(i, j - 30) + "   ");
				}

			}
			System.out.println();
		}
		System.out.println();

		System.out.println(getError(myNS));
	}

	public void list2D(double[][] t, int l, int w) {
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(t[i][j] + " ");
			}
			System.out.println();
		}
	}

	public double[][] recommend(MyNetShop myNS) {
		double[][] recomendBook = new double[myNS.getNumOfCustomers()][myNS.getNumOfBooks()];
		for (int i = 0; i < myNS.getNumOfCustomers(); i++) {
			for (int j = 0; j < myNS.getNumOfBooks(); j++) {
				recomendBook[i][j] = myNS.getReview(i, j);
			}
		}

		for (int i = 0; i < myNS.getNumOfCustomers(); i++) {
			for (int j = 0; j < myNS.getNumOfBooks(); j++) {
				if (recomendBook[i][j] == 0) {
					recomendBook[i][j] = setReview(j, i, myNS);
				} else {
					recomendBook[i][j] = 0;
				}
			}
		}

		return recomendBook;
	}

	public double setReview(int x, int y, MyNetShop myNS) {
		double temp = 0.0;
		for (int k = 0; k < 30; k++) {
			temp += myNS.getTopRandom(x, k) * myNS.getLeftRandom(k, y);
		}
		if (temp < 1) {
			return -2;
		}
		return temp;
	}

	public void topN(int cus, double[][] reviews, int nOC, int nOB, int n) {
		double max = 0.0;
		int bookPlace = 0;
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < nOB; i++) {
				if (reviews[cus][i] > max) {
					max = reviews[cus][i];
					bookPlace = i;
				}
			}
			if (j < 9) {
				System.out.print(bookPlace + "\t");
			} else {
				System.out.print(bookPlace);
			}
			max = 0.0;
			reviews[cus][bookPlace] = 0.0;
		}
	}

	public double[][] createTWRN(int x, int y) {
		double[][] rand = new double[x][y];
		Random r = new Random();
		for (int j = 0; j < x; j++) {
			for (int i = 0; i < y; i++) {
				rand[j][i] = 1.0 + (Math.sqrt(5.0) - 1.0) * r.nextDouble();
			}
		}
		return rand;
	}

	public double getError(MyNetShop myNS) {
		double error = 0.0;
		for (int i = 0; i < myNS.getNumOfCustomers(); i++) {
			for (int j = 0; j < myNS.getNumOfBooks(); j++) {
				double temp = 0.0;
				for (int k = 0; k < 30; k++) {
					temp += (myNS.getTopRandom(j, k) * myNS.getLeftRandom(k, i));
				}
				if (myNS.getReview(i, j) != 0) {
					error += (myNS.getReview(i, j) - temp) * (myNS.getReview(i, j) - temp);
				}
			}
		}
		return error;
	}

	public void correct(MyNetShop myNS) {
		double error = 0.0;
		double a = 0.00007;
		for (int i = 0; i < myNS.getNumOfCustomers(); i++) {
			for (int j = 0; j < myNS.getNumOfBooks(); j++) {
				double temp = 0.0;
				for (int k = 0; k < 30; k++) {
					temp += myNS.getTopRandom(j, k) * myNS.getLeftRandom(k, i);
				}
				if (myNS.getReview(i, j) != 0) {
					error = (myNS.getReview(i, j) - temp);
					double myError = 2 * error * a;
					for (int l = 0; l < 30; l++) {
						myNS.addToOneTopRandom(j, l, myError * myNS.getLeftRandom(l, i));
						myNS.addToOneLeftRandom(l, i, myError * myNS.getTopRandom(j, l));
					}
				}
			}
		}
	}
}