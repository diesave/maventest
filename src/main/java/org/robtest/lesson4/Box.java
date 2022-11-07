package org.robtest.lesson4;

public class Box {
    private Integer ballCount;

    public Box() {
        ballCount = 0;
    }

    public Integer getBallCount() {
        return ballCount;
    }

    public void addBall() {
        this.ballCount++;
    }

    public void deleteBall() throws BallCountException {
        if (this.ballCount == 0)
            throw new BallCountException();
        this.ballCount--;
    }
}