package it.must.be.funny.creational.builder;

public class House {
    //required
    private int floors;
    private String houseType;

    //optional
    private boolean hasGarage;
    private Integer member;

    private House(HouseBuilder builder){
        this.floors = builder.floors;
        this.houseType = builder.houseType;
        this.hasGarage = builder.hasGarage;
        this.member = builder.member;
    }

    public int getFloors() {
        return floors;
    }

    public String getHouseType() {
        return houseType;
    }

    public boolean isHasGarage() {
        return hasGarage;
    }

    public Integer getMember() {
        return member;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "House{" +
                "floors=" + floors +
                ", houseType='" + houseType + '\'' +
                ", hasGarage=" + hasGarage +
                ", member=" + member +
                '}';
    }

    //builder class
    public static class  HouseBuilder {
        //required
        private int floors;
        private String houseType;

        //optional
        private boolean hasGarage;
        private Integer member;

        public HouseBuilder(int floors, String houseType){
            this.floors = floors;
            this.houseType = houseType;
        }
        public HouseBuilder setGarage(boolean hasGarage){
            this.hasGarage = hasGarage;
            return this;
        }
        public HouseBuilder setMember(Integer member){
            this.member = member;
            return this;
        }
        public House build(){
            return new House(this);
        }
    }

}

