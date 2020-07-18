package Controller;


import java.util.ArrayList;

import Application.AnimationSort;
import Application.QuickSort;
import Application.RadixSort;
import Application.ThreeWayRadixSort;
import ObjectModel.ColumnObj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Controller {
	@FXML
	private TextField dayso;
	@FXML
	private Pane paneResult;
	@FXML
	private MenuButton menuButton;
	@FXML
	private Label title;
	@FXML
	private ImageView bgr;
	@FXML
	private Button PlayPauseBtn;
	
	private int MaxY = 500;
	private int MaxX = 960;
	private int widthcol = 50;
	private int spaceCol = 3;
	private ArrayList<ColumnObj> ListObj = new ArrayList<ColumnObj>();
	private int option;
	private int status;
	
	AnimationSort aS = new AnimationSort();
		
	public void Run(ActionEvent e )
	{
		bgr.setVisible(false);
		paneResult.getChildren().clear();
		ListObj.clear();
		String arrInt = dayso.getText();
		
		if (InputControl.TestInput(arrInt) == false) dayso.setText("");
		else 
		{	
			
			int[] A =InputControl.toArray(arrInt);
			int x = (MaxX-(A.length*widthcol + (A.length-1)*spaceCol))/2;
			for (int i=0; i<A.length;i++)
			{
				ColumnObj col = new ColumnObj(paneResult,x, MaxY-A[i], widthcol, A[i], String.valueOf(i));
				ListObj.add(col);
				x = x + widthcol + spaceCol;
				col.GoToView();
			}
			
			if(option == 1) {
				aS = new QuickSort(paneResult, ListObj);
			}
			if(option == 2) {
				aS = new RadixSort(paneResult, ListObj);
			}
			if(option == 3) {
				aS = new ThreeWayRadixSort(paneResult, ListObj);
			}
			
			aS.sort();
			
			for (int i=0; i<ListObj.size();i++)
			{
				ColumnObj col =  ListObj.get(i);
				col.BeginPlayAnimationColumn();
			}
			
			ColumnObj checkDone = ListObj.get(ListObj.size()-1);
			System.out.println("Check done: "+checkDone.getValue());
			checkDone.getTopSeqAnimation().setOnFinished(action->{
				for(ColumnObj c:ListObj) {
					c.getTopObj().setFill(Color.CHOCOLATE);
				}
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("DONE");
				alert.show();
			});
			
			status = 0;
			PlayPauseBtn.setVisible(true);
			PlayPauseBtn.setOnAction( evt -> {
				if(status == 0)
		    	{
		    		
					for (int i=0; i< ListObj.size();i++)
					{
						
						ColumnObj col =  ListObj.get(i);
						col.Pause();
					}
					PlayPauseBtn.setText("Continous");
		    		status = 1;
		    	}else {
		    		for (int i=0; i< ListObj.size();i++)
					{
						
						ColumnObj col =  ListObj.get(i);
						col.Play();
					}
		    		PlayPauseBtn.setText("Pause");
		    		status = 0;
		    	}
			});
		}
	}
	public void action1(ActionEvent e) {
		menuButton.setText("Quick Sort");
		title.setText("Quick Sort Algorithm");
		option = 1;
	}
	public void action2(ActionEvent e) {
		menuButton.setText("Radix Sort");
		title.setText("Radix Sort Algorithm");
		option = 2;
	}
	public void action3(ActionEvent e) {
		menuButton.setText("3_Way Radix Sort");
		title.setText("3 Way Radix Sort Algorithm");
		option = 3;
	}
}
