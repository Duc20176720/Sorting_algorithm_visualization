package Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;

import ObjectModel.ColumnObj;

import static sun.swing.MenuItemLayoutHelper.max;

public class InputControl 
{
	public static boolean TestInput(String str)
	{
		if(str.isEmpty()) 
		{
			Alert alt = new Alert(AlertType.WARNING);
			alt.setContentText("Please! Enter some number.");
			alt.show();
			return false;
		}
		int L = str.length();
		for (int i = 0; i < L ; i++)
		{
			if (str.charAt(i) != ' ' && Character.isDigit(str.charAt(i)) == false )
			{
				Alert alt = new Alert(AlertType.ERROR);
				alt.setContentText("Each number must be separated by spaces\nNo specific charecters in the list number!");
				alt.show();
				return false;
			}
		}
		
		return true;
		
	}
	
	public static int[] toArray(String str)
	{
		str = str.trim();
		str = str.replaceAll(" +", " ");
		
		String x[] = str.split(" ");
		
		int[] arr = new int[x.length];
		for(int i =0; i < x.length; i++)
		{
			{
				arr[i] = Integer.parseInt(x[i]);		
			}
		}
		return arr;
	}
	

	public static int getNumberCharacters(ArrayList<ColumnObj> columnObjList) {
		int maxNumber = 0, numberCharacters = 0;
		for (int i = 0; i<columnObjList.size(); i++) {
			int height = columnObjList.get(i).getValue();
			maxNumber = max(maxNumber, height);
			numberCharacters = ("" + maxNumber).length();
		}
		return numberCharacters;
	}

	public static String toStringColumnHeight(int height, int numberCharacters){
		StringBuilder res = new StringBuilder();
		res = new StringBuilder("" + height);
		while (res.length() < numberCharacters) {
			res.insert(0, "0");
		}
		return res.toString();
	}
}