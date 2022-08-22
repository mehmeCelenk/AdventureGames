import java.util.Random;

public abstract class BattleLoc extends Location{
    protected  Obstacle obstacle;
    protected String award;

    BattleLoc(Player player, String name, Obstacle obstacle, String award){
        super(player);
        this.obstacle = obstacle;
        this.name = name;
        this.award = award;
    }

    public boolean getLocation(){
        int obsCount = obstacle.count();
        System.out.println("Şuan burdasınız : " + this.getName());
        System.out.println("Dikkatli ol Burada " + obsCount + " tane " + obstacle.getName() + " yaşıyor !");
        System.out.print("<S>avaş veya <K>aç :");
        String selCase = scan.nextLine();
        selCase = selCase.toUpperCase();
        if(selCase.equals("S")){
            if(combat(obsCount)){
                System.out.println(this.getName() + " Bölgesindeki tüm düşmanları temizlediniz !");
                if(this.award.equals("Food") && player.getInv().isFood() == false){
                    System.out.println(this.award + " Kazandınız !");
                    player.getInv().setFood(true);
                } else if (this.award.equals("Water") && player.getInv().isWater() == false) {
                    System.out.println(this.award + " Kazandınız !");
                    player.getInv().setWater(true);
                } else if (this.award.equals("Firewood") && player.getInv().isFirewood() == false) {
                    System.out.println(this.award + " Kazandınız");
                    player.getInv().setFirewood(true);
                }
                return true;
            }
            if(player.getHealthy() <= 0){
                System.out.println("maalesef Öldünüz ");
                return false;
            }
        }
        return true;
    }

    public boolean combat(int obsCount){
        for(int i = 0; i < obsCount; i++){
            int defObsHealthy = obstacle.getHealthy();
            playerStats();
            enemyStats();
            while (player.getHealthy() > 0 && obstacle.getHealthy() > 0){
                System.out.print("<V>ur veya <K>aç :");
                String selCase = scan.nextLine();
                selCase = selCase.toUpperCase();
                int chance = Math.round((float) Math.random());
                System.out.println("chance :" + chance);
                if(selCase.equals("V")){
                    if(chance == 0){
                        System.out.println("Siz vurdunuz !");
                        obstacle.setHealthy(obstacle.getHealthy() - player.getTotalDamage());
                        afterHit();
                    }
                    if(obstacle.getHealthy() > 0){
                        if(chance == 1){
                            System.out.println();
                            System.out.println("Canavar size vurdu");
                            player.setHealthy(player.getHealthy() - (obstacle.getDamage() - player.getInv().getArmorDefence()));
                            afterHit();
                        }
                    }
                }else {
                    return false;
                }
            }
            if(obstacle.getHealthy() < player.getHealthy()){
                System.out.println("Düşmanı yendiniz !");
                player.setMoney(player.getMoney() + obstacle.getAward());
                System.out.println("Güncel paranız :" + player.getMoney());
                obstacle.setHealthy(defObsHealthy);
            }else {
                return false;
            }
            System.out.println("----------------------");
        }
        return true;
    }

    public void playerStats(){
        System.out.println("Oyuncu değerleri\n-------------------");
        System.out.println("Can :" + player.getHealthy());
        System.out.println("Hasar :" + player.getTotalDamage());
        System.out.println("Para :" + player.getMoney());
        if(player.getInv().getWeaponDamage() > 0){
            System.out.println("Silah :" + player.getInv().getWeaponName());
        }
        if(player.getInv().getArmorDefence() > 0){
            System.out.println("Zırh :" + player.getInv().getArmorName());
        }
    }

    public void enemyStats(){
        System.out.println("\n" + obstacle.getName() + " Değerleri\n---------------");
        System.out.println("Can :" + obstacle.getHealthy());
        System.out.println("Hasar :" + obstacle.getDamage());
        System.out.println("Ödül :" + obstacle.getAward());
    }

    public void afterHit(){
        System.out.println("Oyuncu canı :" + player.getHealthy());
        System.out.println(obstacle.getName() + " Canı :" + obstacle.getHealthy());
        System.out.println();
    }

    public int randomObstacleNumber(){
        Random r=new Random();
        return r.nextInt(this.obstacle.getMaxNumber())+1;
    }
    

}
