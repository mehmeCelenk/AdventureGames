public class ToolStore extends NormalLoc{

    ToolStore(Player player){
        super(player, "Mağaza");
    }


    public boolean getLocation(){
        System.out.println("Para : " + player.getMoney());
        System.out.println("1- Silahlar");
        System.out.println("2- Zırhlar");
        System.out.println("3- Çıkış");
        int selTool = scan.nextInt();
        int selItemID;

        switch (selTool){
            case 1:
                selItemID = weaponMenu();
                buyWeapon(selItemID);
                break;

            case 2:
                selItemID = armorMenu();
                buyArmor(selItemID);
                break;

            case 3:
                System.out.println("Çıkış yapılıyor");
                break;

            default:
                break;
        }
        return true;
    }

    public int armorMenu(){
        System.out.println("1- Hafif \t <para :15 - hasar : 1>");
        System.out.println("2- Orta \t <para :25 - hasar : 3>");
        System.out.println("3- Ağır \t <para :40 - hasar : 5>");
        System.out.println("4- Çıkış");
        System.out.println("Seçiminiz");
        int selArmorID = scan.nextInt();
        return selArmorID;
    }

    public void buyArmor(int itemID){
        int avoid = 0, price = 0;
        String aName = null;

        switch (itemID){
            case 1:
                avoid = 1;
                aName = "Hafif Zırh";
                price = 15;
                break;
            case 2:
                avoid = 3;
                aName = "Orta zırh";
                price = 25;
                break;

            case 3:
                avoid = 5;
                aName = "Ağır zırh";
                price = 40;
                break;

            case 4:
                System.out.println("Çıkış yapılıyor");
                break;

            default:
                System.out.println("Geçersiz işlem");
                break;
        }
        if(price > 0){
            if(player.getMoney() >= price){
                player.getInv().setArmorDefence(avoid);
                player.getInv().setArmorName(aName);
                player.setMoney(player.getMoney() - price);
                System.out.println(aName + " Satın aldınız, engellenen hasar :" + player.getInv().getArmorDefence());
                System.out.println("Kalan para :" + player.getMoney());
            }else{
                System.out.println("Paranız yetersiz !");
            }
        }
    }

    public int weaponMenu(){
        System.out.println("1- Tabanca\t <para :25 - hasar :2>");
        System.out.println("2- Kılıç\t <para :35 - hasar :3>");
        System.out.println("3- Tüfek\t <para :45 - hasar :7>");
        System.out.println("4- Çıkış");
        System.out.print("Seçiminiz :");
        int selWeaponID = scan.nextInt();
        return selWeaponID;
    }

    public void buyWeapon(int itemID){
        int damage = 0, price = 0;
        String wName = null;

        switch (itemID){
            case 1:
                damage = 2;
                wName = "Tabanca";
                price = 25;
                break;

            case 2:
                damage = 3;
                wName = "Kılıç";
                price = 35;
                break;

            case 3:
                damage = 7;
                wName = "Tüfek";
                price = 45;
                break;

            case 4:
                System.out.println("Çıkış yapılıyor");
                break;

            default:
                System.out.println("Geçersiz işlem");
                break;
        }
        if(price > 0){
            if(player.getMoney() > price){
                player.getInv().setWeaponDamage(damage);
                player.getInv().setWeaponName(wName);
                player.setMoney(player.getMoney() - price);
                System.out.println(wName + " Satın aldınız, önceki hasar :" + player.getDamage() + " Yeni hasarınız :" + (player.getTotalDamage()));
                System.out.println("Kalan para :" + player.getMoney());
            }else {
                System.out.println("Para yetersiz");
            }
        }
    }
}
