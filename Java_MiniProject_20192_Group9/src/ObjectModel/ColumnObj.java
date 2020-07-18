package ObjectModel;


import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Label;

import javafx.scene.layout.Pane;

import javafx.scene.shape.Rectangle;


public class ColumnObj 
{
	private Pane parent;
	private int Xcurrent;
	private int xCol;
	private int yCol;
	private TopObj topObj;
	private BottomObj bottomObj;
	private int value;
	
	public int getValue() {
		return value;
	}
	
	public void setXcurrent(int value) {
		this.Xcurrent = value;
	}
	
	public Pane getParent() {
		return parent;
	}

	public void setParent(Pane parent) {
		this.parent = parent;
	}



	public int getXcurrent() {
		return Xcurrent;
	}
	
	public int getxCol() {
		return xCol;
	}
	public void setxCol(int xCol) {
		this.xCol = xCol;
	}
	public int getyCol() {
		return yCol;
	}
	public void setyCol(int yCol) {
		this.yCol = yCol;
	}
	
	
	
	
	public ColumnObj(Pane parent,int xCol, int yCol, int width, int value, String idCol) {
		
		this.parent = parent;
		this.xCol = xCol;
		this.yCol = yCol;
		this.topObj = new TopObj(width, value, idCol, xCol, yCol);
		this.bottomObj = new BottomObj(value, width, xCol ,"lab".concat(idCol));
		this.Xcurrent = 0;
		this.value = value;
	}
	public void GoToView()
	{
		Rectangle rec = topObj.rectangle();
		Label lab = bottomObj.label();
		this.parent.getChildren().addAll(rec,lab);
	}
	public Rectangle getTopObj()
	{
		return this.topObj.getRectangle(this.parent);
	}
	public Label getBottomObj()
	{
		return this.bottomObj.getLabel(this.parent);
	}
	
	public void addObjAnimation(Animation top, Animation bot)
	{
		this.topObj.addAnimaCol(top);
		this.bottomObj.addAnimaLab(bot);
	}
	
	public void BeginPlayAnimationColumn()
	{
		this.topObj.BeginPlayAnimation(this.parent);
		this.bottomObj.BeginPlayAnimation(this.parent);
	}
	public void Pause()
	{
		this.topObj.PauseAnimation();;
		this.bottomObj.PauseAnimation();
	}
	public void Play()
	{
		this.topObj.PlayAnimation();
		this.bottomObj.PlayAnimation();
	}
	public SequentialTransition getTopSeqAnimation()
	{
		return this.topObj.getSeqAnimaCol();
	}
	
	public ColumnObj()
	{
		
	}
}