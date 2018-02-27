package edu.gatech.pistolpropulsion.homesforall.Models;

public class ShelterManager {
    private Shelter[] shelterArray;
    private String[] namesArray;

    public ShelterManager(int count) {
        namesArray = new String[count];
        shelterArray = new Shelter[count];
    }

    public ShelterManager(String[][] data, int count) {
        this(count);
        String key = "", name = "", cap = "",
                restrictions = "", longitude = "",
                lat = "", address = "",
                notes = "", phone = "";
        for(int i = 0; i < count; i++) {
            for(int j = 0; j < 9; j++) {
//                switch(j) {
//                    case 1:
//                        key = data[i][j];
//                        break;
//                    case 2:
//                        name = data[i][j];
//                        break;
//                    case 3:
//                        cap = data[i][j];
//                        break;
//                    case 4:
//                        restrictions = data[i][j];
//                        break;
//                    case 5:
//                        longitude = data[i][j];
//                        break;
//                    case 6:
//                        lat = data[i][j];
//                        break;
//                    case 7:
//                        address = data[i][j];
//                        break;
//                    case 8:
//                        notes = data[i][j];
//                        break;
//                    case 9:
//                        phone = data[i][j];
//                        break;
//                    default:
//                        break;
//                }
//            }
//            shelterArray[i] = new Shelter(key, name, cap, restrictions, longitude, lat, address, notes, phone);
        }
    }

    public String[] getNamesArray() {
        return this.namesArray;
    }

    public Shelter[] getShelterArray() {
        return this.shelterArray;
    }
}
