package Application;

import java.util.ArrayList;

import ObjectModel.ColumnObj;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class QuickSort extends AnimationSort
{	
//	SequentialTransition
	
	public void Objpicked(ColumnObj X, Color color)
	{
		TranslateTransition transion = new TranslateTransition();
		transion.setDuration(Duration.seconds((float)this.getDurationTime()/4));
		transion.setToX(X.getXcurrent());
		transion.setToY(-50);
		transion.setAutoReverse(true);
		transion.setCycleCount(4);
		FillTransition fill = new FillTransition();
		fill.setDuration(Duration.seconds((float)this.getDurationTime()/4));
		fill.setCycleCount(4);
		fill.setAutoReverse(true);
		fill.setToValue(color);
		
		ParallelTransition para = new ParallelTransition();
		para.getChildren().addAll(transion,fill);
		
		PauseTransition pause = new PauseTransition(Duration.seconds(this.getDurationTime()));
		X.addObjAnimation(para, pause);
	}
	
	private Label Note(String text, String color, int x, int y)
	{
		Label lab = new Label(text);
		lab.setAlignment(Pos.CENTER);
		lab.setTextFill(Color.WHITE);
		lab.setMinHeight(30);
		lab.setMinWidth(50);
		lab.setLayoutX(x);
		lab.setLayoutY(y);
		lab.setStyle("-fx-background-color:" + color);
		return lab;
	}

	private int partition (int low, int high)
	{
		int P  = this.getListObj().get(high).getValue();
		PivotUPAnimation(this.getListObj().get(high));
		SetPauseTheRest(high);
		int L  = low;
		int R  = high-1;
		while (true)
		{
			while (L <= R ) 
			{
				Objpicked(this.getListObj().get(L),Color.BLUE);
				SetPauseTheRest(L);
				
				if (this.getListObj().get(L).getValue() >= P) break;
				L++;
			}
			while (R >= L ) 
			{
				Objpicked(this.getListObj().get(R),Color.RED);
				SetPauseTheRest(R);
				
				if (this.getListObj().get(R).getValue() <= P) break;
				R--;
			}
			if (L >= R) 
			{
				PivotDownAnimation(this.getListObj().get(high));
				SetPauseTheRest(high);
				break;
			}
			swap(L,R);
			L++;
			R--;
		}
		swap(L,high);
		return L;
	}
	
	public void quicksort(int low, int high )
	{
		if(low < high)
		{
			int w = partition(low,high);
			quicksort(low, w -1);
			quicksort(w+1, high);
		}
	}
	
	public QuickSort(Pane P, ArrayList<ColumnObj> Data)
	{
		super(P, Data);
		
		
	}
	
	@Override
	public void sort() {
		Label I = Note("i-->","BLUE",10,10);
		Label J = Note("<--j","RED",65,10);
		Label Piv = Note("Pivot","ORANGE",120,10);
		this.getPane().getChildren().addAll(I,J,Piv);
		quicksort(0,this.getListObj().size()-1);
	}
	
}