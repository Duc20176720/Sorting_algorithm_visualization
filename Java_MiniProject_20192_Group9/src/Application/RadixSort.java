package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ObjectModel.ColumnObj;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class RadixSort extends AnimationSort implements ColumnColor{
	
	private int[] xColumn = new int[100];
	private int max;
	
	private int getMax() {
		max = this.getListObj().get(0).getValue();
		for(int i = 1; i<this.getListObj().size(); i++) {
			if(this.getListObj().get(i).getValue()> max)
				max = this.getListObj().get(i).getValue();
		}
		return max;
	}
	
	private void getXColumn() {
		for(int i = 0; i<this.getListObj().size();i++) {
			
			xColumn[i]= this.getListObj().get(i).getxCol();
		}
	}
	
	private void moveObj(ColumnObj X, int newX) {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(1));
		transition.setToX(newX);
		transition.setToY(-200);
		
		TranslateTransition transition1 = new TranslateTransition();
		transition1.setDuration(Duration.seconds(1));
		transition1.setToX(newX);
		transition1.setToY(-200);
		
		
		X.addObjAnimation(transition, transition1);
		X.setXcurrent(newX);
	}
	
	@Override
	public void setColumnColor(ColumnObj X, Color color) {
		FillTransition transition = new FillTransition();
		transition.setDuration(Duration.seconds(1));
		transition.setToValue(color);
		
//		X.addAnimaCol(transition);
//		X.addAnimaLab(new PauseTransition(Duration.seconds(1)));
		X.addObjAnimation(transition, new PauseTransition(Duration.seconds(1)));
	}
	

	private void down(ColumnObj X) {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(1));
		transition.setToY(0);
		
		TranslateTransition transition1 = new TranslateTransition();
		transition1.setDuration(Duration.seconds(1));
		transition1.setToY(0);
		
		X.addObjAnimation(transition, transition1);
	}
	
	private void countSort(int exp) {
		List<Integer> listNumber = new ArrayList<Integer>();
		ArrayList<ColumnObj> ListObjNew = new ArrayList<ColumnObj>();
		
		for(int i = 0; i < this.getListObj().size();i++) {
			int x = (this.getListObj().get(i).getValue()/exp)%10;
			if(listNumber.contains(x)==false)
				listNumber.add(x);
		}
		
		java.util.Collections.sort(listNumber);
		
		int x=0;
		for(Integer i: listNumber) {
			Random ran = new Random();
			Color color = Color.rgb(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255));
			for(ColumnObj c: this.getListObj()) {
				if((c.getValue()/exp)%10 == i) {
					
					Objpicked(c);
					moveObj(c, xColumn[x]-c.getxCol());
					setColumnColor(c, color);
					ListObjNew.add(x, c);
					
					x++;
				}
				else {
					ObjPause(c, 4);
				}
			}
			
		}
		for(ColumnObj c: ListObjNew) {
			down(c);
		}
		listNumber.clear();
		this.getListObj().clear();
		this.getListObj().addAll(ListObjNew);
	}
	
	
	public RadixSort(Pane pane, ArrayList<ColumnObj> ListObj) {
		super(pane,ListObj);
		
	}
	@Override
	public void sort() {
		getXColumn();
		getMax();

		for(int exp =1; max/exp >0; exp *=10) {
			countSort(exp);
		}
		for(ColumnObj c: this.getListObj())
			System.out.println(c.getValue());
		//countSort(1);
	}
}
