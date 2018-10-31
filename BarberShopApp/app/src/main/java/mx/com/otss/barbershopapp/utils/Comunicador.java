package mx.com.otss.barbershopapp.utils;

import java.util.ArrayList;

public class Comunicador
{
    private static ArrayList<BarberShop> obj = new ArrayList<BarberShop>();

    public static void setOBJ(BarberShop newObjeto) {
        obj.add(newObjeto);
    }

    public static ArrayList<BarberShop> getOBJ() {
        return obj;
    }

    public static void limpiar(){
        obj.clear();
    }
}
