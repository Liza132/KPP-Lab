package lab1;

public class Tool {
    private String material;

    private String origin;

    private String model;

    private int handy;

    private Specifications specifications;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHandy() {
        return handy;
    }

    public void setHandy(int handy) {
        this.handy = handy;
    }

    public Specifications getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "Tool [ model = " + model + ", handy = " + handy + ", origin = " + origin + ", specifications = " + specifications + ", material = " + material + " ]";
    }
}
