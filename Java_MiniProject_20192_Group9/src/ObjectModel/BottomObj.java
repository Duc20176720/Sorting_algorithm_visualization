package ObjectModel;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class BottomObj 
{
	
	private int Number;
	private int height = 30;
	private int width;
	private int LayoutX;
	private int LayoutY = 503;
	private String style = "-fx-background-color:LIGHTGRAY";
	private String idlabel;
	
	private ArrayList<Animation>  AnimaLab = new ArrayList<Animation>();
	private SequentialTransition SeqAnimaLab = new SequentialTransition();
	
	public ArrayList<Animation> getAnimaLab() {
		return AnimaLab;
	}
	public void setAnimaLab(ArrayList<Animation> animaLab) {
		AnimaLab = animaLab;
	}
	public void addAnimaLab(Animation animation) {
		this.AnimaLab.add(animation);
	}
	
	public String getIdlabel() {
		return "#".concat(idlabel);
	}
	public void setIdlabel(String idlabel) {
		this.idlabel = idlabel;
	}
	
	
	
	public Label label() 
	{
		
		Label lab = new Label(String.valueOf(Number));
		lab.setId(this.idlabel);
		lab.setAlignment(Pos.CENTER);
		lab.setMinHeight(height);
		lab.setMinWidth(width);
		lab.setLayoutX(LayoutX);
		lab.setLayoutY(LayoutY);
		lab.setStyle(style);
		return lab;
	}
	
	public Label getLabel(Pane parent)
	{
		return ((Label)parent.lookup(this.getIdlabel()));
	}
	
	public void BeginPlayAnimation(Pane parent)
	{
		Label rec = this.getLabel(parent); 
		SeqAnimaLab.setNode(rec);
		SeqAnimaLab.getChildren().addAll(this.getAnimaLab());
		SeqAnimaLab.play();
	
	}
	public void PauseAnimation()
	{
		SeqAnimaLab.pause();
	}
	public void PlayAnimation()
	{
		SeqAnimaLab.play();
	}
	
	
	public BottomObj(int number, int width, int layoutX, String idlabel) {
		super();
		Number = number;
		this.width = width;
		LayoutX = layoutX;
		this.idlabel = idlabel;
	}
	public BottomObj() {
		// TODO Auto-generated constructor stub
	}

}