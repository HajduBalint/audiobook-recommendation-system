package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.MyNetShop;
import viewmodel.MainViewModel;

public class Main {

	public static void main(String[] args) {
		MyNetShop myNS = null;
		MainViewModel mvm = new MainViewModel();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String thisLine;

		try {
			thisLine = br.readLine();
			String[] line = thisLine.split("\t");
			myNS = new MyNetShop(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
			myNS.setLeftRandom(mvm.createTWRN(30, myNS.getNumOfCustomers()));
			myNS.setTopRandom(mvm.createTWRN(myNS.getNumOfBooks(), 30));
			for (int i = 0; i < myNS.getNumOfReview(); i++) {
				thisLine = br.readLine();
				String[] newLine = thisLine.split("\t");
				myNS.addReview(Integer.parseInt(newLine[0]), Integer.parseInt(newLine[1]),
						Integer.parseInt(newLine[2]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Tests:

		// w.listData(myNS);
		while (mvm.getError(myNS) > 8500) {
			mvm.correct(myNS);
			// System.out.println(w.getError(myNS));
		}
		// w.list2D(w.recommend(myNS), myNS.getNumOfCustomers(), myNS.getNumOfBooks());

		double[][] temprec = mvm.recommend(myNS);
		for (int i = 0; i < myNS.getNumOfCustomers(); i++) {
			mvm.topN(i, temprec, myNS.getNumOfCustomers(), myNS.getNumOfBooks(), 10);
			System.out.println();
			// w.list2D(temprec, myNS.getNumOfCustomers(), myNS.getNumOfBooks());

		}

	}
}
