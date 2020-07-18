package Application;

import java.util.ArrayList;

import ObjectModel.ColumnObj;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AnimationSort 
{
	private Pane pane = new Pane();
	private ArrayList<ColumnObj> ListObj = new ArrayList<ColumnObj>();
	private int DurationTime = 2;
	
	public Pane getPane() {
		return pane;
	}
	
	public ArrayList<ColumnObj> getListObj() {
		return ListObj;
	}
	

	public int getDurationTime() {
		return DurationTime;
	}

	public void setDurationTime(int durationTime) {
		DurationTime = durationTime;
	}
	
	public AnimationSort() {
		
	}
	
	public AnimationSort(Pane pane, ArrayList<ColumnObj> listObj) 
	{
		this.pane = pane;
		this.ListObj = listObj;
		
	}
	
	public void Objpicked(ColumnObj X)
	{
		TranslateTransition transion = new TranslateTransition();
		transion.setDuration(Duration.seconds((float)DurationTime/4));
		transion.setToX(X.getXcurrent());
		transion.setToY(-50);
		transion.setAutoReverse(true);
		transion.setCycleCount(4);

		PauseTransition pause = new PauseTransition(Duration.seconds(DurationTime));
		
		X.addObjAnimation(transion, pause);
	}
	public void ObjPause(ColumnObj X, int timePause)
	{
//		X.addAnimaCol(new PauseTransition(Duration.seconds(timePause)));
//		X.addAnimaLab(new PauseTransition(Duration.seconds(timePause)));
		X.addObjAnimation(new PauseTransition(Duration.seconds(timePause)),new PauseTransition(Duration.seconds(timePause)) );
	}
	
	public void SwapUI(ColumnObj A,ColumnObj B)
	{
	
		//get rectangle of A
		TranslateTransition transion = new TranslateTransition();
		transion.setDuration(Duration.seconds(DurationTime));
		transion.setToX(A.getXcurrent()+ B.getxCol() - A.getxCol());
		transion.setToY(0);

		//get label of A
		TranslateTransition transion3 = new TranslateTransition();
		transion3.setDuration(Duration.seconds(DurationTime));
		transion3.setToX(A.getXcurrent()+ B.getxCol() - A.getxCol());
		transion3.setToY(0);
		
		A.setXcurrent(A.getXcurrent() + B.getxCol() - A.getxCol());
//		A.addAnimaCol(transion);
//		A.addAnimaLab(transion3);
		A.addObjAnimation(transion, transion3);
		//get rectangle of B
		TranslateTransition transion2 = new TranslateTransition();
		transion2.setDuration(Duration.seconds(DurationTime));
		transion2.setToX(B.getXcurrent()+ A.getxCol()-B.getxCol());
		transion2.setToY(0);

		//get label of B
		TranslateTransition transion4 = new TranslateTransition();
		transion4.setDuration(Duration.seconds(DurationTime));
		transion4.setToX(B.getXcurrent()+ A.getxCol() - B.getxCol());
		transion4.setToY(0);
		
		B.setXcurrent(B.getXcurrent()+ A.getxCol() - B.getxCol());
//		B.addAnimaCol(transion2);
//		B.addAnimaLab(transion4);
		B.addObjAnimation(transion2, transion4);
		int tmp = A.getxCol(); 
		A.setxCol(B.getxCol()); 
		B.setxCol(tmp);
	}
	
	public void PivotUPAnimation(ColumnObj X)
	{
		TranslateTransition transion = new TranslateTransition();
		transion.setDuration(Duration.seconds(DurationTime));
		transion.setToX(X.getXcurrent());
		transion.setToY(-50);
		FillTransition fill = new FillTransition();
		fill.setDuration(Duration.seconds(DurationTime));
		fill.setFromValue(Color.GRAY);
		fill.setToValue(Color.ORANGE);
		
		ParallelTransition parallel = new ParallelTransition();
		parallel.getChildren().addAll(transion,fill);
		
//		X.addAnimaCol(parallel);
		PauseTransition pause = new PauseTransition(Duration.seconds(DurationTime));
//		X.addAnimaLab(pause);
		X.addObjAnimation(parallel, pause);
	}
	
	public void PivotDownAnimation(ColumnObj X)
	{
		TranslateTransition transion = new TranslateTransition();
		transion.setDuration(Duration.seconds(DurationTime));
		transion.setToX(X.getXcurrent());
		transion.setToY(0);
		FillTransition fill = new FillTransition();
		fill.setDuration(Duration.seconds(DurationTime));
		fill.setFromValue(Color.ORANGE);
		fill.setToValue(Color.ORANGERED);
		
		ParallelTransition parallel = new ParallelTransition();
		parallel.getChildren().addAll(transion,fill);
		
//		X.addAnimaCol(parallel);
		PauseTransition pause = new PauseTransition(Duration.seconds(DurationTime));
//		X.addAnimaLab(pause);
		X.addObjAnimation(parallel, pause);
	} 
	
	private boolean isContain(int Arr[], int k)
	{
		for (int i = 0 ; i < Arr.length; i++)
		{
			if (k == Arr[i]) return true;
		}
		return false;
	}
	
	public void SetPauseTheRest( int... ExceptIndex)
	{
		
		for (int k =0; k < this.getListObj().size(); k++)
		{
			if(isContain(ExceptIndex, k) == false)
			{
				ObjPause(this.getListObj().get(k), DurationTime);
			}
		}
	}
	
    public void swap(int i, int j)
	{
		ColumnObj col1 = this.getListObj().get(i);
		ColumnObj col2 = this.getListObj().get(j);
		SwapUI(col1, col2);
		
		this.getListObj().set(i, col2);
		this.getListObj().set(j, col1);
		SetPauseTheRest(i,j);
		
	}
	
    public void sort() {
		
	}
}