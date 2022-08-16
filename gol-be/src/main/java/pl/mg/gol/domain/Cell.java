package pl.mg.gol.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Cell {

    private boolean status;
    private boolean nextStatus;
    private Position position;

    public void nextTurn() {
        this.status = this.nextStatus;
        this.nextStatus = false;
    }

    public void calculateNextState(List<Cell> neighbours) {
        int livingNeighbours = 0;
        if (neighbours != null) {
            for (Cell neighbour : neighbours) {
                if (neighbour.status) {
                    livingNeighbours++;
                }
            }
        }

        nextStatus = livingNeighbours != 0 && livingNeighbours != 1 && livingNeighbours < 4;
    }
}
