public class SafeHouse extends NormalLoc{

    SafeHouse(Player player){
        super(player, "Güvenli ev");
    }

    public boolean getLocation(){
        player.setHealthy(player.getrHealthy());
        System.out.println("Sağlığınız yenilendi");
        System.out.println("Şuan güvenli evdesiniz.");
        return true;
    }
}
