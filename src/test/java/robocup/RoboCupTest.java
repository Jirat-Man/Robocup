package robocup;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class RoboCupTest {

    private static final GUI UI = new GUI() ;
    Pitch pitch = new Pitch(UI);

/*    @Test*/
/*    public void playerEnergyShouldStayTheSameIfUPandThenDown() {
        int energy = pitch.player1.getM_energy();
        pitch.player1.energyUp();
        pitch.player1.energyDown();
        assertEquals(energy, pitch.player1.getM_energy());
    }*/

    @Test
    public void ballShouldMoveWithNoneZeroSpeed() {
        BigDecimal x = new BigDecimal(pitch.ball.getPositionX());
        BigDecimal y = new BigDecimal(pitch.ball.getPositionY());
        pitch.ball.setSpeed(10, 10);
        pitch.ball.move();
        assertNotEquals(x, new BigDecimal(pitch.ball.getPositionX()));
        assertNotEquals(y, new BigDecimal(pitch.ball.getPositionY()));
    }

    @Test
    public void ballShouldNotMoveWithZeroSpeed() {
        BigDecimal x = new BigDecimal(pitch.ball.getPositionX());
        BigDecimal y = new BigDecimal(pitch.ball.getPositionY());
        pitch.ball.setSpeed(0, 0);
        pitch.ball.move();
        assertEquals(x, new BigDecimal(pitch.ball.getPositionX()));
        assertEquals(y, new BigDecimal(pitch.ball.getPositionY()));
    }

    @Test
    public void ballShouldMoveOnlyX() {
        BigDecimal x = new BigDecimal(pitch.ball.getPositionX());
        BigDecimal y = new BigDecimal(pitch.ball.getPositionY());
        pitch.ball.setSpeed(10, 0);
        pitch.ball.move();
        assertNotEquals(x, new BigDecimal(pitch.ball.getPositionX()));
        assertEquals(y, new BigDecimal(pitch.ball.getPositionY()));
    }

    @Test
    public void ballShouldMoveOnlyY() {
        BigDecimal x = new BigDecimal(pitch.ball.getPositionX());
        BigDecimal y = new BigDecimal(pitch.ball.getPositionY());
        pitch.ball.setSpeed(0, 10);
        pitch.ball.move();
        assertEquals(x, new BigDecimal(pitch.ball.getPositionX()));
        assertNotEquals(y, new BigDecimal(pitch.ball.getPositionY()));
    }

/*
    @Test
    public void playerShouldMoveWithNoneZeroSpeed() {
        BigDecimal x = new BigDecimal(pitch.player1.getPositionX());
        BigDecimal y = new BigDecimal(pitch.player1.getPositionY());
        pitch.player1.setM_speed(10, 10);
        pitch.player1.move();
        assertNotEquals(x, new BigDecimal(pitch.player1.getPositionX()));
        assertNotEquals(y, new BigDecimal(pitch.player1.getPositionY()));
    }

    @Test
    public void playerShouldNotMoveWithZeroSpeed() {
        BigDecimal x = new BigDecimal(pitch.player1.getPositionX());
        BigDecimal y = new BigDecimal(pitch.player1.getPositionY());
        pitch.player1.setM_speed(0, 0);
        pitch.player1.move();
        assertEquals(x, new BigDecimal(pitch.player1.getPositionX()));
        assertEquals(y, new BigDecimal(pitch.player1.getPositionY()));
    }

    @Test
    public void playerShouldMoveOnlyX() {
        BigDecimal x = new BigDecimal(pitch.player1.getPositionX());
        BigDecimal y = new BigDecimal(pitch.player1.getPositionY());
        pitch.player1.setM_speed(10, 0);
        pitch.player1.move();
        assertNotEquals(x, new BigDecimal(pitch.player1.getPositionX()));
        assertEquals(y, new BigDecimal(pitch.player1.getPositionY()));
    }

    @Test
    public void playerShouldMoveOnlyY() {
        BigDecimal x = new BigDecimal(pitch.player1.getPositionX());
        BigDecimal y = new BigDecimal(pitch.player1.getPositionY());
        pitch.player1.setM_speed(0, 10);
        pitch.player1.move();
        assertEquals(x, new BigDecimal(pitch.player1.getPositionX()));
        assertNotEquals(y, new BigDecimal(pitch.player1.getPositionY()));
    }

    @Test
    public void playerShouldMoveRightAndDown() {
        BigDecimal x = new BigDecimal(pitch.player1.getPositionX());
        BigDecimal y = new BigDecimal(pitch.player1.getPositionY());
        pitch.player1.setM_speed(10, 10);
        pitch.player1.move();
        assertTrue(x.compareTo(new BigDecimal(pitch.player1.getPositionX())) < 0 );
        assertTrue(y.compareTo(new BigDecimal(pitch.player1.getPositionY())) < 0 );
    }

    @Test
    public void playerShouldComeBackWhereHeWasAfterTurning180degrees() {
        BigDecimal delta = new BigDecimal(2);
        BigDecimal x = new BigDecimal(pitch.player1.getPositionX());
        BigDecimal y = new BigDecimal(pitch.player1.getPositionY());
        pitch.player1.setM_speed(10, 10);
        pitch.player1.move();
        pitch.player1.turn(180);
        pitch.player1.move();
        BigDecimal newX = new BigDecimal(pitch.player1.getPositionX());
        BigDecimal newY = new BigDecimal(pitch.player1.getPositionY());
        assertTrue(x.subtract(newX).abs().compareTo(delta) < 0);
        assertTrue(y.subtract(newY).abs().compareTo(delta) < 0);
    }

    @Test
    public void ballShouldMoveWithNoneZeroSpeedAfterKick() {
        BigDecimal x = new BigDecimal(pitch.ball.getSpeedX());
        BigDecimal y = new BigDecimal(pitch.ball.getSpeedY());
        pitch.player1.kick(pitch.ball, 30);
        assertNotEquals(x, new BigDecimal(pitch.ball.getSpeedX()));
    }
*/



}
