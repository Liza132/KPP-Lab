package lab1;

public class Specifications {
    private boolean autonomous;

    private int power;

    private String energy;

    public boolean getAutonomous () {
        return autonomous;
    }

    public void setAutonomous (boolean autonomous) {
        this.autonomous = autonomous;
    }

    public int getPower () {
        return power;
    }

    public void setPower (int power) {
        this.power = power;
    }

    public String getEnergy () {
        return energy;
    }

    public void setEnergy (String energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "[ autonomous = " + autonomous + ", power = " + power + ", energy = " + energy + " ]";
    }
}
