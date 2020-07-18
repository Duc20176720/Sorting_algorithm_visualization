package Application;

import ObjectModel.ColumnObj;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import Controller.InputControl;

public class ThreeWayRadixSort extends AnimationSort implements ColumnColor {

    public ThreeWayRadixSort(Pane P, ArrayList<ColumnObj> Data) {
        super(P, Data);
    }
    
    public void sort() {
    	int numberCharacter = InputControl.getNumberCharacters(this.getListObj());

        radixSort3Way(0, this.getListObj().size() - 1, 0, numberCharacter);
        for (ColumnObj columnObj : this.getListObj()) {
            System.out.printf(columnObj.getValue() + " ");
        }
    }

    private void radixSort3Way(int low, int high, int index, int numberCharacter) {
        System.out.println("low: " + low + " high: " + high + " index: " + index);
        if (high <= low || index == numberCharacter) return;
        int lt = low, gt = high;
        int pivotIndex = low;
        char pivot = InputControl.toStringColumnHeight(this.getListObj().get(low).getValue(), numberCharacter).charAt(index);
        System.out.println("pivot: " + this.getListObj().get(low).getValue() + " height " + InputControl.toStringColumnHeight(this.getListObj().get(low).getValue(), numberCharacter) + " index: " + index + "\n");
        setColorForCurrentArray( low, high, Color.LIGHTPINK);
        PivotUPAnimation(this.getListObj().get(low));
        SetPauseTheRest(low);
        int i = low + 1;
        while (i <= gt) {
            Objpicked(this.getListObj().get(i));
            SetPauseTheRest(i);
            char value = InputControl.toStringColumnHeight(this.getListObj().get(i).getValue(), numberCharacter).charAt(index);
            if (value < pivot) {
                PivotDownAnimation(this.getListObj().get(pivotIndex));
                SetPauseTheRest(pivotIndex);
                if (i != lt)
                    swap( i, lt);

                i++;
                lt++;
                pivotIndex = lt;
                PivotUPAnimation(this.getListObj().get(pivotIndex));
                SetPauseTheRest( pivotIndex);
            } else if (value > pivot) {
                if (i != gt)
                    swap( i, gt);
                gt--;
            } else i++;
        }
        PivotDownAnimation(this.getListObj().get(pivotIndex));
        SetPauseTheRest( pivotIndex);
        setColorForCurrentArray( low, high, Color.GREY);

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        radixSort3Way( low, lt - 1, index, numberCharacter);
        radixSort3Way( lt, gt, index + 1, numberCharacter);
        radixSort3Way( gt + 1, high, index, numberCharacter);

    }

    private void setColorForCurrentArray( int lt, int gt, Color color) {
        List<Integer> listException = new ArrayList<Integer>();
        for (int i = lt; i <= gt; i++) {
            setColumnColor(this.getListObj().get(i), color);
            listException.add(i);
        }
        int[] arr = listException.stream().mapToInt(i -> i).toArray();

        SetPauseTheRest( arr);


    }

    @Override
    public void setColumnColor(ColumnObj columnObj, Color color) {
        FillTransition fill = new FillTransition();
        fill.setDuration(Duration.seconds(2));
        fill.setToValue(color);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        columnObj.addObjAnimation(fill, pause);
    }
}