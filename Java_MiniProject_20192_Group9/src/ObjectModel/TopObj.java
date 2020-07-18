package ObjectModel;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TopObj 
{
	private int width;
	private int height;
	private String idCol;
	private int LayoutX;
	private int LayoutY;
	private Color color = Color.DARKGRAY; 
	
	private ArrayList<Animation> AnimaCol = new ArrayList<Animation>();
	private SequentialTransition SeqAnimaCol = new SequentialTransition();
	
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public ArrayList<Animation> getAnimaCol() {
		return AnimaCol;
	}
	public void setAnimaCol(ArrayList<Animation> animaCol) {
		AnimaCol = animaCol;
	}
	public void addAnimaCol(Animation animation) {
		this.AnimaCol.add(animation);
	}
	public SequentialTransition getSeqAnimaCol()
	{
		return SeqAnimaCol;
	}
	
	public String getIdCol() {
		return "#".concat(idCol);
	}
	public void setIdCol(String idCol) {
		this.idCol = idCol;
	}
	
	public Rectangle rectangle()
	{
		Rectangle rec = new Rectangle();
		rec.setWidth(this.width);
		rec.setHeight(this.height);
		rec.setLayoutX(this.LayoutX);
		rec.setLayoutY(this.LayoutY);
		rec.setId(this.idCol);
		rec.setFill(color);
		return rec;
	}
	public Rectangle getRectangle(Pane parent)
	{
		return ((Rectangle)parent.lookup(this.getIdCol()));
	}
	
	public void BeginPlayAnimation(Pane parent)
	{
		Rectangle rec = this.getRectangle(parent); 
		SeqAnimaCol.setNode(rec);
		SeqAnimaCol.getChildren().addAll(this.getAnimaCol());
		SeqAnimaCol.play();
	
	}
	
	public void PauseAnimation()
	{
		SeqAnimaCol.pause();
	}
	public void PlayAnimation()
	{
		SeqAnimaCol.play();
	}
	public TopObj(int width, int height, String idCol, int layoutX, int layoutY) {
		
		this.width = width;
		this.height = height;
		this.idCol = idCol;
		LayoutX = layoutX;
		LayoutY = layoutY;
	}
	public TopObj() {
		// TODO Auto-generated constructor stub
	}

}