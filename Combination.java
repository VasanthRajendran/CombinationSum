package readFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Combination {

	public void combinationSum(ArrayList<Double> listArray, double target) {
		ArrayList<Double> current = new ArrayList<Double>();
		double[] list = new double[listArray.size()];
		for(int i=0;i<listArray.size();i++)
		{
			list[i] = listArray.get(i);
		}
		Arrays.sort(list);;
		combinationSum(list, target, 0, current);
	}

	public void combinationSum(double[] prices, double target, int j, ArrayList<Double> current) {
		if (target == 0) {
			System.out.println("The combination is ");
			for (int i = 0; i < current.size(); i++) {
				System.out.print( current.get(i) + " ");
			}
			System.out.println();
			return;
		}
		for (int i = j; i < prices.length; i++) {
			if (target < prices[i]) {
				return;
			}
			current.add(prices[i]);
			Double newTarget = target - prices[i];
			double roundOffTarget = Math.round(newTarget * 100.0) / 100.0;
			combinationSum(prices, roundOffTarget,i, current);
			current.remove(current.size() - 1);
		}
	}

	public static void main(String args[]) {
		Combination c = new Combination();
		File file = new File("/Users/vasanthrajendran/eclipse-workspace/Webley/src/readFile/Price.csv");
		Double totalPrice;
		ArrayList<Double> list = new ArrayList<Double>();
		try {
			Scanner inputStream = new Scanner(file);
			String targetValue = inputStream.nextLine();
			String[] targetValuePrice = targetValue.split(",");
			String test = targetValuePrice[1].substring(1);
			totalPrice = Double.parseDouble(test);
			while (inputStream.hasNext()) {
				String dish = inputStream.nextLine();
				String[] dishPrice = dish.split(",");
				String Price = dishPrice[1].substring(1);
				list.add(Double.parseDouble(Price));
			}
			System.out.println("Total Price is " + totalPrice);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			c.combinationSum(list, totalPrice);
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
