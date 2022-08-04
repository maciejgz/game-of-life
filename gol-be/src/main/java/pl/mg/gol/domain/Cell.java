package pl.mg.gol.domain;

import lombok.Data;

@Data
public class Cell {

    private boolean status;
    private boolean nextStatus;
    private boolean position;

    public void nextTurn() {
        this.status = this.nextStatus;
    }

}
