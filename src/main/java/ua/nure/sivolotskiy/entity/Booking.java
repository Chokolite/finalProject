package ua.nure.sivolotskiy.entity;

public class Booking extends Entity{
    private Trip trip;
    private Entity user;
    private String vehicleSpecification;

public Booking(){
}
public Booking(Long id){
    setId(id);
}
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Entity getUser() {
        return user;
    }

    public void setUser(Entity user) {
        this.user = user;
    }

    public String getVehicleSpecification() {
        return vehicleSpecification;
    }

    public void setVehicleSpecification(String vehicleSpecification) {
        this.vehicleSpecification = vehicleSpecification;
    }
}
